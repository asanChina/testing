package com.google.pengjie.second.design;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.pengjie.second.R;

public class HideHeadBehavior extends CoordinatorLayout.Behavior {
    private static final int SCROLL_VALUE = 50;
    private int animationDuration = 200;
    private int childHeight;
    private boolean isHeadHide;
    private boolean isAnimating;

    public HideHeadBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        Log.e("here", "here in HideHeadBehavior.layoutDependOn");
        return dependency.getId() == R.id.red;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        if (target.getId() == R.id.red) {
            if (childHeight == 0) {
                childHeight = target.getHeight();
            }
            return true;
        }
        Log.e("here", "here in HideHeadBehavior.onStartNestedScroll, return false");
        return false;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (isAnimating) {
            return;
        }

        if (dy > SCROLL_VALUE && !isHeadHide) {
            hide(child, target);
        } else if (dy < -SCROLL_VALUE && isHeadHide) {
            show(child, target);
        }
    }

    public void hide(final View child, final View target) {
        isHeadHide = true;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, childHeight);
        valueAnimator.setDuration(animationDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (child.getBottom() > 0) {
                    int value = (int) animation.getAnimatedValue();
                    isAnimating = value != childHeight;
                    child.layout(child.getLeft(), -value, child.getRight(), -value + childHeight);
                    target.layout(target.getLeft(), -value + childHeight, target.getRight(), target.getBottom());
                }
            }
        });
        valueAnimator.start();
    }

    public void show(final View child, final View target) {
        isHeadHide = false;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, childHeight);
        valueAnimator.setDuration(animationDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (child.getBottom() < childHeight) {
                    int value = (int) animation.getAnimatedValue();
                    isAnimating = value != childHeight;
                    child.layout(child.getLeft(), value - childHeight, child.getRight(), value);
                    target.layout(target.getLeft(), value, target.getRight(), target.getBottom());
                }
            }
        });
        valueAnimator.start();
    }

}
