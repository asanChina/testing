package com.google.pengjie.second.fireintent;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.pengjie.second.R;

import java.util.ArrayList;
import java.util.List;

public class FireIntentActivity extends AppCompatActivity {
    private static final String TAG = "fireIntent";

    private ListView listView;
    private List<String> uriList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_intent);

        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "here in position = " + position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(uriList.get(position)));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT > 23) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else {
            ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
            listView.setAdapter(simpleAdapter);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this, R.layout.simple_recycler_view_item, getData());
        listView.setAdapter(simpleAdapter);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    List<String> getData() {
        Uri uri = MediaStore.Files.getContentUri("external");

        //String queryFilter = "media_type = 0 AND mime_type IS NOT NULL AND mime_type NOT LIKE '%image%'AND mime_type NOT LIKE '%video%'AND mime_type NOT LIKE '%audio%'AND mime_type NOT LIKE '%application/vnd.android.package-archive%' AND (_data LIKE '/%') AND media_type=0";
        String queryFilter = "mime_type LIKE '%image%'";
        String[] filterArgs = null;
        Cursor cursor =
                getContentResolver()
                        .query(uri, null, queryFilter, filterArgs, null /*sort*/);
        if (cursor != null) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int idIndex = cursor.getColumnIndex(BaseColumns._ID);
                long id = cursor.getLong(idIndex);
                Uri subUri = ContentUris.withAppendedId(uri, id);
                uriList.add(subUri.toString());
            }
        }
        return uriList;
    }
}
