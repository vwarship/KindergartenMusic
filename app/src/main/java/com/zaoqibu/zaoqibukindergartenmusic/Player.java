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
    private final static String TAG = "MediaPlayer";

    private MediaPlayer mediaPlayer;

    private Context context;
    private Playlist playlist;

    private int currentPosition = 0;

    public Player() {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
    }

    public void setOnCompletionListenerWithMediaPlayer(MediaPlayer.OnCompletionListener listener) {
        mediaPlayer.setOnCompletionListener(listener);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void play(int position) {
        if (position<0 || position>=playlist.count())
            return;

        currentPosition = position;
        Sound sound = playlist.getSound(currentPosition);

        if (sound != null)
            play(sound);
    }

    private void play(Sound sound) {
        mediaPlayer.reset();

        if (sound == null) {
            return;
        }

        try {
            //AssetFileDescriptor afd = context.getResources().openRawResourceFd(sound.getFilenameResId());
            AssetFileDescriptor afd = context.getAssets().openFd(sound.getSoundPath());
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

    public int previousPlayIndex() {
        int previousIndex = currentPosition - 1;
        if (previousIndex < 0)
            previousIndex = playlist.count() - 1;

        return previousIndex;
    }

    public int nextPlayIndex() {
        int nextIndex = currentPosition + 1;
        if (nextIndex >= playlist.count())
            nextIndex = 0;

        return nextIndex;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

}
