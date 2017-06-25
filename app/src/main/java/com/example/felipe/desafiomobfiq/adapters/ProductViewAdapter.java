package com.example.felipe.desafiomobfiq.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felipe.desafiomobfiq.R;
import com.example.felipe.desafiomobfiq.adapters.viewholders.ProductViewHolder;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.models.Product;

import java.util.List;

/**
 * Created by felipe on 6/24/17.
 */

public class ProductViewAdapter extends CustomRecycleViewAdapter<Product> {

    public ProductViewAdapter(Activity activity, List<Product> items) {
        super(activity, items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.activity).inflate(R.layout.card_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ProductViewHolder holder = (ProductViewHolder) viewHolder;
        Product p  = (Product) this.items.get(position) ;

        holder.getTxtName().setText(p.getName());
        holder.getTxtPrice().setText(p.getPrice().toString());
        holder.getnImg().setImageUrl(p.getImage(), AppController.getInstance(activity).getImageLoader());

        //demais campos

    }



}
