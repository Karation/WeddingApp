package com.offcasoftware.weddingapp.repository;

import com.offcasoftware.weddingapp.AndroidApplication;
import com.offcasoftware.weddingapp.persistence.WeedingSharedPreferences;

public class ExpenseRepositoryImpl implements ExpenseRepository {

    private static ExpenseRepositoryImpl mInstance = new ExpenseRepositoryImpl();

    private final WeedingSharedPreferences mSharedPreferences;

    private ExpenseRepositoryImpl() {
        mSharedPreferences = AndroidApplication.getSharedPreferences();
    }

    public static ExpenseRepositoryImpl getInstance() {
        return mInstance;
    }

    @Override
    public void saveCounter(final int counter) {
        mSharedPreferences.saveCounter(counter);
    }

    @Override
    public int getCounter() {
        return mSharedPreferences.getCounter();
    }
}
