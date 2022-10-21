package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ExprDatePlus extends ExprUnit {

    static private Integer exprUnitLength = 8;
    static private String exprUnitString = "datePlus";

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Instant ;

    public static ExprDatePlus exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprDatePlus exprUnit = new ExprDatePlus();
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

        Object dateObject = (Instant) this.leftChildExprUnit.getResult();
        if (dateObject == null) {
            return null;
        }

        if (!(dateObject instanceof Instant)){
            return null;
        }

        Object daysObject = (Integer) this.rightChildExprUnit.getResult();
        if (daysObject == null) {
            return null;
        }

        if (!(daysObject instanceof Integer)){
            return null;
        }

        Instant date = (Instant) dateObject;

        Integer days = (Integer) daysObject;

        return date.plus(days, ChronoUnit.DAYS);
    }
}
