package com.example.felipe.desafiomobfiq;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.felipe.desafiomobfiq.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;

public class SearchActivity extends HomeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
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
            getProductList(onPrepareParams(query));

        }
    }

    private void onExecute(String query){

    }


}
