package com.google.pengjie.second.material;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.pengjie.second.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A fragment that encapsulates the Feature Highlight UI.
 * <p>
 * Use {@link FeatureHighlight} to create and manage the fragments.
 */
public class FeatureHighlightFragment extends Fragment implements View.OnClickListener {

    private static final String FRAGMENT_TAG =
            "com.google.android.libraries.material.featurehighlight.FeatureHighlightFragment";

    private static final String ARG_VIEW_ID = "fh_view_id";
    private static final String ARG_CONTAINER_ID = "fh_container_id";
    private static final String ARG_HEADER_TEXT = "fh_header_text";
    private static final String ARG_BODY_TEXT = "fh_body_text";
    private static final String ARG_INNER_COLOR = "fh_inner_color";
    private static final String ARG_OUTER_COLOR = "fh_outer_color";
    private static final String ARG_INNER_DRAWABLE = "fh_inner_drawable";

    private static final String SHOW_STATE_KEY = "showState";

    /**
     * Whether the feature highlight is being shown and who triggered the show.
     */
    @IntDef({SHOW_STATE_NOT_SHOWN, SHOW_STATE_USER_SHOWN, SHOW_STATE_RESTORE_SHOWN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowState {}

    private static final int SHOW_STATE_NOT_SHOWN = 0;
    private static final int SHOW_STATE_USER_SHOWN = 1;
    private static final int SHOW_STATE_RESTORE_SHOWN = 2;

    /**
     * Returns a new fragment that can be shown using {@link #show(FragmentManager)}.
     */
    static FeatureHighlightFragment newInstance(
            @IdRes int viewId,
            @IdRes int containerId,
            @Nullable String headerText,
            @Nullable String bodyText,
            @ColorInt int outerColor,
            @ColorInt int innerColor,
            @DrawableRes int innerDrawableId) {

        Bundle args = new Bundle();
        args.putInt(ARG_VIEW_ID, viewId);
        args.putInt(ARG_CONTAINER_ID, containerId);
        args.putString(ARG_HEADER_TEXT, headerText);
        args.putString(ARG_BODY_TEXT, bodyText);
        args.putInt(ARG_OUTER_COLOR, outerColor);
        args.putInt(ARG_INNER_COLOR, innerColor);
        args.putInt(ARG_INNER_DRAWABLE, innerDrawableId);

        FeatureHighlightFragment fragment = new FeatureHighlightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @IdRes private int viewId;
    @IdRes private int containerId;
    @Nullable private String headerText;
    @Nullable private String bodyText;
    @ColorInt private int outerColor;
    @ColorInt private int innerColor;
    @ColorRes
    private int innerDrawableId;

    private FeatureHighlightView featureHighlightView;
    private int backStackId;

    @ShowState private int showState = SHOW_STATE_NOT_SHOWN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args == null) {
            return;
        }
        viewId = args.getInt(ARG_VIEW_ID);
        containerId = args.getInt(ARG_CONTAINER_ID);
        headerText = args.getString(ARG_HEADER_TEXT);
        bodyText = args.getString(ARG_BODY_TEXT);
        outerColor = args.getInt(ARG_OUTER_COLOR);
        innerColor = args.getInt(ARG_INNER_COLOR);
        innerDrawableId = args.getInt(ARG_INNER_DRAWABLE);

        if (savedInstanceState != null) {
            showState = getShowState(savedInstanceState.getInt(SHOW_STATE_KEY));
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        featureHighlightView = new FeatureHighlightView(getContext());

        // TODO(kennyy): Support inflating user-defined drawables/layouts and add helpful error messages
        // when they do not implement the right interfaces.
        HelpText helpText =
                (HelpText) inflater.inflate(R.layout.help_text, featureHighlightView, false);
        helpText.setBodyText(bodyText);
        helpText.setHeaderText(headerText);

        if (outerColor != 0) {
            featureHighlightView.setOuterColor(outerColor);
        }

        if (innerColor != 0) {
            featureHighlightView.setInnerColor(innerColor);
        }

        if (innerDrawableId != 0) {
            Drawable targetDrawable =
                    ResourcesCompat.getDrawable(getResources(), innerDrawableId, getContext().getTheme());
            featureHighlightView.setTargetDrawable(targetDrawable);
        }

        featureHighlightView.setContent(helpText);

        return featureHighlightView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (featureHighlightView != null) {
            View targetView = findTargetView();
            if (targetView == null) {
                // TODO(kennyy): Pass an error callback instead if the target view was not found.
                throw new RuntimeException("Target view was not found in hierarchy");
            }

            featureHighlightView.init(targetView, this);

            if (showState == SHOW_STATE_USER_SHOWN) {
                featureHighlightView.doShowAnimation();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SHOW_STATE_KEY, showState);
    }

    /**
     * Shows the feature highlight fragment. This results in this Fragment being added.
     */
    public void show(FragmentManager fragmentManager) {
        showState = SHOW_STATE_USER_SHOWN;

        backStackId =
                fragmentManager.beginTransaction().add(android.R.id.content, this, FRAGMENT_TAG).commit();
    }

    /**
     * Hides the feature highlight fragment. This results in the removal of this Fragment.
     */
    public void hide() {
        if (showState == SHOW_STATE_NOT_SHOWN) {
            return;
        }
        showState = SHOW_STATE_NOT_SHOWN;

        if (featureHighlightView != null) {
            featureHighlightView.dismiss(
                    new Runnable() {
                        @Override
                        public void run() {
                            FragmentManager fragmentManager = getFragmentManager();
                            if (backStackId >= 0) {
                                fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                                backStackId = -1;
                            } else {
                                fragmentManager
                                        .beginTransaction()
                                        .remove(FeatureHighlightFragment.this)
                                        .commitAllowingStateLoss();
                            }
                        }
                    });
            featureHighlightView = null;
        }
    }

    private View findTargetView() {
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }

        if (containerId != 0) {
            View viewInContainer = activity.findViewById(containerId).findViewById(viewId);
            if (viewInContainer != null) {
                // It is not necessarily true that the view is confined by an ancestor view, so we
                // try fallback gracefully in that case.
                return viewInContainer;
            }
        }
        return activity.findViewById(viewId);
    }

    @Override
    public void onClick(View view) {
        // TODO(kennyy): When implementing touch/click support, add handling here.
        if (view == featureHighlightView) {
            hide();
        }
    }

    @ShowState
    private static int getShowState(int value) {
        switch (value) {
            case SHOW_STATE_NOT_SHOWN:
            case SHOW_STATE_USER_SHOWN:
            case SHOW_STATE_RESTORE_SHOWN:
                return value;
            default:
                throw new IllegalArgumentException("Unrecognised show state.");
        }
    }
}
