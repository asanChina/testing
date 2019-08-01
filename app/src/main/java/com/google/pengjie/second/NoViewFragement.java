package com.google.pengjie.second;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoViewFragement extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("here", "here in NoViewFragment.onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("here", "here in NoViewFragment.onCreateView");
        Button button = new Button(this.getContext());
        button.setText("Nimei");
        return button;
        //return super.onCreateView(inflater, container, savedInstanceState);
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
