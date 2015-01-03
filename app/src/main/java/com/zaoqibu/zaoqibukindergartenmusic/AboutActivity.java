package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by vwarship on 2014/12/17.
 */
public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getActionBar().setTitle(R.string.action_about);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        MobclickAgent.onEvent(this, "about");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
