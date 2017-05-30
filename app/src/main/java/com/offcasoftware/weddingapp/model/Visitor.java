package com.offcasoftware.weddingapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.offcasoftware.weddingapp.R;

@DatabaseTable(tableName = "visitors")
public class Visitor {

    public enum VisitorStatus {
        NO_RESPONSE(R.color.white_light),
        RESPONSE_OK(R.color.geen_light),
        RESPONSE_NO(R.color.red_light);

        private int mColor;

        VisitorStatus(int color) {
            mColor = color;
        }

        public int getColor() {
            return mColor;
        }
    }
    @DatabaseField(columnName = "id", generatedId = true)
    private int mId;

    @DatabaseField(columnName = "name", canBeNull = false)
    private String mName;

    @DatabaseField(columnName = "surname", canBeNull = false)
    private String mSurname;

    @DatabaseField (columnName = "additional_person", canBeNull = false)
    private int mAdditionalPerson;

    @DatabaseField (columnName = "status")
    private VisitorStatus mVisitorStatus;

    public Visitor(){

    }

    public Visitor(int id, String name, String surname, int additionalPerson, VisitorStatus visitorStatus) {
        mId = id;
        mName = name;
        mSurname = surname;
        mAdditionalPerson = additionalPerson;
        mVisitorStatus = visitorStatus;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getSurname() {
        return mSurname;
    }

    public int getAdditionalPerson() {
        return mAdditionalPerson;
    }

    public VisitorStatus getVisitorStatus() {
        return mVisitorStatus;
    }

    public void setVisitorStatus(final VisitorStatus visitorStatus) {
        mVisitorStatus = visitorStatus;
    }

    public void setName(final String name) {
        mName = name;
    }

    public void setSurname(final String surname) {
        mSurname = surname;
    }

    public void setAdditionalPerson(final int additionalPerson) {
        mAdditionalPerson = additionalPerson;
    }
}
