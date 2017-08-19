package com.google.pengjie.second.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 8/19/17.
 */

public class CustomTitleViewActivity extends AppCompatActivity {

    private CustomTitleView customTitleView;
    private EditText editText;
    private Button setText;
    private Button setSize;
    private Button setColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_title_view);

        customTitleView = (CustomTitleView) findViewById(R.id.custom_title_view);
        editText = (EditText) findViewById(R.id.edit_text);
        setText = (Button) findViewById(R.id.set_text);
        setSize = (Button) findViewById(R.id.set_size);
        setColor = (Button) findViewById(R.id.set_color);

        setText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
                    customTitleView.setText(editText.getText().toString());
                }
            }
        });

        setSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
                    int size = Integer.parseInt(editText.getText().toString());
                    customTitleView.setTextSize(size);
                }
            }
        });

        setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
                    int color = Integer.parseInt(editText.getText().toString());
                    customTitleView.setTextColor(color);
                }
            }
        });
    }

    private boolean valid() {
        if (TextUtils.isEmpty(editText.getText())) {
            return false;
        }
        return true;
    }
}
