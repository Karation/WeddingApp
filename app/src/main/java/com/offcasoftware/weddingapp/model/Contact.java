package com.offcasoftware.weddingapp.model;

import android.database.Cursor;
import android.provider.ContactsContract;

public class Contact {

    private int mId;
    private String mName;

    public Contact(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex("_id"));
        mName = cursor.getString(cursor.getColumnIndex
                (ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
