package com.google.pengjie.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.coordinator.TestCoordinatorLayout;
import com.google.pengjie.second.customview.TestCustomViewActivity;
import com.google.pengjie.second.featurehighlight.TestFeatureHighlightActivity;
import com.google.pengjie.second.material.TestMaterialFeatureHighlightActivity;
import com.google.pengjie.second.menu.TestMenuActivity;

public class MainActivity extends AppCompatActivity {
    private Button testCoordinatorLayout;
    private Button testCustomView;
    private Button testFeatureHighlight;
    private Button testMenu;
    private Button testMaterialFeatureHighlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testCoordinatorLayout = (Button) findViewById(R.id.button_test_coordinator_layout);
        testCustomView = (Button) findViewById(R.id.button_test_custom_view);
        testFeatureHighlight = (Button) findViewById(R.id.button_test_feature_highlight);
        testMenu = (Button) findViewById(R.id.button_test_menu);
        testMaterialFeatureHighlight = (Button) findViewById(R.id.button_test_material_feature_highlight);

        testCoordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestCoordinatorLayout.class));
            }
        });
        testCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestCustomViewActivity.class));
            }
        });
        testFeatureHighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestFeatureHighlightActivity.class));
            }
        });
        testMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestMenuActivity.class));
            }
        });
        testMaterialFeatureHighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestMaterialFeatureHighlightActivity.class));
            }
        });
    }
}
