package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Terms;
import com.zaoqibu.zaoqibukindergartenmusic.util.GridViewUtil;
import com.zaoqibu.zaoqibukindergartenmusic.util.TermFactory;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    public static final String SHARED_PREFERENCES_NAME = "com.zaoqibu.zaoqibukindergartenmusic_preferences";
    private static final String TAG = "MainActivity";

    private Terms terms = new Terms();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TermFactory termFactory = new TermFactory(this);
        terms.addTerm(termFactory.createTermWithNursery1());
        terms.addTerm(termFactory.createTermWithNursery2());
        terms.addTerm(termFactory.createTermWithLowerKindergarten1());
        terms.addTerm(termFactory.createTermWithLowerKindergarten2());
        terms.addTerm(termFactory.createTermWithUpperKindergarten1());
        terms.addTerm(termFactory.createTermWithUpperKindergarten2());

        GridView gvTerms = (GridView)findViewById(R.id.gv_terms);
        gvTerms.setAdapter(new TermAdapter(this, terms,
                GridViewUtil.calcItemWidth(this)));
        gvTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setCurrentPositionWithTerm(position);
                onTermItemClick(position);
            }
        });

        if (isAutoPlayLast()) {
            MobclickAgent.onEvent(this, "auto_play_last");
            onTermItemClick(getCurrentPositionWithTerm());
        }

        MobclickAgent.setDebugMode(false);
        MobclickAgent.updateOnlineConfig(this);
    }

    private static final String key_auto_play_last_position = "auto_play_last_position";
    private void setCurrentPositionWithTerm(int currentPosition) {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putInt(key_auto_play_last_position, currentPosition);
        prefsEditor.commit();
    }

    private int getCurrentPositionWithTerm() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        return prefs.getInt(key_auto_play_last_position, 0);
    }

    private void onTermItemClick(int position) {
        Intent intent = new Intent(this, TermActivity.class);
        intent.putExtra(TermActivity.ARG_TERMS, terms);
        intent.putExtra(TermActivity.ARG_POSITION, position);
        startActivity(intent);

        Map<String, String> map = new HashMap<String, String>();
        map.put("term", terms.getTerm(position).getName());
        MobclickAgent.onEvent(this, "term", map);
    }

    private boolean isAutoPlayLast() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getBoolean("auto_play_last", false);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
