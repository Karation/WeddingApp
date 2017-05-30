package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;
import com.offcasoftware.weddingapp.model.Visitor;
import com.offcasoftware.weddingapp.repository.PeopleRepository;
import com.offcasoftware.weddingapp.repository.PeopleRepositoryImpl;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitorActivity extends AppCompatActivity implements AddVisitorDialogFragment.AddVisitorDialogInterface {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private VisitorAdapter mVisitorAdapter;
    private final PeopleRepository mPeopleRepository = PeopleRepositoryImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        ButterKnife.bind(this);

        initToolbarView();
        setupRecyclerView();
        displayData();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visitor, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.import_contacts) {
            Intent intent = new Intent(this, ContactActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDissmisDialog() {
        displayData();
    }

    @OnClick(R.id.add_new_visitor)
    void onNewVisitorClikced(View view) {
        DialogFragment dialog = AddVisitorDialogFragment.newInstance("Hello Java");
        dialog.show(getFragmentManager(), "Dialog");
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mVisitorAdapter = new VisitorAdapter(this);
        mRecyclerView.setAdapter(mVisitorAdapter);
        mVisitorAdapter.setOnLongClickListener(mOnLongClickListener);
        mVisitorAdapter.setOnClickLister(mOnClickListener);
    }

    private void initToolbarView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void displayData() {
        mVisitorAdapter.setData(mPeopleRepository.getAllVisitor());
    }

    private void showAskDialog(final Visitor visitor) {
        new AlertDialog.Builder(this)
                .setTitle("Potwierdzenie")
                .setMessage(getString(R.string.confirm_visitor, visitor.getName(),
                        visitor.getSurname()))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        changeVisitorStatus(visitor.getId(), Visitor.VisitorStatus.RESPONSE_OK);
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        changeVisitorStatus(visitor.getId(), Visitor.VisitorStatus.RESPONSE_NO);
                    }
                })
                .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {

                    }
                })
                .show();
    }

    private void changeVisitorStatus(int visitorId, Visitor.VisitorStatus visitorStatus) {
        mPeopleRepository.changeStatus(visitorId, visitorStatus);
        displayData();
    }

    void editVisitor(Visitor visitor) {
        DialogFragment dialog = AddVisitorDialogFragment.newInstance(visitor.getId());
        dialog.show(getFragmentManager(), "Dialog");
    }

    private final View.OnLongClickListener mOnLongClickListener =
            new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View v) {
                    Visitor visitor = (Visitor) v.getTag();
                    showAskDialog(visitor);
                    return true;
                }
            };

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            Visitor visitor = (Visitor) v.getTag();
            editVisitor(visitor);
        }
    };


}
