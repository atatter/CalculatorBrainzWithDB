package com.aleks.calculatorbrainzwithdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by aleks on 12/04/16.
 */
public class OperandRepo extends Repo<Operand> {
    private SQLiteDatabase database;
    private String tableName;
    private String[] allColumns;

    public OperandRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
        this.database = database;
        this.tableName = tablename;
        this.allColumns = allColumns;
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

    public Operand getByOperand(String op) {
        Operand operand;
        Cursor cursor = database.query(tableName,
                allColumns, allColumns[1] + " = '" + op +"'",
                null, null, null, null);

        if (cursor == null || cursor.getCount()<1) {
            Operand opObj = new Operand();
            opObj.setOperand(op);
            opObj.setLifetimeCounter(1);
            operand = add(opObj);
        }
        else {
            cursor.moveToFirst();
            operand = cursorToEntity(cursor);
            long nr = operand.getLifetimeCounter();
            Log.d("MYLOG", "Operandcounter: " + nr);
            nr = nr + 1;
            Log.d("MYLOG", "Operandcounter: " + nr);
            operand.setLifetimeCounter(nr);
            update(operand);
        }

        return operand;
    }
}
