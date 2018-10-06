package com.google.pengjie.second;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoViewFragement extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("here", "here in NoViewFragment.onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("here", "here in NoViewFragment.onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("here", "here in NoViewFragment.onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("here", "here in NoViewFragment.onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("here", "here in NoViewFragment.onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("here", "here in NoViewFragment.onDestroy");
    }
}
