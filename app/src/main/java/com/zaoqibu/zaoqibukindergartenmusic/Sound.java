package com.zaoqibu.zaoqibukindergartenmusic;

import java.io.Serializable;

/**
 * Created by vwarship on 2014/10/21.
 */
public class Sound implements Serializable {
    private String name;
    private int filenameResId;

    public Sound(String name, int filenameResId) {
        this.name = name;
        this.filenameResId = filenameResId;
    }

    public String getName() {
        return name;
    }

    public int getFilenameResId() {
        return filenameResId;
    }
}
