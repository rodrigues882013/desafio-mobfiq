package com.example.felipe.desafiomobfiq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.felipe.desafiomobfiq.adapters.CategoryAdapter;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.helpers.CategoryIFunctionalActionImpl;
import com.example.felipe.desafiomobfiq.models.Category;
import com.example.felipe.desafiomobfiq.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe on 6/25/17.
 */

public class CategoryActivity extends BaseActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    private List<Category> categories = new ArrayList<Category>();
    private CategoryAdapter cAdapter;
    private ListView categoryList;
    private ProgressBar progBarSm;
    private CategoryIFunctionalActionImpl action = CategoryIFunctionalActionImpl.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(Utils.MOBIFQ, "Creating CategoryActive");
        super.onCreate(savedInstanceState);
        onConfigure();
        changeProgressBar();
        action.getList(action.onPrepareParams(null, null), this.getApplicationContext(), this, this);
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(Utils.MOBIFQ, "Callback complete");

        try {
            JSONArray categoryJson = response.getJSONArray("Categories");
            categories.addAll(action.onBuildItemsList(this, categoryJson));
            cAdapter.notifyDataSetChanged();
            changeProgressBar();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Utils.onErrorHandler(this, String.format("Error: %s", error.getMessage()));
        changeProgressBar();
    }

    protected void onConfigure(){
        onInflate(R.layout.activity_category);
        progBarSm = (ProgressBar) findViewById(R.id.pb_progress_small);

        //Super class
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.setActionBarParams();

        if (toolbar != null) {
            super.onInitToolbar(Utils.CATEGORY);
        }
        categoryList = (ListView) findViewById(R.id.category_list);
        cAdapter = new CategoryAdapter(this, categories);
        categoryList.setAdapter(cAdapter);
    }

    @Override
    protected void changeProgressBar(){
        if (progBarSm.getVisibility() == View.INVISIBLE ||
                progBarSm.getVisibility() == View.INVISIBLE){
            progBarSm.setVisibility(View.VISIBLE);
        } else {
            progBarSm.setVisibility(View.INVISIBLE);
        }
    }

}
