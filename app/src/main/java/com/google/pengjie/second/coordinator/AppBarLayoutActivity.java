package com.google.pengjie.second.coordinator;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 9/11/17.
 */

public class AppBarLayoutActivity extends AppCompatActivity {
    private static final String TAG = AppBarLayoutActivity.class.getSimpleName();


    private View linearLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_layout);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.e(TAG, "here in onCreate, height = " + height + "; width = " + width);

        linearLayout = findViewById(R.id.linearlayout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        linearLayout.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "here in onResume, LinearLayout.height = " + linearLayout.getHeight() + "; width = " + linearLayout.getWidth());
            }
        });
        print("", bottomNavigationView);
    }

    private void print(String space, View view) {
        Log.e(TAG, "here in print: " + space + view.getClass().getSimpleName());
        if (view instanceof ViewGroup) {
            String str = new StringBuilder(space).append(" ").toString();
            ViewGroup viewGroup = (ViewGroup) view;
            for(int i = 0; i < viewGroup.getChildCount(); ++i) {
                print(str, viewGroup.getChildAt(i));
            }
        }
    }
}
