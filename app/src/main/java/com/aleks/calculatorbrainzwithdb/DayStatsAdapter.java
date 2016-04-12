package com.aleks.calculatorbrainzwithdb;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by aleks on 12/04/16.
 */
public class DayStatsAdapter extends CursorAdapter {
    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public DayStatsAdapter(Context context, Cursor c, UOW uow) {
        super(context, c, 0);
        layoutInflater = LayoutInflater.from(context);
        this.uow = uow;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view = layoutInflater.inflate(R.layout.db_operand, parent, false);
        parentViewGroup = parent;
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView) view.findViewById(R.id.name);

        DayStat dayStat = uow.dayStatRepo.cursorToEntity(cursor);
        textViewName.setText(dayStat.toString(uow));
    }
}
