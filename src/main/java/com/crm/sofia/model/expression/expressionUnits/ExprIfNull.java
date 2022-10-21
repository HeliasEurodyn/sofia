package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;


public class ExprIfNull extends ExprUnit {

    static private Integer exprUnitLength = 6;
    static private String exprUnitString = "ifNull";

    public static ExprIfNull exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprIfNull exprUnit = new ExprIfNull();
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

        Object left = this.leftChildExprUnit.getResult();
        Object right =  this.rightChildExprUnit.getResult();

        if (left == null) {
            return right;
        } else {
            return left;
        }
    }

}
