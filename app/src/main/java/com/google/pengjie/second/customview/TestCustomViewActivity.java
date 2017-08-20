package com.google.pengjie.second.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 8/19/17.
 */

public class TestCustomViewActivity extends AppCompatActivity {

    private Button testCustomTitleView;
    private Button testCustomImageView;
    private Button testProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);

        testCustomTitleView = (Button) findViewById(R.id.test_custom_title_view);
        testCustomImageView = (Button) findViewById(R.id.test_custom_image_view);
        testProgressView = (Button) findViewById(R.id.test_custom_progress_view);

        testCustomTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, CustomTitleViewActivity.class));
            }
        });
        testCustomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, CustomImageViewActivity.class));
            }
        });
        testProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, ProgressViewActivity.class));
            }
        });
    }
}
