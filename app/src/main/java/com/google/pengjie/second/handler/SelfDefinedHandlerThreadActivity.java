package com.google.pengjie.second.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 3/13/18.
 */

public class SelfDefinedHandlerThreadActivity extends AppCompatActivity {
    private static final String TAG = "nimei";
    private EditText editText;
    private Button button;
    private MyThread myThread = new MyThread();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "here in onCreate, currentThread = " + Thread.currentThread().getId());
        super.onCreate(savedInstanceState);
        myThread.start();

        setContentView(R.layout.activity_self_defined_handler_thread);

        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.arg1 = 1;
                myThread.getHandler().sendMessage(message);
            }
        });
    }

    private class MyThread extends Thread {
        Handler handler = new MyHandler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.e(TAG, "here in = " + msg.arg1);
                return true;
            }
        });

        @Override
        public void run() {
            super.run();
            Log.e(TAG, "here in run(), currentThread = " + Thread.currentThread().getId());
            Looper.prepare();
            Looper.loop();
        }

        Handler getHandler() {
            return handler;
        }
    }

    private class MyHandler extends Handler {
        public MyHandler() {
            super();
            Log.e(TAG, "here in MyHandler()");
        }

        public MyHandler(Callback callback) {
            super(callback);
            Log.e(TAG, "here in MyHandler(Callback callback), currentThread = " + Thread.currentThread().getId());
        }

        public MyHandler(Looper looper) {
            super(looper);
            Log.e(TAG, "here in MyHandler(Looper looper)");
        }

        public MyHandler(Looper looper, Callback callback) {
            super(looper, callback);
            Log.e(TAG, "here in MyHandler(Looper looper, Callback callback)");
        }
    }
}
