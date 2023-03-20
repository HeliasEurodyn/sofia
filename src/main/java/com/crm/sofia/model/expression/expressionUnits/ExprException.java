package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.exception.ExpressionException;
import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

public class ExprException extends ExprUnit {

    static private final Integer exprUnitLength = 9;
    static private final String exprUnitString = "exception";

    public static ExprException exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprException exprUnit = new ExprException();
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
        Object keyObject = (String) this.childExprUnit.getResult(exprInitParameters);
        if (keyObject == null) {
            throw new ExpressionException("");
        }

        if (!(keyObject instanceof String)) {
            throw new ExpressionException("");
        }

        String message = (String) keyObject;

        throw new ExpressionException(message);
    }
}
