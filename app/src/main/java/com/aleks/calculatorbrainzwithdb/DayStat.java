package com.aleks.calculatorbrainzwithdb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aleks on 12/04/16.
 */
public class DayStat implements IEntity{
    private long id;
    private long daystamp;
    private long operandId;
    private long dayCounter;

    DayStat() {

    }

    DayStat(long daystamp, long operandId, long dayCounter) {
        this.daystamp = daystamp;
        this.operandId = operandId;
        this.dayCounter = dayCounter;
    }

    //ID get/set
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //DAYSTAMP get/set
    public long getDaystamp() {
        return daystamp;
    }
    public void setDaystamp(long daystamp) {
        this.daystamp = daystamp;
    }

    //OPERANDID get/set
    public long getOperandId() {
        return operandId;
    }
    public void setOperandId(long operandId) {
        this.operandId = operandId;
    }

    //DAYCOUNTER get/set
    public long getDayCounter() {
        return dayCounter;
    }
    public void setDayCounter(long dayCounter) { this.dayCounter = dayCounter; }

    public String toString(UOW uow) {
        return "Kuupäev: \"" + dayStampToDate(getDaystamp()) + "\" \nMärk: "+ uow.operandRepo.getById(operandId).getOperand() +" päringuid: "+getDayCounter();
    }

    private String dayStampToDate(long l) {
        SimpleDateFormat inFormatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat outFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = inFormatter.parse(String.valueOf(l));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outFormatter.format(date);
    }
}
