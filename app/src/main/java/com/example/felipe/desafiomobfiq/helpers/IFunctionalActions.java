package com.example.felipe.desafiomobfiq.helpers;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by felipe on 6/27/17.
 */

interface IFunctionalActions<T> {
    void getList(JSONObject params, Context context, Response.Listener<JSONObject> l1, Response.ErrorListener l2);
    JSONObject onPrepareParams(String q, Integer offset);
    List<T> onBuildItemsList(Context context, JSONArray itemsJson);

}
