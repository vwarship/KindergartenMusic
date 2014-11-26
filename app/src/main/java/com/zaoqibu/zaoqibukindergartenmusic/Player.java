package com.zaoqibu.zaoqibukindergartenmusic;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Playlist;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Sound;

import java.io.IOException;

/**
 * Created by vwarship on 2014/10/23.
 */
public class Player {
    private Context context;
    private Playlist playlist;

    private MediaPlayer mediaPlayer;
    private final static String TAG = "MediaPlayer";

    public Player() {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (playlist != null)
                    play(playlist.next());
            }
        });
    }

    public void play(Sound sound) {
        mediaPlayer.reset();

        if (sound == null) {
            return;
        }

        try {
//            AssetFileDescriptor afd = context.getResources().openRawResourceFd(sound.getFilenameResId());
            AssetFileDescriptor afd = context.getAssets().openFd(sound.getPath());
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();

            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (IOException ex) {
            Log.e(TAG, "play failed:", ex);
        }
    }

    public void play() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void close() {
        mediaPlayer.stop();
        mediaPlayer.release();

        mediaPlayer = null;
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    private volatile static Player player;
    public static Player getInstance() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null)
                    player = new Player();
            }
        }

        return player;
    }

}
