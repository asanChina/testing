package com.google.pengjie.second.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 8/19/17.
 */

public class ProgressViewActivity extends AppCompatActivity {
    private ProgressView progressView;
    private Button start;
    private Button end;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_view);

        progressView = (ProgressView) findViewById(R.id.progress_view);
        start = (Button) findViewById(R.id.start);
        end = (Button) findViewById(R.id.end);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressView.start();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressView.end();
            }
        });
    }
}
