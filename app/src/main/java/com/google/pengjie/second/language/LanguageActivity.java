package com.google.pengjie.second.language;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.pengjie.second.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("here","here in LanguageActivity.onCreate(Bundle)" + (savedInstanceState== null) + "; " + getResources().getConfiguration());
        setContentView(R.layout.activity_test_language);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("here", "here in LanguageActivity.onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_change_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Configuration configuration = getResources().getConfiguration();
        if (item.getItemId() == R.id.english) {
            Locale.setDefault(Locale.ENGLISH);
            //configuration.setLocale(Locale.ENGLISH);
            //getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
            recreate();
            return true;
        } else if (item.getItemId() == R.id.chinese) {
            Locale.setDefault(Locale.CHINESE);
            /*configuration.setLocale(Locale.CHINESE);
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());*/
            recreate();
            return true;
        } else if (item.getItemId() == R.id.french) {
            Locale.setDefault(Locale.FRENCH);
         /*   configuration.setLocale(Locale.FRENCH);
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());*/
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
