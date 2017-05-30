package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.expense)
    void onExpanseClicked(View view) {
        Intent intent = new Intent(this, ExpanseActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.visitors)
    void onVisitorsClicked(View view) {
        Intent intent = new Intent(this, VisitorActivity.class);
        startActivity(intent);
    }
}