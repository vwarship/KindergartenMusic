package com.zaoqibu.zaoqibukindergartenmusic.domain;

import java.io.Serializable;

/**
 * Created by vwarship on 2014/10/21.
 */
public class Sound implements Serializable {
    private String name;
    private String soundPath;
    private String iconPath;

    public Sound(String name, String soundPath, String iconPath) {
        this.name = name;
        this.soundPath = soundPath;
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public String getIconPath() {
        return iconPath;
    }
}
