package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Playlist;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Terms;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class TermActivity extends Activity {
    public static final String ARG_TERMS = "terms";
    public static final String ARG_POSITION = "position";

    private Terms terms;
    private int position;

    private Player player;
    private Playlist playlist;
    private ListView playlistView;

    private ImageButton playButton;
    private ImageButton playPreviousButton;
    private ImageButton playNextButton;

    private boolean isSequencePlay = true;

    private long bedtimePlayBeginTime = 0;

    private MenuItem menuItemBedtimePlay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        terms = (Terms)getIntent().getExtras().get(ARG_TERMS);
        position = getIntent().getExtras().getInt(ARG_POSITION);

        String termName = terms.getTerm(position).getName();
        getActionBar().setTitle(termName);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        playlist = terms.getTerm(position).getPlaylist();

        player = new Player();
        player.setContext(this);
        player.setPlaylist(playlist);

        initPlaylistView();
        initPlayButton();
        initPlayPrevious();
        initPlayNext();

        player.setOnCompletionListenerWithMediaPlayer(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (playlist != null) {
                    if (bedtimePlayBeginTime > 0 && (Calendar.getInstance().getTimeInMillis() - bedtimePlayBeginTime) > getBedtimePlayTimeMillis()) {
                        playButton.setImageResource(R.drawable.ic_action_play);
                        player.pause();

                        menuItemBedtimePlay.setIcon(R.drawable.ic_action_bedtime_play);
                        bedtimePlayBeginTime = 0;
                        return;
                    }

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("term", terms.getTerm(position).getName());
                    map.put("sound_name", playlist.getSound(player.getCurrentPosition()).getName());
                    MobclickAgent.onEvent(TermActivity.this, "played", map);

                    if (isSequencePlay)
                        playSoundByPosition(player.nextPlayIndex());
                    else
                        playSoundByPosition(player.getCurrentPosition());
                }
            }
        });

        playSoundByPosition(getCurrentPositionWithPlaylist(termName));
    }

    private void initPlaylistView() {
        playlistView = (ListView)findViewById(R.id.playlistView);
        playlistView.setAdapter(new PlaylistAdapter(this, player));

        playlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playSoundByPosition(position);

                Map<String, String> map = new HashMap<String, String>();
                map.put("term", terms.getTerm(position).getName());
                map.put("sound_name", playlist.getSound(player.getCurrentPosition()).getName());
                MobclickAgent.onEvent(TermActivity.this, "play_list_item_onclick", map);
            }
        });
    }

    private void setCurrentPlaying(View view) {
        offAllCurrentPlayingWithPlaylistView();
        onCurrentPlayingWithPlaylistView(view);
    }

    private void offAllCurrentPlayingWithPlaylistView() {
        int count = playlistView.getChildCount();
        for (int i=0; i<count; ++i) {
            View view = playlistView.getChildAt(i);
            view.setBackgroundColor(getResources().getColor(R.color.current_playing_off));
        }
    }

    private void onCurrentPlayingWithPlaylistView(View view) {
        if (view != null) {
            view.setBackgroundColor(getResources().getColor(R.color.current_playing_on));
        }
    }

    private void playSoundByPosition(int position) {
        View view = getViewByPosition(playlistView, position);
        setCurrentPlaying(view);
        playlistView.setSelection(position);

        playButton.setImageResource(R.drawable.ic_action_pause);
        player.play(position);
    }

    private View getViewByPosition(ListView listView, int pos) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void initPlayButton() {
        playButton = (ImageButton) findViewById(R.id.playlistItemPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayClickListener();
            }
        });
    }

    private void onPlayClickListener() {
        setPlayImage();

        if (player.isPlaying()) {
            player.pause();
            MobclickAgent.onEvent(this, "pause");
        }
        else {
            player.play();
            MobclickAgent.onEvent(this, "play");
        }
    }

    private void initPlayPrevious() {
        playPreviousButton = (ImageButton) findViewById(R.id.playlistItemPrevious);
        playPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundByPosition(player.previousPlayIndex());
                MobclickAgent.onEvent(TermActivity.this, "play_previous");
            }
        });
    }

    private void initPlayNext() {
        playNextButton = (ImageButton) findViewById(R.id.playlistItemNext);
        playNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundByPosition(player.nextPlayIndex());
                MobclickAgent.onEvent(TermActivity.this, "play_next");
            }
        });
    }

    private int getCurrentPositionWithPlaylist(String playlistName) {
        SharedPreferences prefs = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        return prefs.getInt(playlistName, 0);
    }

    private void setPlayImage() {
        playButton.setImageResource(player.isPlaying() ? R.drawable.ic_action_play : R.drawable.ic_action_pause);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        setCurrentPositionWithPlaylist(terms.getTerm(position).getName(), player.getCurrentPosition());
        player.close();
    }

    private void setCurrentPositionWithPlaylist(String playlistName, int currentPosition) {
        SharedPreferences prefs = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putInt(playlistName, currentPosition);
        prefsEditor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.term, menu);
        return true;
    }

    private final static String TAG = "TermActivity";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_repeat) {
            MobclickAgent.onEvent(this, isSequencePlay ? "repeat" : "repeat_one");

            item.setIcon(isSequencePlay ? R.drawable.ic_action_repeat_one : R.drawable.ic_action_repeat);
            isSequencePlay = !isSequencePlay;
        }
        else if (id == R.id.action_bedtime_paly) {
            menuItemBedtimePlay = item;
            String toastText;

            if (bedtimePlayBeginTime == 0) {
                menuItemBedtimePlay.setIcon(R.drawable.ic_action_bedtime_playing);

                bedtimePlayBeginTime = Calendar.getInstance().getTimeInMillis();
                toastText = String.format("睡前播放 %d 分钟", getBedtimePlayTimeMinute());
                MobclickAgent.onEvent(this, "bedtime_play");
            }
            else {
                menuItemBedtimePlay.setIcon(R.drawable.ic_action_bedtime_play);

                bedtimePlayBeginTime = 0;
                toastText = "取消睡前播放设置";
                MobclickAgent.onEvent(this, "bedtime_play_cancel");
            }
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();

            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getBedtimePlayTimeMinute() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return Integer.parseInt(prefs.getString("bedtime_play_time", "30"));
    }

    private int getBedtimePlayTimeMillis() {
        return getBedtimePlayTimeMinute() * 60 * 1000;
    }

}
