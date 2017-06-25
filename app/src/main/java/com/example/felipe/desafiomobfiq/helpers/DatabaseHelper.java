package com.example.felipe.desafiomobfiq.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.felipe.desafiomobfiq.models.Category;
import com.example.felipe.desafiomobfiq.models.Product;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by felipe on 6/24/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Product, Integer> productDao = null;
    private Dao<Category, Integer> categoryDao = null;

    public DatabaseHelper(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Product.class);
            TableUtils.createTable(connectionSource, Category.class);

        } catch (SQLException e) {
            Log.e("DATABSE", "Unable to create databases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource,
                          int oldVersion,
                          int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Product.class, true);
            TableUtils.createTable(connectionSource, Product.class);

            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.createTable(connectionSource, Category.class);

        } catch (SQLException e) {
            Log.e("SIGEL", "Unable to create databases", e);
        }
    }

    public Dao<Product, Integer> getPackagesDao() throws SQLException {
        if (productDao == null) {
            productDao = getDao(Product.class);
        }
        return productDao;
    }

    public Dao<Category, Integer> getOrderDao() throws SQLException {
        if (categoryDao == null) {
            categoryDao = getDao(Category.class);
        }
        return categoryDao;
    }

    @Override
    public void close() {
        super.close();
        productDao = null;
        categoryDao = null;
    }
}
