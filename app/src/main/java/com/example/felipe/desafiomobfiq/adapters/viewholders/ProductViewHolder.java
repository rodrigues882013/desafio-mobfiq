package com.example.felipe.desafiomobfiq.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.felipe.desafiomobfiq.R;

/**
 * Created by felipe on 6/24/17.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private TextView txtName;
    private TextView txtPrice;
    private NetworkImageView nImg;

    public ProductViewHolder(View itemView) {
        super(itemView);
        config();
    }

    private void config(){
        configWidgets(R.id.txt_name, itemView);
        configWidgets(R.id.txt_price, itemView);
        configWidgets(R.id.product_image, itemView);
    }

    private void configWidgets(final int vid, View v) {
        switch (vid){
            case R.id.txt_name:
                txtName = (TextView) v.findViewById(vid);
                break;
            case R.id.txt_price:
                txtPrice = (TextView) v.findViewById(vid);
                break;
            case R.id.product_image:
                nImg = (NetworkImageView) v.findViewById(vid);
                break;
        }
    }



    private void setText(String text){

    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextView txtPrice) {
        this.txtPrice = txtPrice;
    }

    public NetworkImageView getnImg() {
        return nImg;
    }

    public void setnImg(NetworkImageView nImg) {
        this.nImg = nImg;
    }
}
