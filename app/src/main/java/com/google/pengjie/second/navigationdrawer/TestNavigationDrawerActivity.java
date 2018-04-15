package com.google.pengjie.second.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 3/17/18.
 */

public class TestNavigationDrawerActivity extends AppCompatActivity {
    private Button simpleNavigationDrawerActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_navigation_drawer);

        simpleNavigationDrawerActivity = findViewById(R.id.simple_navigation_drawer_activity);

        simpleNavigationDrawerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestNavigationDrawerActivity.this, SimpleNavigationDrawerActivity.class));
            }
        });
    }
}
