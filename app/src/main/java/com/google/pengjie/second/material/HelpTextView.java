package com.google.pengjie.second.material;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.pengjie.second.R;

/**
 * Default implementation of {@link HelpText}. Two vertically stacked text views with no
 * background.
 */
public class HelpTextView extends LinearLayout implements HelpText {

    private TextView headerTextView;
    private TextView bodyTextView;

    public HelpTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        headerTextView =
                        (TextView) findViewById(R.id.featurehighlight_help_text_header_view);
        bodyTextView =
                        (TextView) findViewById(R.id.featurehighlight_help_text_body_view);
    }

    @Override
    public void setHeaderText(String headerText) {
        headerTextView.setText(headerText);
    }

    @Override
    public void setBodyText(String bodyText) {
        bodyTextView.setText(bodyText);
    }

    @Override
    public View asView() {
        return this;
    }
}