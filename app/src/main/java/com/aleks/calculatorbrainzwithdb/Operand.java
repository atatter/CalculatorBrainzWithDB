package com.aleks.calculatorbrainzwithdb;

/**
 * Created by aleks on 12/04/16.
 */
public class Operand implements IEntity{
    private long id;
    private String operand;
    private long lifetimeCounter;

    Operand() {

    }

    Operand(String operand, int lifetimeCounter) {
        this.operand = operand;
        this.lifetimeCounter = lifetimeCounter;
    }

    //ID get/set
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    //OPERAND get/set
    public String getOperand() {
        return operand;
    }
    public void setOperand(String operand){
        this.operand = operand;
    }

    //LIFETIMECOUNTER get/set
    public long getLifetimeCounter() {
        return lifetimeCounter;
    }
    public void setLifetimeCounter(long lifetimeCounter) { this.lifetimeCounter = lifetimeCounter; }
}
