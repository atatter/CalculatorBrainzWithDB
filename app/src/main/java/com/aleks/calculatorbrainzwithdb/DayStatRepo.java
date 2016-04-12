package com.aleks.calculatorbrainzwithdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aleks on 12/04/16.
 */
public class DayStatRepo extends Repo<DayStat> {

    public DayStatRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
    }

    @Override
    public DayStat cursorToEntity(Cursor cursor) {
        DayStat dayStat = new DayStat();
        dayStat.setId(cursor.getLong(0));
        dayStat.setDaystamp(cursor.getLong(1));
        dayStat.setOperandId(cursor.getLong(2));
        dayStat.setDayCounter(cursor.getLong(3));

        return dayStat;
    }

    @Override
    public ContentValues entityToContentValues(DayStat dayStat) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteHelper.DAYSTATS_COLUMN_DAYSTAMP, dayStat.getDaystamp());
        contentValues.put(SQLiteHelper.DAYSTATS_COLUMN_OPERANDID, dayStat.getOperandId());
        contentValues.put(SQLiteHelper.DAYSTATS_COLUMN_DAYCOUNTER, dayStat.getDayCounter());

        return contentValues;
    }
}
