package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.io.IOException;


public class PlaylistItemDetailActivity extends Activity {
    public static final String ARG_PLAYLIST = "playlist";
    public static final String ARG_POSITION = "position";

    private ImageButton play;
    private SeekBar seekBar;

    private Playlist playlist;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_item_detail);

        play = (ImageButton)findViewById(R.id.playlistItemPlay);
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        playlist = (Playlist)getIntent().getExtras().get(ARG_PLAYLIST);
        position = getIntent().getIntExtra(ARG_POSITION, 0);

        getActionBar().setTitle(playlist.getSound(position).getName());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Player.getInstance().play(playlist.getSound(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playlist_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
