package com.google.pengjie.second.material;

import android.view.View;

/**
 * Interface for views that show help text on a Feature Highlight.
 */
public interface HelpText {

    void setHeaderText(String headerText);

    void setBodyText(String bodyText);

    /**
     * Returns the view that can be added to the view hierarchy.
     */
    View asView();
}