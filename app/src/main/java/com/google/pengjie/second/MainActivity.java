package com.google.pengjie.second;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.google.pengjie.second.coordinator.TestCoordinatorLayout;
import com.google.pengjie.second.customview.TestCustomViewActivity;
import com.google.pengjie.second.dialog.TestDialogActivity;
import com.google.pengjie.second.drawable.TestDrawableActivity;
import com.google.pengjie.second.featurehighlight.TestFeatureHighlightActivity;
import com.google.pengjie.second.fireintent.FireIntentActivity;
import com.google.pengjie.second.fragment.TestFragmentActivity;
import com.google.pengjie.second.language.LanguageActivity;
import com.google.pengjie.second.launchmode.TestHandleIntentActivity;
import com.google.pengjie.second.material.TestMaterialFeatureHighlightActivity;
import com.google.pengjie.second.materialdesign.TestMaterialDesignActivity;
import com.google.pengjie.second.menu.TestMenuActivity;
import com.google.pengjie.second.popupwindow.TestPopupWindowActivity;
import com.google.pengjie.second.progressbar.TestProgressBarActivity;
import com.google.pengjie.second.recyclerview.TestRecyclerViewActivity;
import com.google.pengjie.second.viewpager.TestViewPagerActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button testCoordinatorLayout;
    private Button testCustomView;
    private Button testFeatureHighlight;
    private Button testMenu;
    private Button testMaterialFeatureHighlight;
    private Button testPopupWindow;
    private Button testDialog;
    private Button testViewPager;
    private Button testRecyclerView;
    private Button testFireIntent;
    private Button testTransparentActivity;
    private Button testHandleIntent;
    private Button testDrawable;
    private Button testMaterialDesignComponents;
    private Button testProgressBar;
    private Button testLanguage;
    private Button testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("here", "here in MainActivity.onCreate(Bundle)" + (savedInstanceState == null) + "; " + getResources().getConfiguration());


        setContentView(R.layout.activity_main);

        testCoordinatorLayout = (Button) findViewById(R.id.button_test_coordinator_layout);
        testCustomView = (Button) findViewById(R.id.button_test_custom_view);
        testFeatureHighlight = (Button) findViewById(R.id.button_test_feature_highlight);
        testMenu = (Button) findViewById(R.id.button_test_menu);
        testMaterialFeatureHighlight = (Button) findViewById(R.id.button_test_material_feature_highlight);
        testPopupWindow = (Button) findViewById(R.id.button_test_popup_window);
        testDialog = (Button) findViewById(R.id.button_test_dialog);
        testViewPager =  findViewById(R.id.button_test_view_pager);
        testRecyclerView = findViewById(R.id.button_test_recycler_view);
        testFireIntent = findViewById(R.id.button_test_fire_intent);
        testTransparentActivity = findViewById(R.id.button_test_transparent_activity);
        testHandleIntent = findViewById(R.id.button_test_handle_intent);
        testDrawable = findViewById(R.id.button_test_drawable);
        testMaterialDesignComponents = findViewById(R.id.button_test_material_design_components);
        testProgressBar = findViewById(R.id.button_test_progress_bar);
        testLanguage = findViewById(R.id.button_test_language);
        testFragment = findViewById(R.id.button_test_fragment);

        testCoordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestCoordinatorLayout.class));
            }
        });
        testCustomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestCustomViewActivity.class));
            }
        });
        testFeatureHighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestFeatureHighlightActivity.class));
            }
        });
        testMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestMenuActivity.class));
            }
        });
        testMaterialFeatureHighlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestMaterialFeatureHighlightActivity.class));
            }
        });
        testPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestPopupWindowActivity.class));
            }
        });
        testDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestDialogActivity.class));
            }
        });
        testViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestViewPagerActivity.class));
            }
        });
        testRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestRecyclerViewActivity.class));
            }
        });
        testFireIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FireIntentActivity.class));
            }
        });
        testTransparentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransparentActivity.class));
            }
        });
        testHandleIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestHandleIntentActivity.class));
            }
        });

        testDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestDrawableActivity.class));
            }
        });
        testMaterialDesignComponents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestMaterialDesignActivity.class));
            }
        });
        testProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestProgressBarActivity.class));
            }
        });
        testLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("here", "here in MainActivity.onCreate, " + Thread.currentThread().getName());
                startActivity(new Intent(MainActivity.this, LanguageActivity.class));
            }
        });
        testFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TestFragmentActivity.class));
            }
        });
        //getDelegate().applyDayNight();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("here", "here in MainActivity.onStart(), Locale.getDefault() = " + Locale.getDefault() + "; " + getResources().getConfiguration().getLocales().get(0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("here", "here in MainActivity.onResume()");
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;
        Log.e("here", "here in onResume(), dpWidth = " + dpWidth + "; dpHeight = " + dpHeight);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("here", "here in MainActivity.onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("here", "here in MainActivity.onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("here", "here in MainActivity.onDestroy");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.e("here", "here in MainActivity.attachBaseContext");
    }
}
