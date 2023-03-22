package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import java.util.HashMap;
import java.util.Map;


public class ExprImportColumnParameter extends ExprUnit {

    static private Integer exprUnitLength = 12;
    static private String exprUnitString = "importColumn";

    public static ExprImportColumnParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprImportColumnParameter exprUnit = new ExprImportColumnParameter();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
          //  exprUnit.setDataset(dataset);
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
            return null;
        }

        if (!(keyObject instanceof String)) {
            return null;
        }

        String key = (String) keyObject;
        if (exprInitParameters.getSystemParameters().containsKey(key.toUpperCase())) {
            return exprInitParameters.getSystemParameters().get(key.toUpperCase());
        } else {
            return null;
        }
    }

}
