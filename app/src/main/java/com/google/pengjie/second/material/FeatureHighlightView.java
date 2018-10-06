package com.google.pengjie.second.material;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.google.pengjie.second.R;

import androidx.annotation.ColorInt;

/**
 * A view group that does custom drawing behind a {@link HelpText}.
 */
public final class FeatureHighlightView extends ViewGroup {

    // Pre-allocated array for holding co-ordinates.
    private final int[] coordinates = new int[2];
    private final Rect targetBounds = new Rect();
    private final Rect textBounds = new Rect();

    private final int helpTextTabletWidth;
    private final int innerZoneRadius;
    private final int innerZoneMargin;

    private OuterHighlightDrawable outerHighlight;
    private InnerZoneDrawable innerZone;
    private HelpText helpText;
    private View targetView;
    private Drawable targetDrawable;

    private int targetWidth;
    private int targetHeight;
    private int targetX;
    private int targetY;

    public FeatureHighlightView(Context context) {
        super(context);

        Resources resources = getResources();
        helpTextTabletWidth =
                resources.getDimensionPixelSize(
                        R.dimen.libraries_material_featurehighlight_help_text_width_tablet);
        innerZoneRadius =
                resources.getDimensionPixelSize(R.dimen.libraries_material_featurehighlight_inner_radius);
        innerZoneMargin =
                resources.getDimensionPixelSize(R.dimen.libraries_material_featurehighlight_inner_margin);
        // This ViewGroup does custom drawing.
        setWillNotDraw(false);

        // Prevent layout until initialized.
        setVisibility(GONE);
    }

    /**
     * Sets the child view.
     */
    public void setContent(HelpText helpText) {
        this.helpText = helpText;
        addView(helpText.asView(), 0);
        innerZone = new InnerZoneDrawable(getContext());
        innerZone.setCallback(this);
        outerHighlight = new OuterHighlightDrawable(getContext());
        outerHighlight.setCallback(this);
    }

    /**
     * Initializes the highlight view by supplying the target View to be highlighted.
     */
    public void init(View targetView, OnClickListener clickListener) {
        this.targetView = targetView;
        setOnClickListener(clickListener);

        setVisibility(VISIBLE);
    }

    public void dismiss(Runnable dismissRunnable) {
        // TODO(kennyy): When implementing animations, make this play the dismiss animation.
        dismissRunnable.run();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Expectation: width is match_parent and height is match_parent.
        // In other words, width and height is [AT_MOST, size] or [EXACTLY, size] in all expected cases.
        // We fill up the entire space allowed.
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        // NOTE: helpText is measured in onLayout() because it depends on the targetView's layout.

        // Complete the contract by calling setMeasuredDimension().
        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Expect this view to layout after the targetView.
        getRelativeLocation(coordinates, targetView);

        targetX = coordinates[0];
        targetY = coordinates[1];
        targetWidth = targetView.getWidth();
        targetHeight = targetView.getHeight();
        calculateTargetBounds();

        if (targetWidth == 0 || targetHeight == 0) {
            // Nothing can be done.
            helpText.asView().layout(0, 0, 0, 0);
        } else {
            int centerY = targetBounds.centerY();
            boolean helpTextBelowInnerZone = centerY < (getMeasuredHeight() / 2);

            int innerZoneHeight = getInnerZoneHeight();
            int innerBottom = centerY + (innerZoneHeight / 2) + innerZoneMargin;

            // Measure and layout helpText. Measure is called here because it depends on the targetView's
            // layout.
            if (helpTextBelowInnerZone) {
                int availableHeight = getMeasuredHeight() - innerBottom;
                measureHelpText(getMeasuredWidth(), availableHeight);
                int childLeft = getHelpLeft(helpText.asView());
                layoutBelowInnerZone(helpText.asView(), childLeft, innerBottom);
            } else {
                int innerTop = centerY - (innerZoneHeight / 2) - innerZoneMargin;
                measureHelpText(getMeasuredWidth(), innerTop);
                int childLeft = getHelpLeft(helpText.asView());
                layoutAboveInnerZone(helpText.asView(), childLeft, innerTop);
            }

            calculateHelpTextBounds();
            outerHighlight.setBounds(left, top, right, bottom);
            outerHighlight.setDrawConstraints(targetBounds, textBounds);
            innerZone.setBounds(left, top, right, bottom);
            innerZone.setDrawConstraints(targetBounds);
        }
    }

