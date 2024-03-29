package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ExprSystemParameter extends ExprUnit {

    static private Integer exprUnitLength = 15;
    static private String exprUnitString = "systemParameter";

    public static ExprSystemParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprSystemParameter exprUnit = new ExprSystemParameter();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
         //   exprUnit.setParameters(systemParameters);
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

        Object systemParameterObject = (String) this.childExprUnit.getResult(exprInitParameters);
        if (systemParameterObject == null) {
            return null;
        }

        if (!(systemParameterObject instanceof String)){
            return null;
        }

        String systemParameter = (String) systemParameterObject;
        if(exprInitParameters.getSystemParameters().containsKey(systemParameter)){
            return exprInitParameters.getSystemParameters().get(systemParameter);
        } else {
            return null;
        }

    }

}
