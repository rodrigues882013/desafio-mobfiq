package com.example.felipe.desafiomobfiq;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
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
            products.addAll(action.onBuildItemsList(this, productsJson));
            pAdapter.notifyDataSetChanged();
            productList.scrollToPosition(products.size()-1);
            offset += 10;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    protected void onConfigure(){
        onInflate(R.layout.activity_search);

        //Super class
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.setActionBarParams();

        if (toolbar != null) {
            super.onInitToolbar(Utils.RESULTS);
        }

        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_home);
        productList = (RecyclerView) findViewById(R.id.recycler);
        progBarSm = (ProgressBar) findViewById(R.id.pb_progress_small);

        layout = new GridLayoutManager(this, 2);
        productList.setLayoutManager(layout);

        pAdapter = new ProductViewAdapter(this, products);
        productList.setAdapter(pAdapter);
        productList.setNestedScrollingEnabled(false);


        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            action.getList(action.onPrepareParams(query, 0), this, this, this);
        }
    }

    private void onExecute(String query){

    }
}