    private int getHelpLeft(View helpTextView) {
        int childLeft;
        if (isPortraitPhoneSized()) {
            // On phone, help text is full-width and centered.
            childLeft = (getMeasuredWidth() / 2) - (helpTextView.getMeasuredWidth() / 2);
        } else {
            // TODO(kennyy): On tablet, help text is fixed-width.
            childLeft = 0;
        }
        return childLeft;
    }

    private void measureHelpText(int width, int height) {
        // On phones, its width should match the parent's width. On tablet, its width should be fixed.
        // Its height is wrap_content. It is only constrained by the parent and the inner zone.
        // We don't care if it's supposed to be above or below the inner zone.
        MarginLayoutParams helpTextLayoutParams =
                ((MarginLayoutParams) helpText.asView().getLayoutParams());
        int helpTextWidth =
                isPortraitPhoneSized()
                        ? width - helpTextLayoutParams.leftMargin - helpTextLayoutParams.rightMargin
                        : helpTextTabletWidth;

        helpText
                .asView()
                .measure(
                        MeasureSpec.makeMeasureSpec(helpTextWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
    }

    // Returns the height of the inner zone. Should be called after the targetView has done its layout
    // in the current pass.
    private int getInnerZoneHeight() {
        int innerHeight;
        if (targetDrawable != null) {
            innerHeight = targetDrawable.getBounds().height();
        } else {
            innerHeight = targetView.getHeight();
        }
        return Math.max(2 * innerZoneRadius, innerHeight);
    }

    private void layoutBelowInnerZone(View child, int childLeft, int innerBottom) {
        child.layout(
                childLeft,
                innerBottom,
                childLeft + child.getMeasuredWidth(),
                innerBottom + child.getMeasuredHeight());
    }

    private void layoutAboveInnerZone(View child, int childLeft, int innerTop) {
        child.layout(
                childLeft,
                innerTop - child.getMeasuredHeight(),
                childLeft + child.getMeasuredWidth(),
                innerTop);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        outerHighlight.draw(canvas);
        innerZone.draw(canvas);

        // Draw either the drawable or the view.
        canvas.save();
        if (targetDrawable != null) {
            float centerX = targetX + (targetWidth / 2f);
            float centerY = targetY + (targetHeight / 2f);
            canvas.translate(
                    centerX - (targetDrawable.getBounds().width() / 2f),
                    centerY - (targetDrawable.getBounds().height() / 2f));
            targetDrawable.draw(canvas);
        } else if (targetView != null) {
            canvas.translate(targetX, targetY);
            targetView.draw(canvas);
        } else {
            throw new IllegalStateException("Neither target view nor drawable was set");
        }
        canvas.restore();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams params) {
        return params instanceof MarginLayoutParams;
    }

    private boolean isPortraitPhoneSized() {
        // TODO(kennyy): Implement.
        return true;
    }

    public void doShowAnimation() {
        if (targetView == null) {
            throw new IllegalStateException("Target view must be set before animation");
        }
        // TODO(kennyy): Start the animation.
    }

    public void setOuterColor(@ColorInt int outerColor) {
        outerHighlight.setColor(outerColor);
    }

    public void setInnerColor(@ColorInt int innerColor) {
        innerZone.setColor(innerColor);
    }

    public void setTargetDrawable(Drawable targetDrawable) {
        this.targetDrawable = targetDrawable;

        if (targetDrawable != null) {
            targetDrawable.setBounds(
                    0, 0, targetDrawable.getIntrinsicWidth(), targetDrawable.getIntrinsicHeight());
            targetDrawable.setCallback(this);
        }
    }

    private void calculateTargetBounds() {
        targetBounds.set(targetX, targetY, targetX + targetWidth, targetY + targetHeight);
    }

    private void calculateHelpTextBounds() {
        View helpTextView = helpText.asView();
        textBounds.set(
                helpTextView.getLeft(),
                helpTextView.getTop(),
                helpTextView.getRight(),
                helpTextView.getBottom());
    }

    // Calculate the coordinates of the target view relative to this view.
    private void getRelativeLocation(int[] coordinates, View targetView) {
        getLocationInWindow(coordinates);
        int thisX = coordinates[0];
        int thisY = coordinates[1];
        targetView.getLocationInWindow(coordinates);

        coordinates[0] = coordinates[0] - thisX;
        coordinates[1] = coordinates[1] - thisY;
    }
}