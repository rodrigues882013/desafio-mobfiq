package com.example.felipe.desafiomobfiq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.felipe.desafiomobfiq.adapters.CategoryAdapter;
import com.example.felipe.desafiomobfiq.core.AppController;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(Utils.MOBIFQ, "Creating CategoryActive");
        super.onCreate(savedInstanceState);
        onConfigure();
        getCategoryList();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(Utils.MOBIFQ, "Callback complete");

        try {
            JSONArray categoryJson = response.getJSONArray("Categories");
            onBuildCategoryList(categoryJson);
            cAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(Utils.MOBIFQ, String.format("Error: %s", error.getMessage()));
    }

    protected void onConfigure(){
        onInflate(R.layout.activity_category);

        //Super class
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.setActionBarParams();

        if (toolbar != null) {
            super.onInitToolbar("Category");
        }
        categoryList = (ListView) findViewById(R.id.category_list);
        cAdapter = new CategoryAdapter(this, categories);
        categoryList.setAdapter(cAdapter);
    }

    private void getCategoryList() {
        RequestQueue queue = AppController.getInstance(this.getApplicationContext()).getRequestQueue();
        String url = Utils.API_URL + "/StorePreference/CategoryTree";
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonObject);
    }

    private void onBuildCategoryList(JSONArray categoryJson) {
        Log.d(Utils.MOBIFQ, "Deserializing list from server");

        try {
            for (int i = 0; i < categoryJson.length(); i++) {
                Category c = new Category();
                JSONObject jsonObj = categoryJson.getJSONObject(i);
                c.setName(jsonObj.getString("Name"));
                c.setImage("");
                categories.add(c);

            }

        } catch (JSONException e) {
            Log.e(Utils.MOBIFQ, String.format("Error: %s", e.getMessage()));
        }
    }

}
