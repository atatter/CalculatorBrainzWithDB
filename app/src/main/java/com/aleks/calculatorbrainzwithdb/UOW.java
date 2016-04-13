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

    public void addDataToDB(double nr1, double nr2, String op, double res) {
        Operand operand = operandRepo.getByOperand(op);
        Operation operation = operationRepo.add(new Operation(operand.getId(), nr1, nr2, res, System.currentTimeMillis()));
        dayStatRepo.addOneToDayCounter(operand.getId());
    }

    public void DropCreateDatabase() {
        dbHelper.dropCreateDatabase(database);
    }

    public void SeedData() {
        Operand plus = operandRepo.add(new Operand("+", 0));
        Operand minus = operandRepo.add(new Operand("-", 0));
        Operand multiply = operandRepo.add(new Operand("*", 0));
        Operand divide = operandRepo.add(new Operand("/", 0));

        Operation operation1 = operationRepo.add(new Operation(plus.getId(), 1, 2, 3, System.currentTimeMillis()));
        Operation operation2 = operationRepo.add(new Operation(minus.getId(), 3, 2, 1, System.currentTimeMillis()));
        Operation operation3 = operationRepo.add(new Operation(multiply.getId(), 1, 2, 2, System.currentTimeMillis()));
        Operation operation4 = operationRepo.add(new Operation(divide.getId(), 3, 3, 1, System.currentTimeMillis()));

        DayStat DayStatPlus = dayStatRepo.add(new DayStat(20000202, plus.getId(), 1));
        DayStat DayStatMinus = dayStatRepo.add(new DayStat(20000203, minus.getId(), 1));
        DayStat DayStatMultiply = dayStatRepo.add(new DayStat(20000204, multiply.getId(), 1));
        DayStat DayStatDivide = dayStatRepo.add(new DayStat(20000205, divide.getId(), 1));
    }

}
