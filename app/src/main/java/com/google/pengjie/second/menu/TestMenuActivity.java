package com.google.pengjie.second.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.pengjie.second.R;

/**
 * Created by pengjie on 8/28/17.
 */

public class TestMenuActivity extends AppCompatActivity {
    private static final String TAG = TestMenuActivity.class.getSimpleName();

    private Button disableShare;
    private Button disableText;
    private TextView textView;
    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        disableShare = (Button) findViewById(R.id.disable);
        disableText = (Button) findViewById(R.id.disable_text);
        textView = (TextView) findViewById(R.id.text_view);

        disableShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.findItem(R.id.share).setEnabled(false);
            }
        });
        disableText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setEnabled(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Log.e(TAG, "here share menu item clicked.");
                return true;
            case R.id.delete:
                Log.e(TAG, "here delete menu item clicked.");
                return true;
            case R.id.rename:
                Log.e(TAG, "here rename menu item clicked.");
                return true;
            case R.id.fileinfo:
                Log.e(TAG, "here file info menu item clicked.");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
