package com.google.pengjie.second.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.pengjie.second.NoViewFragement;
import com.google.pengjie.second.R;
import com.google.pengjie.second.SecondFragment;
import com.google.pengjie.second.drawable.TestDrawableActivity;

public class TestFragmentActivity extends AppCompatActivity {

    private static final String SECOND_FRAGMENT_TAG = "second fragment tag";

    private SecondFragment secondFragment;
    private Button commitButton;
    private Button startButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_fragment);

        secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag(SECOND_FRAGMENT_TAG);

        if (secondFragment == null) {
            secondFragment = new SecondFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content, secondFragment).commitNow();
        }

        commitButton = findViewById(R.id.commit_fragment_1);
        startButton = findViewById(R.id.start_activity);

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(new NoViewFragement(), "haha").commitNow();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestFragmentActivity.this, TestDrawableActivity.class));
            }
        });
    }
}
