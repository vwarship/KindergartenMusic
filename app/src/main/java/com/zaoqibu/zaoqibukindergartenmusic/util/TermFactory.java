package com.zaoqibu.zaoqibukindergartenmusic.util;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Playlist;
import com.zaoqibu.zaoqibukindergartenmusic.R;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Sound;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Term;

/**
 * Created by vwarship on 2014/11/27.
 */
public class TermFactory {
    static public Term createTermWithNursery1() {
        Term term = new Term("小班·上", R.color.nursery_term_background_color);

        Playlist playlist = new Playlist();
        playlist.add(new Sound("两只小小鸭", "Nursery34_1/01.mp3"));
        playlist.add(new Sound("鱼儿好朋友", "Nursery34_1/02.mp3"));

        term.setPlaylist(playlist);

        return term;
    }

    static public Term createTermWithNursery2() {
        Term term = new Term("小班·下", R.color.nursery_term_background_color);

        Playlist playlist = new Playlist();
        playlist.add(new Sound("我家有几口", "Nursery34_2/01.mp3"));
        playlist.add(new Sound("睡吧，布娃娃", "Nursery34_2/02.mp3"));

        term.setPlaylist(playlist);

        return term;
    }

    static public Term createTermWithLowerKindergarten1() {
        Term term = new Term("中班·上", R.color.lower_kindergarten_term_background_color);

        Playlist playlist = new Playlist();
        playlist.add(new Sound("太阳小鸟夸奖我", "LowerKindergarten45_1/01.mp3"));
        playlist.add(new Sound("好朋友", "LowerKindergarten45_1/02.mp3"));

        term.setPlaylist(playlist);

        return term;
    }

    static public Term createTermWithLowerKindergarten2() {
        Term term = new Term("中班·下", R.color.lower_kindergarten_term_background_color);

        Playlist playlist = new Playlist();
        playlist.add(new Sound("小小粉刷匠", "LowerKindergarten45_2/01.mp3"));
        playlist.add(new Sound("小小粉刷匠（伴奏）", "LowerKindergarten45_2/02.mp3"));

        term.setPlaylist(playlist);

        return term;
    }

    static public Term createTermWithUpperKindergarten1() {
        Term term = new Term("大班·上", R.color.upper_kindergarten_term_background_color);

        Playlist playlist = new Playlist();
        playlist.add(new Sound("拉拉勾", "UpperKindergarten56_1/01.mp3"));
        playlist.add(new Sound("我爱你", "UpperKindergarten56_1/02.mp3"));

        term.setPlaylist(playlist);

        return term;
    }

    static public Term createTermWithUpperKindergarten2() {
        Term term = new Term("大班·下", R.color.upper_kindergarten_term_background_color);

        Playlist playlist = new Playlist();
        playlist.add(new Sound("闹花灯", "UpperKindergarten56_2/01.mp3"));
        playlist.add(new Sound("游子吟", "UpperKindergarten56_2/02.mp3"));

        term.setPlaylist(playlist);

        return term;
    }

}
