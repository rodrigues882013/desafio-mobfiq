package com.example.felipe.desafiomobfiq.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.felipe.desafiomobfiq.R;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.models.Category;

import java.util.List;

/**
 * Created by felipe on 6/24/17.
 */

public class CategoryAdapter extends CustomAdapter<Category> {

    public CategoryAdapter(Activity activity, List<Category> items) {
        super(activity, items);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        Category c = items.get(position);

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_category, null);
        }

        //Get Image
        NetworkImageView imgLocal = (NetworkImageView) convertView.findViewById(R.id.img_category_image);
        imgLocal.setImageUrl(c.getImage(), AppController.getInstance(activity).getImageLoader());

        //Get Texts
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_category_name);
        String packageName = c.getName();
        if (packageName != null) {
            txtTitle.setText(packageName);
        }

        return convertView;



    }
}
