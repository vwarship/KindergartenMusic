package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by vwarship on 2014/12/17.
 */
public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getActionBar().setTitle(R.string.action_about);
    }
}
