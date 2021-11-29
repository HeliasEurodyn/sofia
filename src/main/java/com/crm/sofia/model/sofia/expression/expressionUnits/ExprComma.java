package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

public class ExprComma extends ExprUnit {

    static private Integer exprUnitLength = 1;
    static private String exprUnitString = ",";

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = null ;



    public static ExprComma exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprComma exprUnit = new ExprComma();
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
    public Object getResult(){
        return null;
    }
//    @Override
//    public String checkBranchTypes(){
//        return "Error! Comma should not be on branches.";
//    }

}
