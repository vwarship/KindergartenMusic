package com.zaoqibu.zaoqibukindergartenmusic.util;

import android.content.Context;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Playlist;
import com.zaoqibu.zaoqibukindergartenmusic.R;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Sound;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Term;

/**
 * Created by vwarship on 2014/11/27.
 */
public class TermFactory {
    private Context context;

    public TermFactory(Context context) {
        this.context = context;
    }

    public Term createTermWithNursery1() {
        Term term = new Term("小班·上", R.drawable.term_item_nursery34_1_selector, R.drawable.nursery34_1);
        term.setPlaylist(createPlaylist(R.array.Nursery34_1, "Nursery34_1"));

        return term;
    }

    private Playlist createPlaylist(int StringArrayResId, String dir) {
        Playlist playlist = new Playlist();

        String[] names = context.getResources().getStringArray(StringArrayResId);
        for (int i=0; i<names.length; ++i) {
            String name = names[i];
            String soundPath = String.format("%s/sound/%02d.mp3", dir, i+1);
            String iconPath = String.format("%s/icon/%02d.png", dir, i+1);

            playlist.add(new Sound(name, soundPath, iconPath));
        }

        return playlist;
    }

    public Term createTermWithNursery2() {
        Term term = new Term("小班·下", R.drawable.term_item_nursery34_2_selector, R.drawable.nursery34_2);
        term.setPlaylist(createPlaylist(R.array.Nursery34_2, "Nursery34_2"));

        return term;
    }

    public Term createTermWithLowerKindergarten1() {
        Term term = new Term("中班·上", R.drawable.term_item_lowerkindergarten45_1_selector, R.drawable.lowerkindergarten45_1);
        term.setPlaylist(createPlaylist(R.array.LowerKindergarten45_1, "LowerKindergarten45_1"));

        return term;
    }

    public Term createTermWithLowerKindergarten2() {
        Term term = new Term("中班·下", R.drawable.term_item_lowerkindergarten45_2_selector, R.drawable.lowerkindergarten45_2);
        term.setPlaylist(createPlaylist(R.array.LowerKindergarten45_2, "LowerKindergarten45_2"));

        return term;
    }

    public Term createTermWithUpperKindergarten1() {
        Term term = new Term("大班·上", R.drawable.term_item_upperkindergarten56_1_selector, R.drawable.upperkindergarten56_1);
        term.setPlaylist(createPlaylist(R.array.UpperKindergarten56_1, "UpperKindergarten56_1"));

        return term;
    }

    public Term createTermWithUpperKindergarten2() {
        Term term = new Term("大班·下", R.drawable.term_item_upperkindergarten56_2_selector, R.drawable.upperkindergarten56_2);
        term.setPlaylist(createPlaylist(R.array.UpperKindergarten56_2, "UpperKindergarten56_2"));

        return term;
    }

}
