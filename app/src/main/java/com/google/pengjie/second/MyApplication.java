package com.google.pengjie.second;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import java.util.Locale;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("here", "here in MyApplication.onCreate()" + Locale.getDefault());
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("here", "here in MyApplication.onConfigurationChanged(Configuration newConfig)" + Locale.getDefault());
        printLocale(newConfig);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e("here", "here in MyApplication.attachBaseContext(Context)" + Locale.getDefault());
        printLocale(base.getResources().getConfiguration());
    }

    private void printLocale(Configuration configuration) {
        Log.e("here", "here in printLocale(Configuration)" + configuration);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e("here", "here in printLocale: " + configuration.locale);
        } else {
            for (int i = 0; i < configuration.getLocales().size(); ++i) {
                Log.e("here", "here in printLocale-else: " + configuration.getLocales().get(i));
            }
        }
    }
}
