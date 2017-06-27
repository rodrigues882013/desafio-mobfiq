package com.example.felipe.desafiomobfiq;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.felipe.desafiomobfiq.adapters.ProductViewAdapter;
import com.example.felipe.desafiomobfiq.helpers.ProductIFunctionalActionImpl;
import com.example.felipe.desafiomobfiq.models.Product;
import com.example.felipe.desafiomobfiq.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    private RelativeLayout mRelativeLayout;
    private List<Product> products = new ArrayList<Product>();
    private ProductViewAdapter pAdapter;
    private RecyclerView productList;
    private Integer offset = 0;
    private ProgressBar progBarSm;
    private RecyclerView.LayoutManager layout;
    private ProductIFunctionalActionImpl action = ProductIFunctionalActionImpl.getInstance();



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        onConfigure();

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(Utils.MOBIFQ, "Callback complete");

        try {
            JSONArray productsJson = response.getJSONArray("Products");
            //action.onBuildProductList(this.getApplicationContext(), productsJson);
            pAdapter.notifyDataSetChanged();
            productList.scrollToPosition(products.size()-1);
            offset += 10;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onBuildItemList(JSONArray productsJson){
//        super.onBuildItemList(null);
//
//        try {
//            for (int i = 0; i < productsJson.length(); i++) {
//                products.add(action.onBuildProduct(this, productsJson.getJSONObject(i)));
//            }
//
//        } catch (JSONException e) {
//            Utils.onErrorHandler(this, e.getMessage());
//        }
//
//    }

    protected void onConfigure(){
        onInflate(R.layout.activity_search);

        //Super class
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.setActionBarParams();

        if (toolbar != null) {
            super.onInitToolbar("Category");
        }

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            action.getList(action.onPrepareParams(query, 0), this, this, this);

        }
    }

    private void onExecute(String query){

    }
}
