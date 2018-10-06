package com.google.pengjie.second;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TransparentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("here", "here in TransparentFragment.onResume()");
        getChildFragmentManager().beginTransaction().add(new NoViewFragement(), "TAG").commitAllowingStateLoss();
    }

    @Override
    public void onPause() {
        Log.e("here", "here in TransparentFragment.onPause()");
        super.onPause();
    }


    @Override
    public void onStop() {
        Log.e("here", "here in TransparentFragment.onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("here", "here in TransparentFragment.onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("here", "here in TransparentFragment.onDestroy()");
    }
}
