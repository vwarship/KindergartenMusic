package com.zaoqibu.zaoqibukindergartenmusic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vwarship on 2014/10/21.
 */
public class Playlist implements Serializable {
    private List<Sound> sounds = new ArrayList<Sound>();

    public void add(Sound sound) {
        sounds.add(sound);
    }

    public Sound getSound(int index) {
        if (index >=0 && index <count()) {
            return sounds.get(index);
        }

        return null;
    }

    public int count() {
        return sounds.size();
    }

}
