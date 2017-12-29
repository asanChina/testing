package com.google.pengjie.second.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 10/1/17.
 */

public class TestMaterialFeatureHighlightActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_material_feature_highlight);
        button = (Button) findViewById(R.id.show_feature_highlight);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeatureHighlight.Builder.forView(R.id.second).setHeader("Test Header").setBody("Test Body").build().show(TestMaterialFeatureHighlightActivity.this);
            }
        });
    }
}
