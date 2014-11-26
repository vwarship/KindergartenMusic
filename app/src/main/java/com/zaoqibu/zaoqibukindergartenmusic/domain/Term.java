package com.zaoqibu.zaoqibukindergartenmusic.domain;

import java.io.Serializable;

/**
 * Created by vwarship on 2014/11/27.
 */
public class Term implements Serializable {
    private String name;
    private int backgroundColorResId;

    private Playlist playlist;

    public Term(String name, int backgroundColorResId) {
        this.name = name;
        this.backgroundColorResId = backgroundColorResId;
    }

    public String getName() {
        return name;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public int getBackgroundColorResId() {
        return backgroundColorResId;
    }
}
