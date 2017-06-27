package com.example.felipe.desafiomobfiq;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.felipe.desafiomobfiq.utils.Utils;
import com.github.underscore.$;

import org.json.JSONArray;

import java.sql.SQLException;

/**
 * Created by felipe on 6/25/17.
 */

public class BaseActivity extends AppCompatActivity implements ListView.OnItemClickListener  {
    protected DrawerLayout drawerLayout;
    private String[] menuTitles;
    private ListView menuDrawer;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        onConfigure();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onBuildItemList(JSONArray itemsJson) {
        Log.d(Utils.MOBIFQ, "Deserializing list from server");
    }

    protected void onInflate(int layoutId){
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null, false);
        drawerLayout.addView(contentView, 0);
    }

    private void onConfigure() {
        menuTitles = getResources().getStringArray(R.array.menu_item);
        drawerLayout = (DrawerLayout) findViewById(R.id.base_layout);
        menuDrawer = (ListView) findViewById(R.id.left_drawer);
        menuDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, menuTitles));
        menuDrawer.setOnItemClickListener(this);
    }

    protected void setActionBarParams() {
        mDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                Log.d(Utils.MOBIFQ, "TESTE");
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(mDrawerToggle);


    }

    protected void onInitToolbar(String title){

        if (!$.isNull(title)){
            toolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void selectItem(int position) {
        Intent intent;
        switch (position) {

            case 0:
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                break;

            case 1:
                intent = new Intent(getBaseContext(), CategoryActivity.class);
                startActivity(intent);
                break;
        }
    }


}
