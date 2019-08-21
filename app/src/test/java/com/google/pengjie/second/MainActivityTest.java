package com.google.pengjie.second;

import android.os.Environment;
import android.util.Log;

import com.google.pengjie.second.language.LanguageActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.junit.runner.RunWith;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.File;


@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowFoo.class})
public class MainActivityTest {

    private ActivityController<MainActivity> activityController;

    @Before
    public void setup() {
        ShadowLog.stream = System.out;
        activityController = Robolectric.buildActivity(MainActivity.class);
        activityController.setup();
        Robolectric.flushForegroundThreadScheduler();
    }

    @Test
    public void buttonClick() {
        Log.e("here","here in buttonClick()" + Thread.currentThread().getName());
        Foo foo = new Foo();
        foo.display();
        File file = new File(Environment.getExternalStorageDirectory(), "haha");
        file.mkdir();
        File file1 = new File(Environment.getExternalStorageDirectory(), "章 鹏杰");
        file1.mkdir();
    }
}