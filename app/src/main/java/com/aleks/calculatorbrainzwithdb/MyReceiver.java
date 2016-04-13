package com.aleks.calculatorbrainzwithdb;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Created by aleks on 13/04/16.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        UOW uow = new UOW(context);


        if(isOrderedBroadcast()){
            Bundle extras = intent.getExtras();
            if(extras != null) {
                double nr1 = extras.getDouble("val1");
                double nr2 = extras.getDouble("val2");
                String op = extras.getString("op");
                double res = calculate(nr1, nr2, op);
                setResultCode(Activity.RESULT_OK);
                setResultData(String.valueOf(res));
                uow.addDataToDB(nr1, nr2, op, res);
            }
        }
    }

    public static Double calculate(Double d1, Double d2, String op) {

        BigDecimal answer = BigDecimal.ZERO;
        try {
            BigDecimal val1 = BigDecimal.valueOf(d1);
            BigDecimal val2 = BigDecimal.valueOf(d2);
            if (op.equals("+")) {
                answer = val1.add(val2);
            } else if (op.equals("-")) {
                answer = val1.subtract(val2);
            } else if (op.equals("*")) {
                answer = val1.multiply(val2);
            } else if (op.equals("/")) {
                answer = val1.divide(val2, 10, RoundingMode.HALF_UP);
            }
        } catch (ArithmeticException a) {
            return Double.NaN;
        } catch (NumberFormatException n) {
            return Double.NaN;
        }
        return answer.doubleValue();
    }
}
