package com.zaoqibu.zaoqibukindergartenmusic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vwarship on 2014/10/21.
 */
public class Playlist implements Serializable {
    private List<Sound> sounds = new ArrayList<Sound>();
    private int curPlayIndex = 0;

    public void add(Sound sound) {
        sounds.add(sound);
    }

    public Sound getSound(int index) {
        if (index >=0 && index <count()) {
//            curPlayIndex = index;
//            return sounds.get(curPlayIndex);
            return sounds.get(index);
        }

        return null;
    }

    public int count() {
        return sounds.size();
    }

    public Sound next() {
        int nextIndex = curPlayIndex + 1;
        if (nextIndex < sounds.size())
            curPlayIndex = nextIndex;
        else
            curPlayIndex = 0;

        return sounds.get(curPlayIndex);
    }

    public void setCurrentPlayIndex(int index) {
        if (index >= count())
            index = 0;

        curPlayIndex = index;
    }

    public int getCurrentPlayIndex() {
        return curPlayIndex;
    }

    public static Playlist create() {
        Playlist playlist = new Playlist();

        playlist.add(new Sound("拉拉勾", R.raw.upper_kindergarten_01));
        playlist.add(new Sound("我爱你", R.raw.upper_kindergarten_02));
        playlist.add(new Sound("泼水歌", R.raw.upper_kindergarten_03));
        playlist.add(new Sound("司马光砸缸", R.raw.upper_kindergarten_04));
        playlist.add(new Sound("戏说脸谱（京歌）", R.raw.upper_kindergarten_05));
        playlist.add(new Sound("同唱一首歌", R.raw.upper_kindergarten_06));
        playlist.add(new Sound("男儿当自强", R.raw.upper_kindergarten_07));
        playlist.add(new Sound("国旗、国旗红红的哩", R.raw.upper_kindergarten_08));
        playlist.add(new Sound("小海军", R.raw.upper_kindergarten_09));
        playlist.add(new Sound("喜洋洋", R.raw.upper_kindergarten_10));
        playlist.add(new Sound("送我一支玫瑰花", R.raw.upper_kindergarten_11));
        playlist.add(new Sound("龟兔赛跑（京歌）", R.raw.upper_kindergarten_12));
        playlist.add(new Sound("秋天多么美", R.raw.upper_kindergarten_13));
        playlist.add(new Sound("苹果丰收", R.raw.upper_kindergarten_14));
        playlist.add(new Sound("快乐的小鼹鼠", R.raw.upper_kindergarten_15));
        playlist.add(new Sound("化蝶", R.raw.upper_kindergarten_16));
        playlist.add(new Sound("猪八戒吃西瓜", R.raw.upper_kindergarten_17));
        playlist.add(new Sound("吃西瓜", R.raw.upper_kindergarten_18));
        playlist.add(new Sound("百鸟朝凤", R.raw.upper_kindergarten_19));
        playlist.add(new Sound("猴哥", R.raw.upper_kindergarten_20));
        playlist.add(new Sound("猴子看，猴子做", R.raw.upper_kindergarten_21));
        playlist.add(new Sound("一个师傅仨徒弟", R.raw.upper_kindergarten_22));
        playlist.add(new Sound("小老鼠打电话", R.raw.upper_kindergarten_23));
        playlist.add(new Sound("邮递马车", R.raw.upper_kindergarten_24));
        playlist.add(new Sound("打字机", R.raw.upper_kindergarten_25));
        playlist.add(new Sound("雪花飞", R.raw.upper_kindergarten_26));
        playlist.add(new Sound("铃儿响叮当", R.raw.upper_kindergarten_27));
        playlist.add(new Sound("快乐舞会", R.raw.upper_kindergarten_28));
        playlist.add(new Sound("红绸舞", R.raw.upper_kindergarten_29));

        return playlist;
    }
}
