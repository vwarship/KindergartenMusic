package com.zaoqibu.zaoqibukindergartenmusic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Sound;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vwarship on 2014/10/23.
 */
public class PlaylistAdapter extends BaseAdapter {
    private Context context;
    private Player player;

    public PlaylistAdapter(Context context, Player player) {
        this.context = context;
        this.player = player;
    }

    @Override
    public int getCount() {
        return player.getPlaylist().count();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = null;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.activity_playlist_item, parent, false);
        }
        else {
            view = convertView;
        }

        Sound sound = player.getPlaylist().getSound(position);

        ImageView icon = (ImageView)view.findViewById(R.id.playlistItemIcon);
        TextView tvIconText = (TextView)view.findViewById(R.id.iconText);
        tvIconText.setText("");

        try {
            InputStream inputStream = context.getAssets().open(sound.getIconPath());
            icon.setImageDrawable(Drawable.createFromStream(inputStream, null));
        } catch (IOException e) {
            e.printStackTrace();
            icon.setImageResource(R.drawable.sound_default_icon);

            tvIconText.setText(sound.getName().substring(0,1));
        }

        TextView name = (TextView)view.findViewById(R.id.playlistItemName);
        name.setText(sound.getName());

        if (position == player.getCurrentPosition()) {
            view.setBackgroundColor(context.getResources().getColor(R.color.current_playing_on));
        }
        else {
            view.setBackgroundColor(context.getResources().getColor(R.color.current_playing_off));
        }

        return view;
    }
}
