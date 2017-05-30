package com.offcasoftware.weddingapp.database;

import com.offcasoftware.weddingapp.model.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseCache implements Database {

    private final Map<Integer, Visitor> mVisitors = new HashMap<>();

    public DatabaseCache() {
        Visitor visitor1 = new Visitor(1, "Jan", "Nowak", 2, Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor2 = new Visitor(2, "Anna", "Nowak", 2, Visitor.VisitorStatus.RESPONSE_OK);
        Visitor visitor3 = new Visitor(3, "Tomasz", "Wieczorek", 1, Visitor.VisitorStatus.RESPONSE_NO);
        Visitor visitor4 = new Visitor(4, "Jan", "Kowalksi", 2, Visitor.VisitorStatus.RESPONSE_NO);
        Visitor visitor5 = new Visitor(5, "Michał", "Król", 3, Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor6 = new Visitor(6, "Agnieskza", "Wróbel", 1, Visitor.VisitorStatus.RESPONSE_OK);

        mVisitors.put(1, visitor1);
        mVisitors.put(2, visitor2);
        mVisitors.put(3, visitor3);
        mVisitors.put(4, visitor4);
        mVisitors.put(5, visitor5);
        mVisitors.put(6, visitor6);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return new ArrayList<>(mVisitors.values());
    }

    @Override
    public void changeStatus(final int visitorId, final Visitor.VisitorStatus visitorStatus) {
        mVisitors.get(visitorId).setVisitorStatus(visitorStatus);
    }

    @Override
    public void saveVisitor(final Visitor visitor) {
        mVisitors.put(visitor.getId(), visitor);
    }

    @Override
    public Visitor getVisitor(final int visitorId) {
        return mVisitors.get(visitorId);
    }

    @Override
    public void updateVisitor(int visitorId, Visitor visitor) {

    }
}
