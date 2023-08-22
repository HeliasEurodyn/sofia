package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.exception.ExpressionException;
import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

public class ExprThrowExceptionParameter extends ExprUnit {

    static private Integer exprUnitLength = 14;
    static private String exprUnitString = "throwException";

    public static ExprThrowExceptionParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprThrowExceptionParameter exprUnit = new ExprThrowExceptionParameter();
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

        Object keyObject = this.childExprUnit.getResult(exprInitParameters);
        if (keyObject == null) {
            return null;
        }

        if (!(keyObject instanceof String)) {
            return null;
        }
        String message = (String) keyObject;
        return new ExpressionException(message);
    }

}
