package com.aleks.calculatorbrainzwithdb;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by aleks on 12/04/16.
 */
public class OperandsAdapter extends CursorAdapter {
    private final LayoutInflater layoutInflater;
    private UOW uow;
    private ViewGroup parentViewGroup;

    public OperandsAdapter(Context context, Cursor c, UOW uow) {
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

        Operand operand = uow.operandRepo.cursorToEntity(cursor);
        textViewName.setText(operand.getOperand() + " Kasutatud " + operand.getLifetimeCounter() + " kord(a)");
    }

}
