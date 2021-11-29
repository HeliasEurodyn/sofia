package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

import java.util.HashMap;
import java.util.Map;


public class ExprImportColumnParameter extends ExprUnit {

    static private Integer exprUnitLength = 12;
    static private String exprUnitString = "importColumn";

    private Map<String, Object> dataset = new HashMap<>();

    public static ExprImportColumnParameter exrtactExprUnit(String expression, Integer expressionPosition, Map<String, Object> dataset) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprImportColumnParameter exprUnit = new ExprImportColumnParameter();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            exprUnit.setDataset(dataset);
            return exprUnit;
        }

        return null;
    }

    public void setDataset(Map<String, Object> dataset) {
        this.dataset = dataset;
    }

    @Override
    public Integer getExprUnitLength() {
        return exprUnitLength;
    }


    @Override
    public Object getResult() {

        Object keyObject = (String) this.childExprUnit.getResult();
        if (keyObject == null) {
            return null;
        }

        if (!(keyObject instanceof String)) {
            return null;
        }

        String key = (String) keyObject;
        if (this.dataset.containsKey(key.toUpperCase())) {
            return this.dataset.get(key.toUpperCase());
        } else {
            return null;
        }
    }

}
