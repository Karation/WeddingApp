package com.offcasoftware.weddingapp.database;

import com.offcasoftware.weddingapp.model.Visitor;

import java.util.List;

public interface Database {

    List<Visitor> getAllVisitors();

    void changeStatus(int visitorId, Visitor.VisitorStatus visitorStatus);

    void saveVisitor(Visitor visitor);

    Visitor getVisitor(int visitorId);
    void updateVisitor(int visitorId, Visitor visitor);
}
