package com.google.pengjie.second.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 2/5/18.
 */

public class TestDialogActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(TestDialogActivity.this);
                dialog.setContentView(R.layout.popup_window);
                dialog.show();
                Window window = dialog.getWindow();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.width = 500;
                lp.height = 500;
                lp.x = 100;

                window.setAttributes(lp);

            }
        });
    }
}
