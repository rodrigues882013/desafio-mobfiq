package com.example.felipe.desafiomobfiq;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.felipe.desafiomobfiq.adapters.ProductViewAdapter;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.models.Product;
import com.example.felipe.desafiomobfiq.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeActivity extends BaseActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, View.OnClickListener{

    private RelativeLayout mRelativeLayout;
    private List<Product> products = new ArrayList<Product>();
    private ProductViewAdapter pAdapter;
    private RecyclerView productList;
    private Button btnLoadMore;
    private Integer offset = 0;
    private ProgressBar progBarSm;
    private RecyclerView.LayoutManager layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(Utils.MOBIFQ, "Creating HomeActive");

        super.onCreate(savedInstanceState);
        onConfigure();
        getProductList();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(Utils.MOBIFQ, "Callback complete");

        try {
            JSONArray productsJson = response.getJSONArray("Products");
            onBuildProductList(productsJson);
            pAdapter.notifyDataSetChanged();
            productList.scrollToPosition(products.size()-1);
            changeProgressBar();
            offset += 10;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        changeProgressBar();
        Utils.onErrorHandler(this, error.getMessage());
    }

    @Override
    public void onClick(View v) {
        getProductList();
    }

    private void onConfigure(){
        onInflate(R.layout.activity_home);

        //Super class
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.setActionBarParams();

        if (toolbar != null) {
            onInitToolbar(null);
        }

        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_home);
        productList = (RecyclerView) findViewById(R.id.recycler);
        btnLoadMore = (Button) findViewById(R.id.btn_load_more);
        progBarSm = (ProgressBar) findViewById(R.id.pb_progress_small);

        btnLoadMore.setOnClickListener(this);
        layout = new GridLayoutManager(this, 2);
        productList.setLayoutManager(layout);

        pAdapter = new ProductViewAdapter(this, products);
        productList.setAdapter(pAdapter);
        productList.setNestedScrollingEnabled(false);


    }

    private void changeProgressBar(){
        if (progBarSm.getVisibility() == View.INVISIBLE || progBarSm.getVisibility() == View.GONE){
            btnLoadMore.setVisibility(View.GONE);
            progBarSm.setVisibility(View.VISIBLE);

        } else {
            progBarSm.setVisibility(View.GONE);
            btnLoadMore.setVisibility(View.VISIBLE);
        }
    }

    private JSONObject onPrepareParams(){
        Log.d(Utils.MOBIFQ, "Prepare params to search");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Query", "");
        params.put("Offset", offset.toString());
        params.put("Size", "10");

        return new JSONObject(params);

    }

    private void getProductList(){
        Log.d(Utils.MOBIFQ, "Retrieving product list");
        changeProgressBar();
        RequestQueue queue = AppController.getInstance(this.getApplicationContext()).getRequestQueue();
        String url = Utils.API_URL + "/Search/criteria";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, onPrepareParams(), this, this);
        queue.add(jsonRequest);
    }

    private void onBuildProductList(JSONArray productsJson){
        Log.d(Utils.MOBIFQ, "Deserializing list from server");

        try {
            for (int i = 0; i < productsJson.length(); i++) {
                Product p = new Product();
                JSONObject jsonObj = productsJson.getJSONObject(i);

                //Some informations are extract directly from SKU's
                JSONArray skusJson = jsonObj.getJSONArray("Skus");
                JSONObject skuJson = skusJson.getJSONObject(0);
                JSONArray imagesJson = skuJson.getJSONArray("Images");
                JSONObject imageJson = imagesJson.getJSONObject(0);
                JSONArray sellersJson = skuJson.getJSONArray("Sellers");
                JSONObject sellerJson = sellersJson.getJSONObject(0);

                String imgUrl = imageJson.getString("ImageUrl");
                Double price = sellerJson.getDouble("Price");

                p.setName(jsonObj.getString("Name"));
                p.setImage(imgUrl);
                p.setPrice(price);

                products.add(p);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
