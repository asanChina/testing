package com.google.pengjie.second.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by pengjie on 6/7/18.
 */

public class TestViewPagerActivity extends AppCompatActivity {
    private static final String PAGER_SET = "pager_set";

    private static final String TAG = "ViewPagerActivity";

    private String[] text = new String[] {"first", "second", "third", "fourth"};
    private boolean pagerSet = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewpager);

        if (savedInstanceState != null) {
            pagerSet = savedInstanceState.getBoolean(PAGER_SET, false);
        }
        //if (!pagerSet) {
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    Log.e(TAG, "here in instantiateItem(ViewGroup container, int position)" + position + "; " + container.getClass().getSimpleName());
                    return super.instantiateItem(container, position);
                }

                @Override
                public Fragment getItem(int position) {
                    Log.e(TAG, "here in getItem(int position)" + position);
                    switch (position) {
                        case 0: return ItemFragment1.newInstance(text[0]);
                        case 1: return ItemFragment2.newInstance(text[1]);
                        case 2: return ItemFragment3.newInstance(text[2]);
                        default: return ItemFragment4.newInstance(text[3]);
                    }
                }


                @Override
                public int getCount() {
                    return text.length;
                }
            });
            pagerSet = true;
        //}
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PAGER_SET, pagerSet);
    }
}
