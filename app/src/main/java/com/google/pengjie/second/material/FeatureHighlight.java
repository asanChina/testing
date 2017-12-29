package com.google.pengjie.second.material;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Feature Highlights are UI components that provide the user with guidance about
 * selected controls, features and flows. They focus attention on a single UI element by
 * presenting an animated, modal view on top of the interface.
 * <p>
 * To set a feature highlight using the ID of a view, use:
 * <pre>{@code
 *   FeatureHighlight featureHighlight = FeatureHighlight.Builder.forView(targetViewId)
 *       .setHeader("Tap the plus icon to create...");
 *       .build();
 *   featureHighlight.show(activity);
 * }</pre>
 */
public class FeatureHighlight {

    /**
     * Builds a {@link FeatureHighlight}.
     * <p>
     * To get a Builder, use:
     * <pre>{@code
     *   FeatureHighlight.Builder builder = FeatureHighlight.Builder.forView(targetViewId);
     * }</pre>
     */
    public static class Builder {
        @IdRes private int targetViewId = 0;
        @IdRes private int confiningViewId = android.R.id.content;
        @Nullable private String headerText = null;
        @Nullable private String bodyText = null;
        @ColorInt private int outerColor = 0;
        @ColorInt private int innerColor = 0;
        @DrawableRes private int innerDrawableId = 0;

        private Builder(@IdRes int targetViewId) {
            this.targetViewId = targetViewId;
        }

        /**
         * Returns a new {@link Builder}, specifying the target view to be highlighted.
         * @param targetViewId The ID of the view to highlight.
         */
        public static Builder forView(@IdRes int targetViewId) {
            return new Builder(targetViewId);
        }

        /**
         * Sets the view used to confine the drawing of the highlight. Usually, this is an ancestor
         * view of the target view.
         * @param confiningViewId The ID of the confining view.
         */
        public Builder setConfiningView(@IdRes int confiningViewId) {
            this.confiningViewId = confiningViewId;
            return this;
        }

        /**
         * Sets the text to be displayed as the header help text. The header is displayed above
         * the body text.
         */
        public Builder setHeader(@Nullable String headerText) {
            this.headerText = headerText;
            return this;
        }

        /**
         * Sets the text to be displayed as the header body text. The body is displayed under the
         * header text.
         */
        public Builder setBody(@Nullable String bodyText) {
            this.bodyText = bodyText;
            return this;
        }

        /**
         * Sets the color to be used for the outer highlight.
         */
        public Builder setOuterColor(@ColorInt int outerColor) {
            this.outerColor = outerColor;
            return this;
        }

        /**
         * Sets the color to be used as the background of the inner zone.
         */
        public Builder setInnerColor(@ColorInt int innerColor) {
            this.innerColor = innerColor;
            return this;
        }

        /**
         * Sets a drawable to be drawn inside the inner zone. If this is not supplied, the target
         * view will be drawn inside the inner zone.
         */
        public Builder setInnerDrawable(@DrawableRes int innerDrawableId) {
            this.innerDrawableId = innerDrawableId;
            return this;
        }

        // TODO(kennyy): For v2, add support for setting custom layout IDs.

        // TODO(kennyy): For v1, add support for event tracking IDs.

        /**
         * Builds and returns a {@link FeatureHighlight} using the builder's current state.
         */
        public FeatureHighlight build() {
            return new FeatureHighlight(
                    targetViewId,
                    confiningViewId,
                    headerText,
                    bodyText,
                    outerColor,
                    innerColor,
                    innerDrawableId);
        }
    }

    @IdRes private final int viewId;
    @IdRes private final int containerId;
    @Nullable private final String headerText;
    @Nullable private final String bodyText;
    @ColorInt private final int outerColor;
    @ColorInt private final int innerColor;
    @DrawableRes private final int innerDrawableId;

    private FragmentActivity activity;
    private FeatureHighlightFragment fragment;

    private FeatureHighlight(
            @IdRes int viewId,
            @IdRes int containerId,
            @Nullable String headerText,
            @Nullable String bodyText,
            @ColorInt int outerColor,
            @ColorInt int innerColor,
            @DrawableRes int innerDrawableId) {
        this.viewId = viewId;
        this.containerId = containerId;
        this.headerText = headerText;
        this.bodyText = bodyText;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
        this.innerDrawableId = innerDrawableId;
    }

    /**
     * Show this Feature Highlight in the given activity.
     */
    public void show(FragmentActivity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }

        this.activity = activity;

        fragment =
                FeatureHighlightFragment.newInstance(
                        viewId, containerId, headerText, bodyText, outerColor, innerColor, innerDrawableId);
        fragment.show(activity.getSupportFragmentManager());
    }

    /**
     * Hides the Feature Highlight and removes it from the activity.
     */
    public void hide() {
        if (activity == null || fragment == null || !fragment.isAdded()) {
            return;
        }

        fragment.hide();
        activity = null;
        fragment = null;
    }
}