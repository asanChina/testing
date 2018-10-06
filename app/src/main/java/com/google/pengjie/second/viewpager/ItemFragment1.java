package com.google.pengjie.second.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.pengjie.second.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by pengjie on 6/7/18.
 */

public class ItemFragment1 extends Fragment {
    private static String TAG = "ItemFragment";

    private static final String TEXT_KEY = "text_key";
    private String text;

    public static ItemFragment1 newInstance(String text) {
        ItemFragment1 itemFragment = new ItemFragment1();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_KEY, text);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString(TEXT_KEY);
        Log.e(TAG, "here in ItemFragment.onCreate, " + text + "===" + this.toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_recycler_view_item, container, false);
        ((TextView) view).setText(text);
        Log.e(TAG, "here in ItemFragment.onCreateView, " + text + "===" + this.toString());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "here in ItemFragment.onDestroyView, " + text + "===" + this.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "here in ItemFragment.onDestroy, " + text + "===" + this.toString());
    }
}
