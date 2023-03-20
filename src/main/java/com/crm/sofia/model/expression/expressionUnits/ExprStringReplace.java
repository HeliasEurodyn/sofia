package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

public class ExprStringReplace extends ExprUnit {

    static private Integer exprUnitLength = 13;
    static private String exprUnitString = "stringReplace";
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.String ;

    public static ExprStringReplace exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprStringReplace exprUnit = new ExprStringReplace();
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
    public Object getResult(ExprInitParameters exprInitParameters) {

        Object firstStringObject = this.leftChildExprUnit.getResult(exprInitParameters);
        if (firstStringObject == null) {
            return null;
        }

        if (!(firstStringObject instanceof String)){
            return null;
        }

        Object secondStringObject =  this.rightChildExprUnit.getResult(exprInitParameters);
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
