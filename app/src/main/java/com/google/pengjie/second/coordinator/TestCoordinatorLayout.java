package com.google.pengjie.second.coordinator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;
import com.google.pengjie.second.coordinator.bottombar.BottomBarActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 8/10/17.
 */

public class TestCoordinatorLayout extends AppCompatActivity {

    private Button testBottomBar;
    private Button testAppBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_coordinator_layout);

        testBottomBar = (Button) findViewById(R.id.test_bottom_bar);
        testAppBarLayout = (Button) findViewById(R.id.test_appbar);

        testBottomBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCoordinatorLayout.this, BottomBarActivity.class));
            }
        });
        testAppBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCoordinatorLayout.this, AppBarLayoutActivity.class));
            }
        });
    }
}
