package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public Object getResult() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong file type");
    }
}
