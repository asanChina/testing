package com.google.pengjie.second.customview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.pengjie.second.R;

public class ViewPagerIndicatorActivity extends AppCompatActivity {

    private ViewPagerIndicator myTabIndicator;
    private ViewPager viewPager;
    private TextView[] textViews = new TextView[5];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager_indicator);
        init();

        myTabIndicator = findViewById(R.id.view_pager_indicator);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return textViews.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(textViews[position]);
                return textViews[position];
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(textViews[position]);
            }
        });
        myTabIndicator.setViewPager(viewPager, 0);
    }

    private void init() {
        for (int i = 0; i < textViews.length; ++i) {
            textViews[i] = new TextView(this);
            textViews[i].setText("zhang" + i);
        }
    }
}
