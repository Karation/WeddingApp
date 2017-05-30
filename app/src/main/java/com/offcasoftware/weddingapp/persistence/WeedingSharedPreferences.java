package com.offcasoftware.weddingapp.persistence;

import android.content.Context;
import android.content.SharedPreferences;

public class WeedingSharedPreferences {

    private static final String SHARED_NAME = "weedingApp";
    private static final String COUNTER_KEY = "counter";

    private final SharedPreferences mSharedPreferences;

    public WeedingSharedPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public void saveCounter(int counter) {
        final SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(COUNTER_KEY, counter);
        editor.apply();
    }

    public int getCounter() {
        final int counter = mSharedPreferences.getInt(COUNTER_KEY, 0);
        return counter;
    }
}
