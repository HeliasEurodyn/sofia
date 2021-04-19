package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;
import com.crm.sofia.services.auth.JWTService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;


public class ExprSystemParameter extends ExprUnit {

    static private Integer exprUnitLength = 15;
    static private String exprUnitString = "systemParameter";
    static public Map<String,String> systemParameters;

    public static ExprSystemParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprSystemParameter exprUnit = new ExprSystemParameter();
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

        Object systemParameterObject = (String) this.childExprUnit.getResult();
        if (systemParameterObject == null) {
            return null;
        }

        if (!(systemParameterObject instanceof String)){
            return null;
        }

        String systemParameter = (String) systemParameterObject;
        if(ExprSystemParameter.systemParameters.containsKey(systemParameter)){
            return ExprSystemParameter.systemParameters.get(systemParameter);
        } else {
            return null;
        }

    }

}
