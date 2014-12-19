package com.zaoqibu.zaoqibukindergartenmusic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Terms;
import com.zaoqibu.zaoqibukindergartenmusic.util.GridViewUtil;
import com.zaoqibu.zaoqibukindergartenmusic.util.TermFactory;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private Terms terms = new Terms();

    public MainActivity() {
        Log.i(TAG, "MainActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

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
                Intent intent = new Intent(MainActivity.this, TermActivity.class);
                intent.putExtra(TermActivity.ARG_TERMS, terms);
                intent.putExtra(TermActivity.ARG_POSITION, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
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
        if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
