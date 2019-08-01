package com.google.pengjie.second;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("here", "here in SecondFragment.onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(container.getContext());
        textView.setText("I am a sde working in Google");
        Log.e("here", "here in SecondFragment.onCreateView");
        return textView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("here", "here in SecondFragment.onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("here", "here in SecondFragment.onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("here", "here in SecondFragment.onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("here", "here in SecondFragment.onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("here", "here in SecondFragment.onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("here", "here in SecondFragment.onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("here", "here in SecondFragment.onDestroy");
    }
}
