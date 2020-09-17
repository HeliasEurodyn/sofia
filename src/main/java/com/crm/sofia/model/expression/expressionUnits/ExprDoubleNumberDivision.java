package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

public class ExprDoubleNumberDivision extends ExprUnit {

    static private Integer exprUnitLength = 14;
    static private String exprUnitString = "numberDivision";
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Double ;

    public static ExprDoubleNumberDivision exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprDoubleNumberDivision exprUnit = new ExprDoubleNumberDivision();
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

        Object firstNumberObject = this.leftChildExprUnit.getResult();
        if (firstNumberObject == null) {
            return null;
        }

        if (!(firstNumberObject instanceof Double)){
            return null;
        }

        Object secondNumberObject =  this.rightChildExprUnit.getResult();
        if (secondNumberObject == null) {
            return null;
        }

        if (!(secondNumberObject instanceof Double)){
            return null;
        }

        Double firstNumber = (Double) firstNumberObject;
        Double secondNumber = (Double) secondNumberObject;

        return firstNumber/secondNumber;
    }


}
