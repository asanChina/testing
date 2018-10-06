package com.google.pengjie.second.coordinator.bottombar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.pengjie.second.R;
import com.google.pengjie.second.recyclerview.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by pengjie on 8/10/17.
 */

public class BottomBarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_bottom_bar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, init());
        recyclerView.setAdapter(simpleAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleAdapter.add("zhang");
            }
        });
    }

    private List<String> init() {
        List<String> data = new ArrayList<>();
            for (int i = 0; i < 200; ++i) {
                data.add("zhang pengjie" + i);
            }
        return data;
    }
}
