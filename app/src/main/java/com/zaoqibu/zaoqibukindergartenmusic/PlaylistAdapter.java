package com.zaoqibu.zaoqibukindergartenmusic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vwarship on 2014/10/23.
 */
public class PlaylistAdapter extends BaseAdapter {
    private Context context;
    private Playlist playlist;

    public PlaylistAdapter(Context context, Playlist playlist) {
        this.context = context;
        this.playlist = playlist;
    }

    @Override
    public int getCount() {
        return playlist.count();
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

        Sound sound = playlist.getSound(position);
        TextView name = (TextView)view.findViewById(R.id.playlistItemName);
        name.setText(sound.getName());

        ImageView currentPlaying = (ImageView)view.findViewById(R.id.currentPlaying);
        if (position == playlist.getCurrentPlayIndex()) {
            currentPlaying.setBackgroundColor(context.getResources().getColor(R.color.current_playing_on));
        }
        else {
            currentPlaying.setBackgroundColor(context.getResources().getColor(R.color.current_playing_off));
        }

        return view;
    }
}
