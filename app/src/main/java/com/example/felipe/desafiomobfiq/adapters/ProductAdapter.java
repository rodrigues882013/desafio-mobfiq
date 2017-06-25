package com.example.felipe.desafiomobfiq.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;
import com.example.felipe.desafiomobfiq.R;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.helpers.DatabaseHelper;
import com.example.felipe.desafiomobfiq.models.Product;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by felipe on 6/24/17.
 */

public class ProductAdapter extends CustomAdapter<Product> {

    public ProductAdapter(Activity activity, List<Product> items) {
        super(activity, items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }




}
