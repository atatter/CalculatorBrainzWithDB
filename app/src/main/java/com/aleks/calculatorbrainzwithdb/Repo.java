package com.aleks.calculatorbrainzwithdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleks on 12/04/16.
 */
//CRUD
public abstract class Repo<T extends IEntity> {

    private SQLiteDatabase database;
    private String tablename;
    private String[] allColumns;

    private static String TAG = Repo.class.getName();

    public Repo(SQLiteDatabase database, String tablename, String[] allColumns) {
        this.database = database;
        this.tablename = tablename;
        this.allColumns = allColumns;
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    public  String getTableName(){
        return tablename;
    }

    public String[] getAllColumns(){
        return allColumns;
    }

    public List<T> getAll(){
        List<T> listOfEntity = new ArrayList<T>();

        Cursor cursor = database.query(tablename,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            T entity = cursorToEntity(cursor);
            listOfEntity.add(entity);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return listOfEntity;
    }


    public Cursor getCursorAll(){
        Cursor cursor = database.query(tablename,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        return cursor;
    }


    public T getById(long id){
        Cursor cursor = database.query(tablename,
                allColumns, allColumns[0] + " = " + id,
                null, null, null, null);

        if (cursor == null){
            return null;
        }

        cursor.moveToFirst();
        T newEntity = cursorToEntity(cursor);

        return newEntity;
    }

    public T add(T entity){
        ContentValues values = entityToContentValues(entity);
        long insertId = database.insert(tablename, null, values);

        Cursor cursor = database.query(tablename,
                allColumns, allColumns[0] + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        T newEntity = cursorToEntity(cursor);

        cursor.close();
        return newEntity;
    }

    public void update(T entity){
        ContentValues values = entityToContentValues(entity);
        database.update(tablename, values, allColumns[0] + "=" + entity.getId(), null);
    }

    public void delete(T entity){
        long id = entity.getId();
        System.out.println("Entity deleted from table "+tablename+" with id: " + id);
        database.delete(tablename, allColumns[0] + " = " + id, null);
    }

    public void delete(long id){
        System.out.println("Entity deleted from table "+tablename+" with id: " + id);
        database.delete(tablename, allColumns[0] + " = " + id, null);
    }

    public void deleteAll(){
        database.delete(tablename, null , null);
    }

    // this has to be implemented in child class
    public abstract T cursorToEntity(Cursor cursor);

    // this has to be implemented in child class
    public abstract ContentValues entityToContentValues(T entity);

}

