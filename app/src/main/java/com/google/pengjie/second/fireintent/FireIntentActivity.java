package com.google.pengjie.second.fireintent;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.pengjie.second.R;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FireIntentActivity extends AppCompatActivity {
    private static final String TAG = "fireIntent";

    private ListView listView;
    private List<String> uriList = new ArrayList<>();
    private boolean excuted = false;



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
                intent.setDataAndType(Uri.parse("https://www.youtube.com/watch?v=GBX4Di4XTag"), "video/*");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT > 23) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            if (!excuted) {
                excuted = true;
                new UriTask().execute();

            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!excuted) {
            excuted = true;
            new UriTask().execute();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    List<String> getData() {
        Uri uri = MediaStore.Files.getContentUri("external");

        //String queryFilter = "media_type = 0 AND mime_type IS NOT NULL AND mime_type NOT LIKE '%image%'AND mime_type NOT LIKE '%video%'AND mime_type NOT LIKE '%audio%'AND mime_type NOT LIKE '%application/vnd.android.package-archive%' AND (_data LIKE '/%') AND media_type=0";
        String queryFilter = "mime_type LIKE '%image%'";
        String[] filterArgs = null;
        Cursor cursor =
                getContentResolver()
                        .query(uri, new String[]{BaseColumns._ID}, queryFilter, filterArgs, null /*sort*/);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int idIndex = cursor.getColumnIndex(BaseColumns._ID);
                long id = cursor.getLong(idIndex);
                Uri subUri = ContentUris.withAppendedId(uri, id);
                uriList.add(subUri.toString());
            }

        }
        return uriList;
    }

    private final class UriTask extends AsyncTask<Void, Integer, List<String>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(FireIntentActivity.this, android.R.layout.simple_list_item_1, strings);
            listView.setAdapter(simpleAdapter);
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            return getData();
        }
    }
}
