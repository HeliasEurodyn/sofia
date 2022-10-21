package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

import java.util.UUID;

public class ExprUuid extends ExprUnit {

    static private final Integer exprUnitLength = 4;
    static private final String exprUnitString = "uuid";

    public static ExprUuid exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprUuid exprUnit = new ExprUuid();
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

        return UUID.randomUUID().toString();
    }
}
