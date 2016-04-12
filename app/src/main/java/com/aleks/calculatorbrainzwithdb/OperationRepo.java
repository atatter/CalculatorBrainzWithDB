package com.aleks.calculatorbrainzwithdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aleks on 12/04/16.
 */
public class OperationRepo extends Repo<Operation>{

    public OperationRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public Operation cursorToEntity(Cursor cursor) {
        Operation operation = new Operation();
        operation.setId(cursor.getLong(0));
        operation.setOperandId(cursor.getLong(1));
        operation.setNr1(cursor.getDouble(2));
        operation.setNr2(cursor.getDouble(3));
        operation.setRes(cursor.getDouble(4));
        operation.setTimestamp(cursor.getLong(5));

        return operation;
    }

    @Override
    public ContentValues entityToContentValues(Operation operation) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteHelper.OPERATIONS_COLUMN_OPERANDID, operation.getOperandId());
        contentValues.put(SQLiteHelper.OPERATIONS_COLUMN_NR1, operation.getNr1());
        contentValues.put(SQLiteHelper.OPERATIONS_COLUMN_NR2, operation.getNr2());
        contentValues.put(SQLiteHelper.OPERATIONS_COLUMN_RES, operation.getRes());
        contentValues.put(SQLiteHelper.OPERATIONS_COLUMN_TIMESTAMP, operation.getTimestamp());

        return contentValues;
    }
}
