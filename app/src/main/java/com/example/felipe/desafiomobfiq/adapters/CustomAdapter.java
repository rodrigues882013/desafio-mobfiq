package com.example.felipe.desafiomobfiq.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.felipe.desafiomobfiq.models.Product;

import java.util.HashMap;
import java.util.List;

/**
 * Created by felipe on 6/24/17.
 */

abstract class CustomAdapter<T> extends RecyclerView.Adapter {
    protected List<T> items;
    protected Activity activity;
    protected HashMap<T, Integer> mIdMap = new HashMap<T, Integer>();

    CustomAdapter(Activity activity, List<T> items) {
        //super(activity, resource);
        this.items = items;
        this.activity = activity;

        int idx = 0;
        for (T t : items){
            this.mIdMap.put(t, idx++);
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }


}
