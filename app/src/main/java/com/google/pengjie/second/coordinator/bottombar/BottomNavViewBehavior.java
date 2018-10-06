package com.google.pengjie.second.coordinator.bottombar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.pengjie.second.R;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.view.ViewCompat.SCROLL_AXIS_VERTICAL;

/**
 * Created by pengjie on 8/10/17.
 */

public class BottomNavViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {
    public static final String TAG = BottomNavViewBehavior.class.getSimpleName();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private int height;

    public BottomNavViewBehavior() {
        super();
    }

    public BottomNavViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        Log.e(TAG, "here in layoutDependsOn()");
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        recyclerView = (RecyclerView) dependency.findViewById(R.id.recycler_view);
        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        Log.e(TAG, "here in onDependentViewChanged(), w = " + dependency.getWidth() + "; h = " + dependency.getHeight() + "; x = " + dependency.getX() + "; y = " + dependency.getY() + "; top = " + dependency.getTop() + "; last = " + linearLayoutManager.findLastCompletelyVisibleItemPosition() + "; count = " + recyclerView.getAdapter().getItemCount());
        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < recyclerView.getAdapter().getItemCount() - 1) {
            height = child.getHeight();
            Log.e(TAG, "here >>>>> height = " + height);
            child.setTranslationY(height);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, BottomNavigationView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        Log.e(TAG, "here in onMeasureChild(), widthUsed = " + widthUsed + "; heightUsed = " + heightUsed);
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
        Log.e(TAG, "here in onLayoutChild(), " + (linearLayoutManager != null) + "; " + (recyclerView != null) + "; " + (linearLayoutManager != null?linearLayoutManager.findLastCompletelyVisibleItemPosition():"null") + "; " + (recyclerView != null?recyclerView.getAdapter().getItemCount():"null"));
        if (linearLayoutManager != null && recyclerView != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() < recyclerView.getAdapter().getItemCount() - 1) {
            height = child.getHeight();
            Log.e(TAG, "here >>>>> height = " + height);
            child.setTranslationY(height);
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.e(TAG, "here in onStartNestedScroll(), " + directTargetChild.getClass().getSimpleName() + "; " + target.getClass().getSimpleName());
        return nestedScrollAxes == SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target) {
        Log.e(TAG, "here in onStopNestedScroll(), " + linearLayoutManager.findLastCompletelyVisibleItemPosition());
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "here in onNestedScroll(), dxConsumed = " + dxConsumed + "; dyConsumed = " + dyConsumed + "; dxUnconsumed = " + dxUnconsumed + "; dyUnconsumed = " + dyUnconsumed + "; last = " + linearLayoutManager.findLastCompletelyVisibleItemPosition());
        if (dyConsumed == 0) {
            if (dyUnconsumed > 0) {
                // Means recycler view content is scrolled up.
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
                    if (child.getTranslationY() > 0) {
                        Log.e(TAG, "here ======= " + child.getTranslationY());
                        int need = (int) Math.min(child.getTranslationY(), (float) dyUnconsumed);
                        recyclerView.setTranslationY(-(-recyclerView.getTranslationY() + need));
                        child.setTranslationY(child.getTranslationY() - need);
                    }
                }
            }
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, int dx, int dy, int[] consumed) {
        Log.e(TAG, "here in onNestedPreScroll(), dx = " + dx + "; dy = " + dy);
        // Suppose we already show the BottomNavigationView due to user scroll
        if (dy  < 0 && child.getTranslationY() < height && linearLayoutManager.findFirstCompletelyVisibleItemPosition() > 0) {
            int need = (int) Math.min(height - child.getTranslationY(), -dy);
            child.setTranslationY(child.getTranslationY() + need);
            recyclerView.setTranslationY(Math.min(0, recyclerView.getTranslationY() + need));
            consumed[1] = -need;
        } else
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.e(TAG, "here in onNestedFling(), velocityX = " + velocityX + "; velocityY = " + velocityY + "; consumed = " + consumed + "; last = " + linearLayoutManager.findLastCompletelyVisibleItemPosition());

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, float velocityX, float velocityY) {
        Log.e(TAG, "here in onNestedPreFling(), velocityX = " + velocityX + "; velocityY = " + velocityY);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
}
