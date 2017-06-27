package com.example.felipe.desafiomobfiq.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.felipe.desafiomobfiq.R;
import com.example.felipe.desafiomobfiq.models.Category;

import java.util.HashMap;
import java.util.List;

/**
 * Created by felipe on 6/25/17.
 */

abstract class CustomAdapter<T> extends BaseAdapter{

    protected List<T> items;
    protected Activity activity;
    protected HashMap<T, Integer> mIdMap = new HashMap<T, Integer>();
    protected LayoutInflater inflater;

    CustomAdapter(Activity activity, List<T> items) {
        this.items = items;
        this.activity = activity;

        int idx = 0;
        for (T t : items){
            this.mIdMap.put(t, idx++);
        }

    }
    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public T getItem(int position) {
        return this.items.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return -1;
        }
        T item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return android.os.Build.VERSION.SDK_INT < 21;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        return null;
    }

}
