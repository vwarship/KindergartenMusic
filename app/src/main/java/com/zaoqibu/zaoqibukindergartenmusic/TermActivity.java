package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Playlist;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Terms;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        terms = (Terms)getIntent().getExtras().get(ARG_TERMS);
        position = getIntent().getExtras().getInt(ARG_POSITION);

        getActionBar().setTitle(terms.getTerm(position).getName());

        playlist = terms.getTerm(position).getPlaylist();

        initPlaylistView();
        initPlayButton();
        initPlayPrevious();
        initPlayNext();

        player = new Player();//Player.getInstance();
        player.setContext(this);
        player.setPlaylist(playlist);

        playSoundByPosition(0);
    }

    private void initPlaylistView() {
        playlistView = (ListView)findViewById(R.id.playlistView);
        playlistView.setAdapter(new PlaylistAdapter(this, playlist));

        playlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playSoundByPosition(position);
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
            ImageView currentPlaying = (ImageView)view.findViewById(R.id.currentPlaying);
            currentPlaying.setBackgroundColor(getResources().getColor(R.color.current_playing_off));
        }
    }

    private void onCurrentPlayingWithPlaylistView(View view) {
        if (view != null) {
            ImageView currentPlaying = (ImageView) view.findViewById(R.id.currentPlaying);
            currentPlaying.setBackgroundColor(getResources().getColor(R.color.current_playing_on));
        }
    }

    private void playSoundByPosition(int position) {
        View view = getViewByPosition(playlistView, position);
        setCurrentPlaying(view);

        player.play(playlist.getSound(position));
        playlist.setCurrentPlayIndex(position);

        setPlayImage();
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
                if (player.isPlaying()) {
                    player.pause();
                }
                else {
                    player.play();
                }

                setPlayImage();
            }
        });
    }

    private void initPlayPrevious() {
        playPreviousButton = (ImageButton) findViewById(R.id.playlistItemPrevious);
        playPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundByPosition(previousPlayIndex());
            }
        });
    }

    private int previousPlayIndex() {
        int previousIndex = playlist.getCurrentPlayIndex() - 1;
        if (previousIndex < 0)
            previousIndex = playlist.count() - 1;

        return previousIndex;
    }

    private void initPlayNext() {
        playNextButton = (ImageButton) findViewById(R.id.playlistItemNext);
        playNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSoundByPosition(nextPlayIndex());
            }
        });
    }

    private int nextPlayIndex() {
        int nextIndex = playlist.getCurrentPlayIndex() + 1;
        if (nextIndex >= playlist.count())
            nextIndex = 0;

        return nextIndex;
    }

    private void setPlayImage() {
        playButton.setImageResource(player.isPlaying() ? R.drawable.ic_action_pause : R.drawable.ic_action_play);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        player.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.term, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
