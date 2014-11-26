package com.zaoqibu.zaoqibukindergartenmusic.domain;

import java.io.Serializable;

/**
 * Created by vwarship on 2014/10/21.
 */
public class Sound implements Serializable {
    private String name;
    private String path;

    public Sound(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
