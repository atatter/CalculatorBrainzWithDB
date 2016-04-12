package com.aleks.calculatorbrainzwithdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by aleks on 12/04/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    //OPERAND Class is here
    public static final String TABLE_OPERANDS = "operands";
    public static final String OPERANDS_COLUMN_ID = "_id";
    public static final String OPERANDS_COLUMN_OPERAND = "operand";
    public static final String OPERANDS_COLUMN_LIFETIMECOUNTER = "lifetimeCounter";
    //uow vajab allcolumns
    public static final String[] OPERANDS_ALLCOLUMNS = { OPERANDS_COLUMN_ID, OPERANDS_COLUMN_OPERAND,
            OPERANDS_COLUMN_LIFETIMECOUNTER };

    //OPERATION Class is here
    public static final String TABLE_OPERATIONS = "operations";
    public static final String OPERATIONS_COLUMN_ID = "_id";
    public static final String OPERATIONS_COLUMN_OPERANDID = "operandId";
    public static final String OPERATIONS_COLUMN_NR1 = "nr1";
    public static final String OPERATIONS_COLUMN_NR2 = "nr2";
    public static final String OPERATIONS_COLUMN_RES = "res";
    public static final String OPERATIONS_COLUMN_TIMESTAMP = "timestamp";
    //uow vajab allcolumns
    public static final String[] OPERATIONS_ALLCOLUMNS = { OPERATIONS_COLUMN_ID, OPERATIONS_COLUMN_OPERANDID,
            OPERATIONS_COLUMN_NR1, OPERATIONS_COLUMN_NR2, OPERATIONS_COLUMN_RES, OPERATIONS_COLUMN_TIMESTAMP };

    //DAYSTAT Class is here
    public static final String TABLE_DAYSTATS = "daystats";
    public static final String DAYSTATS_COLUMN_ID = "_id";
    public static final String DAYSTATS_COLUMN_DAYSTAMP = "daystamp";
    public static final String DAYSTATS_COLUMN_OPERANDID = "operandId";
    public static final String DAYSTATS_COLUMN_DAYCOUNTER = "dayCounter";
    //uow vajab allcolumns
    public static final String[] DAYSTATS_ALLCOLUMNS = { DAYSTATS_COLUMN_ID, DAYSTATS_COLUMN_DAYSTAMP,
            DAYSTATS_COLUMN_OPERANDID, DAYSTATS_COLUMN_DAYCOUNTER };

    //DATABASE CREATION STRINGS
    private static final String DATABASE_CREATE_OPERANDS = "create table "
            + TABLE_OPERANDS + "("
            + OPERANDS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERANDS_COLUMN_OPERAND + " text not null, "
            + OPERANDS_COLUMN_LIFETIMECOUNTER + " integer not null);";

    private static final String DATABASE_CREATE_OPERATIONS = "create table "
            + TABLE_OPERATIONS + "("
            + OPERATIONS_COLUMN_ID + " integer primary key autoincrement, "
            + OPERATIONS_COLUMN_OPERANDID + " integer not null, "
            + OPERATIONS_COLUMN_NR1 + " real null, "
            + OPERATIONS_COLUMN_NR2 + " real null, "
            + OPERATIONS_COLUMN_RES + " real null, "
            + OPERATIONS_COLUMN_TIMESTAMP + " integer not null);";

    private static final String DATABASE_CREATE_DAYSTATS = "create table "
            + TABLE_DAYSTATS + "("
            + DAYSTATS_COLUMN_ID + " integer primary key autoincrement, "
            + DAYSTATS_COLUMN_DAYSTAMP + " integer not null, "
            + DAYSTATS_COLUMN_OPERANDID + " integer not null, "
            + DAYSTATS_COLUMN_DAYCOUNTER + " integer not null);";

    //DATABASE INFO
    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_OPERANDS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_DAYSTATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAYSTATS);
        onCreate(db);
    }

    public void dropCreateDatabase(SQLiteDatabase db){
        //Kustuta need read:
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAYSTATS);
        //Tekita need read:
        db.execSQL(DATABASE_CREATE_OPERANDS);
        db.execSQL(DATABASE_CREATE_OPERATIONS);
        db.execSQL(DATABASE_CREATE_DAYSTATS);

    }
}
