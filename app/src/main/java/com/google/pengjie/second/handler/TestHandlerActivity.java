package com.google.pengjie.second.handler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 3/13/18.
 */

public class TestHandlerActivity extends AppCompatActivity {

    private Button selfDefinedHandlerThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);

        selfDefinedHandlerThread = findViewById(R.id.self_define_handler_thread);
        selfDefinedHandlerThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestHandlerActivity.this, SelfDefinedHandlerThreadActivity.class));
            }
        });
    }
}
