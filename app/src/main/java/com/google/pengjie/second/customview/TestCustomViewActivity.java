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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);

        testCustomTitleView = (Button) findViewById(R.id.test_custom_title_view);

        testCustomTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomViewActivity.this, CustomTitleViewActivity.class));
            }
        });
    }
}
