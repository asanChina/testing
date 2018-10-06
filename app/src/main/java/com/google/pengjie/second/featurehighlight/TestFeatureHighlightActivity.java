package com.google.pengjie.second.featurehighlight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 8/20/17.
 */

public class TestFeatureHighlightActivity extends AppCompatActivity {
    private Button showFeatureHighlight;
    private MyFeatureHighlightView featureHighlightView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_feature_highlight);

        showFeatureHighlight = (Button) findViewById(R.id.show_feature_highlight);
        featureHighlightView = (MyFeatureHighlightView) findViewById(R.id.feature_highlight);

        showFeatureHighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                featureHighlightView.showHighlight(R.id.second);
            }
        });
    }
}
