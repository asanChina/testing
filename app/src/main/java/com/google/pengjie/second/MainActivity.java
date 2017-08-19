package com.google.pengjie.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.coordinator.TestCoordinatorLayout;

public class MainActivity extends AppCompatActivity {
    private Button testCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testCoordinatorLayout = (Button) findViewById(R.id.button_test_coordinator_layout);

        testCoordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestCoordinatorLayout.class));
            }
        });
    }
}
