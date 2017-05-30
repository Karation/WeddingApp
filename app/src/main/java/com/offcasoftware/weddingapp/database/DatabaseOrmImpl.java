package com.offcasoftware.weddingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.offcasoftware.weddingapp.model.Visitor;
import java.sql.SQLException;
import java.util.List;


public class DatabaseOrmImpl extends OrmLiteSqliteOpenHelper implements Database {

    private static final String DATABASE_NAME = "weddingAppDatabase";
    private static final int DATABASE_VERSION = 1;

    private RuntimeExceptionDao<Visitor, Integer> visitorDao;

    public DatabaseOrmImpl(final Context context) throws SQLException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        visitorDao = getRuntimeExceptionDao(Visitor.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTableIfNotExists(connectionSource, Visitor.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Visitor visitor1 = new Visitor(1, "Jan", "Nowak", 2, Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor2 = new Visitor(2, "Anna", "Nowak", 2, Visitor.VisitorStatus.RESPONSE_OK);
        Visitor visitor3 = new Visitor(3, "Tomasz", "Wieczorek", 1, Visitor.VisitorStatus.RESPONSE_NO);
        Visitor visitor4 = new Visitor(4, "Jan", "Kowalksi", 2, Visitor.VisitorStatus.RESPONSE_NO);
        Visitor visitor5 = new Visitor(5, "Michał", "Król", 3, Visitor.VisitorStatus.NO_RESPONSE);
        Visitor visitor6 = new Visitor(6, "Agnieskza", "Wróbel", 1, Visitor.VisitorStatus.RESPONSE_OK);

        visitorDao.create(visitor1);
        visitorDao.create(visitor2);
        visitorDao.create(visitor3);
        visitorDao.create(visitor4);
        visitorDao.create(visitor5);
        visitorDao.create(visitor6);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void updateVisitor(int visitorId, Visitor visitor) {
        try {
            UpdateBuilder<Visitor, Integer> updateBuilder = visitorDao.updateBuilder();
            updateBuilder.updateColumnValue("name", visitor.getName());
            updateBuilder.updateColumnValue("surname", visitor.getSurname());
            updateBuilder.updateColumnValue("additional_person", visitor.getAdditionalPerson());
            updateBuilder.where().eq("id", visitorId);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorDao.queryForAll();
    }

    @Override
    public void changeStatus(int visitorId, Visitor.VisitorStatus visitorStatus) {
        try {
            UpdateBuilder<Visitor, Integer> updateBuilder = visitorDao.updateBuilder();
            Where where = updateBuilder.where();
            where.eq("id", visitorId);
            updateBuilder.updateColumnValue("status", visitorStatus);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveVisitor(Visitor visitor) {
        visitorDao.create(visitor);
//        long a= System.currentTimeMillis();
//        Visitor visitor1 = new Visitor(1, "Jan", "Nowak", 2, Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor2 = new Visitor(2, "Anna", "Nowak", 2, Visitor.VisitorStatus.RESPONSE_OK);
//        Visitor visitor3 = new Visitor(3, "Tomasz", "Wieczorek", 1, Visitor.VisitorStatus.RESPONSE_NO);
//        Visitor visitor4 = new Visitor(4, "Jan", "Kowalksi", 2, Visitor.VisitorStatus.RESPONSE_NO);
//        Visitor visitor5 = new Visitor(5, "Michał", "Król", 3, Visitor.VisitorStatus.NO_RESPONSE);
//        Visitor visitor6 = new Visitor(6, "Agnieskza", "Wróbel", 1, Visitor.VisitorStatus.RESPONSE_OK);
//
//        visitorDao.create(visitor1);
//        visitorDao.create(visitor2);
//        visitorDao.create(visitor3);
//        visitorDao.create(visitor4);
//        visitorDao.create(visitor5);
//        visitorDao.create(visitor6);
//        long b= System.currentTimeMillis();
//
//        try {
//            getWritableDatabase().beginTransaction();
//
//            visitorDao.create(visitor1);
//            visitorDao.create(visitor2);
//            visitorDao.create(visitor3);
//            visitorDao.create(visitor4);
//            visitorDao.create(visitor5);
//            visitorDao.create(visitor6);
//            getWritableDatabase().setTransactionSuccessful();
//        }finally {
//            getWritableDatabase().endTransaction();
//        }
//
//        long c = System.currentTimeMillis();
//        long d =b-a;
//        long e = c-b;
//        long f = d-e;
//        Log.d("No", String.valueOf(d));
//        Log.d("With", String.valueOf(e));
//        Log.d("Difference", String.valueOf(f));
    }

    @Override
    public Visitor getVisitor(int visitorId) {
        try {
            QueryBuilder<Visitor, Integer> query = visitorDao.queryBuilder();
            Where where = query.where();
            where.eq("id", visitorId);
            return query.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
