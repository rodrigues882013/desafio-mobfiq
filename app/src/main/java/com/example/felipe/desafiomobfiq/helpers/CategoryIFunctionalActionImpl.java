package com.example.felipe.desafiomobfiq.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.felipe.desafiomobfiq.core.AppController;
import com.example.felipe.desafiomobfiq.models.Category;
import com.example.felipe.desafiomobfiq.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe on 6/27/17.
 */

public class CategoryIFunctionalActionImpl implements IFunctionalActions<Category>{

    private static CategoryIFunctionalActionImpl instance;

    private CategoryIFunctionalActionImpl(){}

    public static CategoryIFunctionalActionImpl getInstance(){
        if (instance == null){
            instance = new CategoryIFunctionalActionImpl();
        }
        return instance;
    }

    @Override
    public void getList(JSONObject params, Context context, Response.Listener<JSONObject> l1, Response.ErrorListener l2) {
        RequestQueue queue = AppController.getInstance(context).getRequestQueue();
        String url = Utils.API_URL + "/StorePreference/CategoryTree";
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, params, l1, l2);
        queue.add(jsonObject);
    }

    @Override
    public JSONObject onPrepareParams(String q, Integer offset) {
        return null;
    }

    @Override
    public List<Category> onBuildItemsList(Context context, JSONArray itemsJson) {
        Log.d(Utils.MOBIFQ, "Deserializing list from server");

        List<Category> categories = new ArrayList<Category>();

        try {
            for (int i = 0; i < itemsJson.length(); i++) {
                Category c = new Category();
                JSONObject jsonObj = itemsJson.getJSONObject(i);
                c.setName(jsonObj.getString("Name"));
                c.setImage("");
                categories.add(c);

            }

            return categories;

        } catch (JSONException e) {
            Log.e(Utils.MOBIFQ, String.format("Error: %s", e.getMessage()));
            return categories;
        }
    }
}
