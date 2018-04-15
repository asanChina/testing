package com.google.pengjie.second.mediaplayer.exoplayer;

import android.content.ContentUris;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.ContentDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.pengjie.second.R;
import com.google.pengjie.second.mediaplayer.simpleaudio.SimpleAudioPlayerActivity;

import java.io.IOException;

/**
 * Created by pengjie on 3/8/18.
 */

public class ExoPlayerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer);


    }


    @Override
    protected void onResume() {
        super.onResume();


                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                String[] projection = {
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.COMPOSER,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.DATE_ADDED,
                        MediaStore.Audio.Media.DATE_MODIFIED,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.MIME_TYPE,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.YEAR,
                };
                String selection = MediaStore.Audio.Media.DISPLAY_NAME + " = ?";
                Cursor cursor = getContentResolver().query(uri, projection, selection, new String[]{"SampleAudio_0.4mb.mp3"}, null);
                cursor.moveToNext();


                int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                Uri audio = ContentUris.withAppendedId(uri, cursor.getLong(idIndex));

        SimpleExoPlayer simpleExoPlayer =
                ExoPlayerFactory.newSimpleInstance(
                        new DefaultRenderersFactory(this),
                        new DefaultTrackSelector(),
                        new DefaultLoadControl());

        ExtractorMediaSource.Factory factory =
                new ExtractorMediaSource.Factory(new DataSource.Factory() {
                    @Override
                    public DataSource createDataSource() {
                        return new ContentDataSource(ExoPlayerActivity.this);
                    }
                });
        MediaSource mediaSource = factory.createMediaSource(audio);
        simpleExoPlayer.prepare(mediaSource,  true,  false);
        simpleExoPlayer.setPlayWhenReady(true);
    }
}
