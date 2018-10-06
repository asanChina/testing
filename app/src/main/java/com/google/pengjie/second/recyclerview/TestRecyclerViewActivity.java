package com.google.pengjie.second.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.pengjie.second.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by pengjie on 6/14/18.
 */

public class TestRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView tvTopBar;

    /** 浮动标题的高度 */
    private int mTopBarHeight = 0;
    /** 当前可见的第一个item的下标 */
    private int mCurrentPosition = 0;
    /** 线性管理器 */
    private LinearLayoutManager linearLayoutManager;
    /** 数据源 */
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_view);

        /**查找组件*/
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvTopBar = (TextView) findViewById(R.id.tvTop);

        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);//设置线性显示
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//设置线性宫格显示，相似于gridView
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.HORIZONTAL));//线性宫格布局，类似于瀑布流
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("选项===>>" + i);
        }
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(this, mList));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取上浮title的高度
                mTopBarHeight = tvTopBar.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //当滑动的时候获取当前滑动的下一个item的视图
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view == null) {
                    return;
                }
                //当view距离手机顶端的top值小于等于浮动title的高度的时候
                if (view.getTop() <= mTopBarHeight) {
                    //浮动标题的高度-view距离top的距离，计算出view和浮动层的交集部分，
                    //然后设置浮动view的top值，保证滑动的view始终是在浮动层的下方
                    tvTopBar.setY(-(mTopBarHeight - view.getTop()));
                } else {
                    tvTopBar.setY(0);
                }

                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    tvTopBar.setY(0);
                    tvTopBar.setText(mList.get(mCurrentPosition));
                }
            }
        });
    }
}
