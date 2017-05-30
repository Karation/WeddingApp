package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;
import com.offcasoftware.weddingapp.repository.ExpenseRepository;
import com.offcasoftware.weddingapp.repository.ExpenseRepositoryImpl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.counter)
    TextView mCounterText;

    ExpenseRepository mExpenseRepository = ExpenseRepositoryImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        displayCounter();
        runMainScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void displayCounter() {
        int counter = mExpenseRepository.getCounter();

        String counterText2 = getResources().getQuantityString(R.plurals.open_app_counter, counter, counter);
        mCounterText.setText(counterText2);

        incrementCounter(counter);
    }

    private void incrementCounter(int counter) {
        counter++;
        mExpenseRepository.saveCounter(counter);
    }

    private void runMainScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToMenuScreen();
            }
        }, 3000);
    }

    private void navigateToMenuScreen() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
