package com.example.felipe.desafiomobfiq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.felipe.desafiomobfiq.adapters.CategoryAdapter;
import com.example.felipe.desafiomobfiq.models.Category;
import com.example.felipe.desafiomobfiq.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe on 6/25/17.
 */

public class CategoryActivity extends AppCompatActivity implements Response.Listener<String>,
        Response.ErrorListener{

    private List<Category> categoriesList = new ArrayList<Category>();
    private CategoryAdapter cAdapter;
    private ListView categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Utils.MOBIFQ, "Creating CategoryActive");

        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        categoryList.setAdapter(cAdapter);

        getCategoryList();
    }

    private void getCategoryList() {
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }
}
