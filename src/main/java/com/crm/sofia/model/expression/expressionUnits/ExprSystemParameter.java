package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

import java.util.Map;


public class ExprSystemParameter extends ExprUnit {

    static private Integer exprUnitLength = 15;
    static private String exprUnitString = "systemParameter";
    private Map<String,Object> parameters;

    public static ExprSystemParameter exrtactExprUnit(String expression, Integer expressionPosition, Map<String,Object> systemParameters) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprSystemParameter exprUnit = new ExprSystemParameter();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            exprUnit.setParameters(systemParameters);
            return exprUnit;
        }

        return null;
    }

    public void setParameters(Map<String,Object> parameters){
        this.parameters = parameters;
    }

    @Override
    public Integer getExprUnitLength() {
        return exprUnitLength;
    }


    @Override
    public Object getResult() {

        Object systemParameterObject = (String) this.childExprUnit.getResult();
        if (systemParameterObject == null) {
            return null;
        }

        if (!(systemParameterObject instanceof String)){
            return null;
        }

        String systemParameter = (String) systemParameterObject;
        if(this.parameters.containsKey(systemParameter)){
            return this.parameters.get(systemParameter);
        } else {
            return null;
        }

    }

}
