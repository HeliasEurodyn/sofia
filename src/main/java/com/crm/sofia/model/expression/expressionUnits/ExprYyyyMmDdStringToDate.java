package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ExprYyyyMmDdStringToDate extends ExprUnit {

    static private Integer exprUnitLength = 20;
    static private String exprUnitString = "yyyyMmDdStringToDate";

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Instant ;

    public static ExprYyyyMmDdStringToDate exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprYyyyMmDdStringToDate exprUnit = new ExprYyyyMmDdStringToDate();
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

        Object dateStringObject = (String) this.childExprUnit.getResult();
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
        return dateTimeFormatter.parse(dateString+"0000", Instant::from);
    }

}
