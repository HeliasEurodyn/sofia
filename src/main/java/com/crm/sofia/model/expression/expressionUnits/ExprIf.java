package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

public class ExprIf extends ExprUnit {

    static private Integer exprUnitLength = 2;
    static private String exprUnitString = "if";

    public static ExprIf exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprIf exprUnit = new ExprIf();
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

        Object decisionExpression = this.childExprUnit.getResult();

        if (decisionExpression == null) {
            return null;
        }

        if (!(decisionExpression instanceof Boolean)) {
            return null;
        }

        if ((Boolean) decisionExpression) {
            return this.leftChildExprUnit.getResult();
        } else {
            return this.rightChildExprUnit.getResult();
        }

    }
}
