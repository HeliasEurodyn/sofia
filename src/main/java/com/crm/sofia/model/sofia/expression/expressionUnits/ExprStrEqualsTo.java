package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

import java.math.BigInteger;

public class ExprStrEqualsTo extends ExprUnit {

    static private Integer exprUnitLength = 11;
    static private String exprUnitString = "strEqualsTo";

    public static ExprStrEqualsTo exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprStrEqualsTo exprUnit = new ExprStrEqualsTo();
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

        String firstString;
        String secondString;

        Object firstObject = this.leftChildExprUnit.getResult();
        if (firstObject == null) {
            return null;
        }

        Object secondObject =  this.rightChildExprUnit.getResult();
        if (secondObject == null) {
            return null;
        }

        firstString = firstObject.toString();
        secondString = secondObject.toString();

        return (firstString.equals(secondString));
    }
}
