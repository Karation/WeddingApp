package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;
import com.offcasoftware.weddingapp.model.Contact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {

    private static final int CONTACT_LOADER = 1;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ContactAdapter mContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        setupRecyclerView();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "Kliknij tu", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 12);
        } else {
            loadContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 12) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            } else {
                Toast.makeText(this, "Brak zgody", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mContactAdapter = new ContactAdapter(this);
        mRecyclerView.setAdapter(mContactAdapter);
    }

    private void loadContacts() {
        getSupportLoaderManager().initLoader(CONTACT_LOADER, null, mCallbackContacts);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mCallbackContacts =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
                    return new CursorLoader(ContactActivity.this, ContactsContract.Contacts.CONTENT_URI,
                            null, null, null, null);
                }

                @Override
                public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
                    displayContacts(data);
                }

                @Override
                public void onLoaderReset(final Loader<Cursor> loader) {

                }
            };

    private void displayContacts(Cursor cursor) {
        if (cursor == null) {
            return;
        }

        List<Contact> contactList = new ArrayList<>();
        cursor.moveToFirst();
        do {
            Contact contact = new Contact(cursor);
            contactList.add(contact);
        } while (cursor.moveToNext());

        mContactAdapter.setContactList(contactList);
    }

}
