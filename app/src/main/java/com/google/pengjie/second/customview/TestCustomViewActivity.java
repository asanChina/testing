package com.google.pengjie.second.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 8/19/17.
 */

public class TestCustomViewActivity extends AppCompatActivity {

    private Button testColor;
    private Button testCustomTitleView;
    private Button testCustomImageView;
    private Button testProgressView;
    private Button testCustomViewGroup;
    private Button testXfermode;
    private Button testShader;
    private Button testPathEffect;
    private Button testTabIndicator;
    private Button testViewPagerIndicator;
    private Button testStupidView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);

        testColor = (Button) findViewById(R.id.test_color);
        testCustomTitleView = (Button) findViewById(R.id.test_custom_title_view);
        testCustomImageView = (Button) findViewById(R.id.test_custom_image_view);
        testProgressView = (Button) findViewById(R.id.test_custom_progress_view);
        testCustomViewGroup = (Button) findViewById(R.id.test_custom_viewgroup);
        testXfermode = (Button) findViewById(R.id.test_custom_xfermode);
        testShader = (Button) findViewById(R.id.test_shader);
        testPathEffect = (Button) findViewById(R.id.test_path_effect);
        testTabIndicator = findViewById(R.id.test_tab_indicator);
        testViewPagerIndicator = findViewById(R.id.test_view_pager_indicator);
        testStupidView = findViewById(R.id.test_stupid_view);

        testColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, ColorActivity.class));
            }
        });
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
        testCustomViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, SimpleViewGroupActivity.class));
            }
        });
        testXfermode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, XfermodeActivity.class));
            }
        });
        testShader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, ShaderActivity.class));
            }
        });
        testPathEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, PathEffectActivity.class));
            }
        });
        testTabIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, TabIndicatorActivity.class));
            }
        });
        testViewPagerIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, ViewPagerIndicatorActivity.class));
            }
        });
        testStupidView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, StupidViewActivity.class));
            }
        });
    }
}
