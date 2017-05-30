package com.offcasoftware.weddingapp;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.offcasoftware.weddingapp.database.Database;
import com.offcasoftware.weddingapp.database.DatabaseCache;
import com.offcasoftware.weddingapp.database.DatabaseOrmImpl;
import com.offcasoftware.weddingapp.persistence.WeedingSharedPreferences;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

public class AndroidApplication extends Application {

    private static Database mDatabase;
    private static WeedingSharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeVectorResources();
        mSharedPreferences = new WeedingSharedPreferences(this);
        //mDatabase = new DatabaseCache();
        mDatabase = OpenHelperManager.getHelper(this, DatabaseOrmImpl.class);
    }

    private void initializeVectorResources() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static WeedingSharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public static Database getDatabase() {
        return mDatabase;
    }
}
