package com.google.pengjie.second.mediaplayer.simpleaudio;

import android.content.ContentUris;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.pengjie.second.R;

import java.io.IOException;

/**
 * Created by pengjie on 3/8/18.
 */

public class SimpleAudioPlayerActivity extends AppCompatActivity {
    private static final String TAG = SimpleAudioPlayerActivity.class.getSimpleName();

    private TextView textView;
    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_audio_player);

        textView = (TextView) findViewById(R.id.text_view);
        videoView = findViewById(R.id.video_view);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            public void run() {

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
                Cursor cursor = getContentResolver().query(uri, projection, selection, new String[]{"example.amr"}, null);
                cursor.moveToNext();


                int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                Uri audio = ContentUris.withAppendedId(uri, cursor.getLong(idIndex));
                final MediaPlayer mediaPlayer = new MediaPlayer();
                try

                {
                    mediaPlayer.setDataSource(SimpleAudioPlayerActivity.this, audio);
                    mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Log.e(TAG, "here in onError: " + what);
                            return false;
                        }
                    });
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                    mediaPlayer.prepare();
                } catch (IOException ioe) {

                }
            }
        }).start();
    }
}
