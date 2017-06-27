package com.example.felipe.desafiomobfiq;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
    private ProductIFunctionalActionImpl action = ProductIFunctionalActionImpl.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(Utils.MOBIFQ, "Creating HomeActive");

        super.onCreate(savedInstanceState);
        onConfigure();
        changeProgressBar();
        action.getList(action.onPrepareParams(null, offset), this.getApplicationContext(), this, this);
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
            products.addAll(action.onBuildItemsList(this, productsJson));
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
        action.getList(action.onPrepareParams(null, offset), this.getApplicationContext(), this, this);
    }

    protected void onConfigure(){
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

    protected void changeProgressBar(){
        if (progBarSm.getVisibility() == View.INVISIBLE || progBarSm.getVisibility() == View.GONE){
            btnLoadMore.setVisibility(View.GONE);
            progBarSm.setVisibility(View.VISIBLE);

        } else {
            progBarSm.setVisibility(View.GONE);
            btnLoadMore.setVisibility(View.VISIBLE);
        }
    }


}
