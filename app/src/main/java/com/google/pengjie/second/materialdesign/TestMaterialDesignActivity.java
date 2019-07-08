package com.google.pengjie.second.materialdesign;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.pengjie.second.R;

public class TestMaterialDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_material_design_components);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(new TestMaterialDesignFragment(), "").commitNow();
        }

        ((NavigationView)findViewById(R.id.navigation_view)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.settings) {

                }
                return false;
            }
        });
    }
}
