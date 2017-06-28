package com.example.felipe.desafiomobfiq.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.models.Product;
import com.example.felipe.desafiomobfiq.utils.Utils;
import com.github.underscore.$;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by felipe on 6/27/17.
 */

public class ProductIFunctionalActionImpl implements IFunctionalActions<Product> {

    private static ProductIFunctionalActionImpl instance;

    private ProductIFunctionalActionImpl(){}

    public static ProductIFunctionalActionImpl getInstance(){
        if (instance == null){
            instance = new ProductIFunctionalActionImpl();
        }
        return instance;
    }

    @Override
    public void getList(JSONObject params,
                        Context context,
                        Response.Listener<JSONObject> l1,
                        Response.ErrorListener l2) {

        Log.d(Utils.MOBIFQ, "Retrieving product list");
        //changeProgressBar();
        RequestQueue queue = AppController.getInstance(context).getRequestQueue();
        String url = Utils.API_URL + "/Search/criteria";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, params, l1, l2);
        queue.add(jsonRequest);
    }

    @Override
    public JSONObject onPrepareParams(String q, Integer offset){
        Log.d(Utils.MOBIFQ, "Prepare params to search");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Query", $.isNull(q) ? "" : q);
        params.put("Offset", offset.toString());
        params.put("Size", "10");

        return new JSONObject(params);

    }

    @Override
    public List<Product> onBuildItemsList(Context context, JSONArray productsJson){
        Log.d(Utils.MOBIFQ, "Building product objects");

        List<Product> products = new ArrayList<Product>();

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
                Double listPrice = sellerJson.getDouble("ListPrice");
                JSONObject bestInstalments = sellerJson.getJSONObject("BestInstallment");
                Integer installment = bestInstalments.getInt("Count");

                p.setName(jsonObj.getString("Name"));
                p.setImage(imgUrl);
                p.setPrice(price);
                p.setListPrice(listPrice);
                p.setInstallment(installment);

                products.add(p);

            }

            Log.d(Utils.MOBIFQ, "Object built with successful");
            return products;

        } catch (JSONException e) {
            Log.e(Utils.MOBIFQ, e.getMessage());
            Utils.onErrorHandler(context, e.getMessage());
            return products;
        }



    }

}
