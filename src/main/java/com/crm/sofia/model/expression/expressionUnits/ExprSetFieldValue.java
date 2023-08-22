package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import java.math.BigInteger;

public class ExprSetFieldValue extends ExprUnit {

    static private Integer exprUnitLength = 13;
    static private String exprUnitString = "setFieldValue";

    public static ExprSetFieldValue exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprSetFieldValue exprUnit = new ExprSetFieldValue();
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
        String fieldName = (String) this.leftChildExprUnit.getResult(exprInitParameters);
        String[] fieldParts = fieldName.split("\\.");

        Object fieldValue =  this.rightChildExprUnit.getResult(exprInitParameters);

        exprInitParameters.getComponentDTO().flatComponentPersistEntityTree()
                .filter(cpe -> cpe.getCode().equals(fieldParts[0]))
                .forEach(cpe -> {
            cpe.getComponentPersistEntityFieldList().stream()
                    .filter(cpef -> cpef.getPersistEntityField().getName().equals(fieldParts[1]))
                    .forEach(cpef -> {
                        cpef.setValue(fieldValue);
            });
        });

        return fieldValue;
    }
}
