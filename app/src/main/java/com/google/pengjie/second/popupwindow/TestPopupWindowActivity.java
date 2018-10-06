package com.google.pengjie.second.popupwindow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by pengjie on 2/4/18.
 */

public class TestPopupWindowActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Button button;
    private TextView textView;
    private PopupWindow popupWindow;
    private Button dismissButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pop_up_window);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow = new PopupWindow(TestPopupWindowActivity.this);
                View view = TestPopupWindowActivity.this.getLayoutInflater().inflate(R.layout.popup_window, null, false);
                dismissButton = (Button) view.findViewById(R.id.dismiss);

                popupWindow.setContentView(view);
                dismissButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setClippingEnabled(false);

                popupWindow.showAsDropDown(textView, 0, 0);

               //popupWindow.showAtLocation(relativeLayout, Gravity.TOP, 100, 0);
            }
        });

    }
}
