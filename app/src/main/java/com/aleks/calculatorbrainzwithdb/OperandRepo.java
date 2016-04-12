package com.aleks.calculatorbrainzwithdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aleks on 12/04/16.
 */
public class OperandRepo extends Repo<Operand> {
    public OperandRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public Operand cursorToEntity(Cursor cursor) {
        Operand operand = new Operand();
        operand.setId(cursor.getLong(0));
        operand.setOperand(cursor.getString(1));
        operand.setLifetimeCounter(cursor.getLong(2));
        return operand;
    }

    @Override
    public ContentValues entityToContentValues(Operand operand) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteHelper.OPERANDS_COLUMN_OPERAND, operand.getOperand());
        contentValues.put(SQLiteHelper.OPERANDS_COLUMN_LIFETIMECOUNTER, operand.getLifetimeCounter());

        return contentValues;
    }
}
