package com.example.felipe.desafiomobfiq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.felipe.desafiomobfiq.utils.Utils;

import java.sql.SQLException;

/**
 * Created by felipe on 6/25/17.
 */

public class BaseActivity extends AppCompatActivity implements ListView.OnItemClickListener  {
    protected DrawerLayout drawerLayout;
    private String[] menuTitles;
    private ListView menuDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        createWidgets();
        setActionBarParams();

        if (toolbar != null) {
            toolbar.setTitle("Navigation Drawer");
            setSupportActionBar(toolbar);
        }
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private void setActionBarParams() {
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
                    super.onDrawerOpened(drawerView);
                }
            };

            drawerLayout.addDrawerListener(mDrawerToggle);


    }

    private void createWidgets() {
        menuTitles = getResources().getStringArray(R.array.menu_item);
        drawerLayout = (DrawerLayout) findViewById(R.id.base_layout);
        menuDrawer = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        menuDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, menuTitles));
        menuDrawer.setOnItemClickListener(this);
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
