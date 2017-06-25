package com.example.felipe.desafiomobfiq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.felipe.desafiomobfiq.adapters.ProductAdapter;
import com.example.felipe.desafiomobfiq.models.Product;
import com.github.underscore.Block;
import com.github.underscore.lodash.$;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class HomeActivity extends AppCompatActivity {

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


    }
}
