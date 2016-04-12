package com.aleks.calculatorbrainzwithdb;

/**
 * Created by aleks on 12/04/16.
 */
public class Operation implements IEntity{
    private long id;
    private long operandId;
    private double nr1;
    private double nr2;
    private double res;
    private long timestamp;

    Operation() {

    }


    Operation(long operandId, double nr1, double nr2, double res, long timestamp) {
        this.operandId = operandId;
        this.nr1 = nr1;
        this.nr2 = nr2;
        this.res = res;
        this.timestamp = timestamp;
    }

    //ID get/set
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //OPERANDID get/set
    public long getOperandId() {
        return operandId;
    }
    public void setOperandId(long operandId) {
        this.operandId = operandId;
    }

    //NR1 get/set
    public double getNr1() {
        return nr1;
    }
    public void setNr1(double nr1){ this.nr1 = nr1; }

    //NR2 get/set
    public double getNr2() {
        return nr2;
    }
    public void setNr2(double nr2){ this.nr2 = nr2; }

    //RES get/set
    public double getRes() {
        return res;
    }
    public void setRes(double res){ this.res = res; }

    //TIMESTAMP get/set
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
