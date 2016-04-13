package com.aleks.calculatorbrainzwithdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aleks on 12/04/16.
 */
public class DayStatRepo extends Repo<DayStat> {

    private SQLiteDatabase database;
    private String tableName;
    private String[] allColumns;

    public DayStatRepo(SQLiteDatabase database, String tablename, String[] allColumns) {
        super(database, tablename, allColumns);
        this.database = database;
        this.tableName = tablename;
        this.allColumns = allColumns;
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
    private long getDateStamp() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Long.parseLong(sdf.format(date));
    }

    public void addOneToDayCounter(long id) {
        DayStat newEntity;
        long dayStamp=getDateStamp();
        Cursor cursor = database.query(tableName,
                allColumns, allColumns[2] + " = "+ id +" and " + allColumns[1] +" = "+dayStamp ,
                null, null, null, null);

        if (cursor == null || cursor.getCount()<1) {
            //lisame
            DayStat stat = new DayStat();
            stat.setDayCounter(1);
            stat.setOperandId(id);
            stat.setDaystamp(dayStamp);
            newEntity = add(stat);
        } else {
            cursor.moveToFirst();
            newEntity = cursorToEntity(cursor);
            newEntity.setDayCounter(newEntity.getDayCounter() + 1);
            update(newEntity);
        }
    }
}
