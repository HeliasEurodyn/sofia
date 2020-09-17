package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.expression.ExprUnit;

public class ExprStringConcat extends ExprUnit {

    static private Integer exprUnitLength = 12;
    static private String exprUnitString = "stringConcat";
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.String ;

    public static ExprStringConcat exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprStringConcat exprUnit = new ExprStringConcat();
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

        Object firstStringObject = this.leftChildExprUnit.getResult();
        if (firstStringObject == null) {
            return null;
        }

        if (!(firstStringObject instanceof String)){
            return null;
        }

        Object secondStringObject =  this.rightChildExprUnit.getResult();
        if (secondStringObject == null) {
            return null;
        }

        if (!(secondStringObject instanceof String)){
            return null;
        }

        String firstString = (String) firstStringObject;
        String secondString = (String) secondStringObject;

        return firstString + secondString;
    }
}
