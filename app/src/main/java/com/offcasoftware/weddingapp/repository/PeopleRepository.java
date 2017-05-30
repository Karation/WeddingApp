package com.offcasoftware.weddingapp.repository;

import com.offcasoftware.weddingapp.model.Visitor;

import java.util.List;

public interface PeopleRepository {

    List<Visitor> getAllVisitor();

    void changeStatus(int visitorId, Visitor.VisitorStatus visitorStatus);

    void saveVisitor(Visitor visitor);

    Visitor getVisitor(int visitorId);

    void updateVisitor(int visitorId, Visitor visitor);
}
