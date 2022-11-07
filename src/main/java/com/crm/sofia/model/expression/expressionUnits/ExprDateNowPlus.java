package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class ExprDateNowPlus extends ExprUnit {

    static private Integer exprUnitLength = 11;
    static private String exprUnitString = "dateNowPlus";

    public static ExprDateNowPlus exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprDateNowPlus exprUnit = new ExprDateNowPlus();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            return exprUnit;
        }

        return null;

    }

    @Override
    public Integer getExprUnitLength() {
        return exprUnitLength;
    }


    @Override
    public Object getResult() {

        Object daysObject = (Double) this.childExprUnit.getResult();
        if (daysObject == null) {
            return null;
        }

        if (!(daysObject instanceof Double)){
            return null;
        }

        Double days = (Double) daysObject;

        return Instant.now().plus(days.intValue(), ChronoUnit.DAYS);
    }

}
