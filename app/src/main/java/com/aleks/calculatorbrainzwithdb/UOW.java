package com.aleks.calculatorbrainzwithdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by aleks on 12/04/16.
 */
public class UOW {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private final Context context;
    public OperandRepo operandRepo;
    public OperationRepo operationRepo;
    public DayStatRepo dayStatRepo;

    public UOW(Context context) {
        this.context = context;

        dbHelper = new SQLiteHelper(context);
        database = dbHelper.getWritableDatabase();

        operandRepo = new OperandRepo(database, dbHelper.TABLE_OPERANDS, dbHelper.OPERANDS_ALLCOLUMNS);
        operationRepo = new OperationRepo(database, dbHelper.TABLE_OPERATIONS, dbHelper.OPERATIONS_ALLCOLUMNS);
        dayStatRepo = new DayStatRepo(database, dbHelper.TABLE_DAYSTATS, dbHelper.DAYSTATS_ALLCOLUMNS);
    }

    public void DropCreateDatabase() {
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData() {
        Operand plus = operandRepo.add(new Operand("+", 0));
        Operand minus = operandRepo.add(new Operand("-", 0));
        Operand multiply = operandRepo.add(new Operand("*", 0));
        Operand divide = operandRepo.add(new Operand("/", 0));

        Operation operation1 = operationRepo.add(new Operation(plus.getId(), 1, 2, 3, 0));
        Operation operation2 = operationRepo.add(new Operation(minus.getId(), 3, 2, 1, 0));
        Operation operation3 = operationRepo.add(new Operation(multiply.getId(), 1, 2, 2, 0));
        Operation operation4 = operationRepo.add(new Operation(divide.getId(), 3, 3, 1, 0));

        DayStat DayStatPlus = dayStatRepo.add(new DayStat(0, plus.getId(), 1));
        DayStat DayStatMinus = dayStatRepo.add(new DayStat(0, minus.getId(), 1));
        DayStat DayStatMultiply = dayStatRepo.add(new DayStat(0, multiply.getId(), 1));
        DayStat DayStatDivide = dayStatRepo.add(new DayStat(0, divide.getId(), 1));
    }

}
