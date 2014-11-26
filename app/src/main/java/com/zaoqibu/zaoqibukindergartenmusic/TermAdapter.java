package com.zaoqibu.zaoqibukindergartenmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.zaoqibu.zaoqibukindergartenmusic.domain.Term;
import com.zaoqibu.zaoqibukindergartenmusic.domain.Terms;

/**
 * Created by vwarship on 2014/11/27.
 */
public class TermAdapter extends BaseAdapter {
    private Context context;
    private Terms terms;
    private int itemWidth;

    public TermAdapter(Context context, Terms terms, int itemWidth) {
        this.context = context;
        this.terms = terms;
        this.itemWidth = itemWidth;
    }

    @Override
    public int getCount() {
        return terms.count();
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

        View item = null;

        if (convertView == null)
        {
            item = layoutInflater.inflate(R.layout.term_item, parent, false);
            item.setLayoutParams(new GridView.LayoutParams(itemWidth, itemWidth));
        }
        else
        {
            item = convertView;
        }

       Term term = terms.getTerm(position);

        //ImageView imageView = (ImageView)item.findViewById(R.id.itemImage);
        //imageView.setBackgroundColor(Color.parseColor(color.getHexColorCode()));

        item.setBackgroundColor(context.getResources().getColor(term.getBackgroundColorResId()));
        TextView textView = (TextView)item.findViewById(R.id.tv_term_name);
        textView.setText(term.getName());

        return item;
    }
}
