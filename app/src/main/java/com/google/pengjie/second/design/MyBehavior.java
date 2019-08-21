package com.google.pengjie.second.design;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

public class MyBehavior extends CoordinatorLayout.Behavior implements NestedScrollingChild {

    private NestedScrollingChildHelper nestedScrollingChildHelper;
    private float x;
    private float y;
    private int[] consumed = new int[2];
    private int[] offsetInWindow = new int[2];

    public MyBehavior(Context context, AttributeSet attributeSet) {
        Log.e("here", "here in MyBehavior.constructor");
    }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull MotionEvent ev) {
        Log.e("here", "here in MyBehavior.onTouchEvent");
        if (nestedScrollingChildHelper == null) {
            nestedScrollingChildHelper = new NestedScrollingChildHelper(child);
            setNestedScrollingEnabled(true);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                y = ev.getY();
                if (x < child.getLeft() || x > child.getRight() || y < child.getTop() || y > child.getBottom()) {
                    return false;
                }
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                break;
            case MotionEvent.ACTION_MOVE:
                float nx = ev.getX();
                float ny = ev.getY();
                float dx = nx - x;
                float dy = ny - y;
                if (Math.abs(dx) < Math.abs(dy)) {
                    dispatchNestedPreScroll((int)dx, -(int)dy, consumed, offsetInWindow);
                }
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        nestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return nestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.e("here", "here in startNestedScroll(int axes)" + axes);
        return nestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        Log.e("here", "here in stopNestedScroll()");
        nestedScrollingChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return nestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return nestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        boolean ret = nestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
        Log.e("here", "here in dispatchNestedPreScroll(int, int, int[], int[])" + dx + ", " + dy + ", " + consumed + ", " + offsetInWindow);
        return ret;
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return nestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return nestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
