package com.google.pengjie.second.design;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.pengjie.second.R;

import java.util.ArrayList;
import java.util.List;

public class TestDesignActivity extends AppCompatActivity {

    private static final List<String> data = new ArrayList<String>();
    static {
        for (int i = 0; i < 200; ++i) {
            data.add("zhang" + i);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_design);
    }
}
