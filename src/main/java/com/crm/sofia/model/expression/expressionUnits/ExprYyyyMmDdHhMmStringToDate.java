package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ExprYyyyMmDdHhMmStringToDate extends ExprUnit {

    static private Integer exprUnitLength = 24;
    static private String exprUnitString = "yyyyMmDdHhMmStringToDate";

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Instant ;

    public static ExprYyyyMmDdHhMmStringToDate exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprYyyyMmDdHhMmStringToDate exprUnit = new ExprYyyyMmDdHhMmStringToDate();
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
    public Object getResult(ExprInitParameters exprInitParameters) {

        Object dateStringObject = (String) this.childExprUnit.getResult(exprInitParameters);
        if (dateStringObject == null) {
            return null;
        }

        if (!(dateStringObject instanceof String)){
            return null;
        }

        String dateString = (String) dateStringObject;

                    DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                            .appendPattern("yyyyMMddHHmm")
                            .toFormatter()
                            .withZone(ZoneOffset.UTC);
        return dateTimeFormatter.parse(dateString, Instant::from);
    }

}
