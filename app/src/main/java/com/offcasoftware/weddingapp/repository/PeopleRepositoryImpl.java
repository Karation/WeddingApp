package com.offcasoftware.weddingapp.repository;

import com.offcasoftware.weddingapp.AndroidApplication;
import com.offcasoftware.weddingapp.database.Database;
import com.offcasoftware.weddingapp.model.Visitor;

import java.util.List;

public class PeopleRepositoryImpl implements PeopleRepository {

    private static PeopleRepositoryImpl mInstance = new PeopleRepositoryImpl();

    private final Database mDatabase;

    private PeopleRepositoryImpl() {
        mDatabase = AndroidApplication.getDatabase();
    }

    public static PeopleRepositoryImpl getInstance() {
        return mInstance;
    }

    @Override
    public List<Visitor> getAllVisitor() {
        return mDatabase.getAllVisitors();
    }

    @Override
    public void changeStatus(final int visitorId, final Visitor.VisitorStatus visitorStatus) {
        mDatabase.changeStatus(visitorId, visitorStatus);
    }

    @Override
    public void saveVisitor(final Visitor visitor) {
        mDatabase.saveVisitor(visitor);
    }

    @Override
    public Visitor getVisitor(final int visitorId) {
        return mDatabase.getVisitor(visitorId);
    }

    @Override
    public void updateVisitor(int visitorId, Visitor visitor) {
        mDatabase.updateVisitor(visitorId, visitor);
    }

}
