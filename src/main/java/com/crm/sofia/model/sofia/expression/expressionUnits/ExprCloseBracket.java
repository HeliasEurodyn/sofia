package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

public class ExprCloseBracket extends ExprUnit {

    static private Integer exprUnitLength = 1;
    static private String exprUnitString = ")";
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = null ;


    public static ExprCloseBracket exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprCloseBracket exprUnit = new ExprCloseBracket();
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
//        return "Error! CloseBracket should not be on branches.";
//    }

}
