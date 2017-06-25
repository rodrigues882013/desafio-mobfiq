package com.example.felipe.desafiomobfiq;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.felipe.desafiomobfiq.adapters.ProductAdapter;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.models.Product;
import com.example.felipe.desafiomobfiq.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    private RelativeLayout mRelativeLayout;
    private List<Product> products = new ArrayList<Product>();
    private ProductAdapter pAdapter;
    private RecyclerView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_home);
        productList = (RecyclerView) findViewById(R.id.recycler);


        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 2);
        productList.setLayoutManager(layout);

        pAdapter = new ProductAdapter(this, products);
        productList.setAdapter(pAdapter);

        getProductList();
    }

    private JSONObject onPrepareParams(){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Query", "");
        params.put("Offset", "0");
        params.put("Size", "10");

        return new JSONObject(params);

    }

    private void getProductList(){
        RequestQueue queue = AppController.getInstance(this.getApplicationContext()).getRequestQueue();
        String url = Utils.API_URL + "/Search/criteria";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, onPrepareParams(), this, this);
        queue.add(jsonRequest);
    }

    private void onBuildProductList(JSONArray productsJson){
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

            Log.d("TESTE", "" + products.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray productsJson = response.getJSONArray("Products");
            onBuildProductList(productsJson);
            pAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


}
