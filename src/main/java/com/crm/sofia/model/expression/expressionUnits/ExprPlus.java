package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

public class ExprPlus extends ExprUnit {

    static private final Integer exprUnitLength = 1;
    static private final String exprUnitString = "+";


    public static ExprPlus exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprPlus exprUnit = new ExprPlus();
            exprUnit.setExpressionPart("+");
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

        String firstString = null;
        String secondString = null;

        Object firstObject = this.leftChildExprUnit.getResult();
        if (firstObject == null) {
            return null;
        }

        Object secondObject = this.rightChildExprUnit.getResult();
        if (secondObject == null) {
            return null;
        }

        if (firstObject instanceof String || firstObject instanceof String) {
            firstString = firstObject.toString();
            secondString = secondObject.toString();
            return (firstString.concat(secondString));
        } else if (firstObject instanceof Double || firstObject instanceof Double) {
            return (Double) firstObject + (Double) firstObject;
        } else {
            return null;
        }

    }

}
