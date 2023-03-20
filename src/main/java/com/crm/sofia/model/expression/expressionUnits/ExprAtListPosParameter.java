package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import java.util.ArrayList;
import java.util.List;


public class ExprAtListPosParameter extends ExprUnit {

    static private Integer exprUnitLength = 9;
    static private String exprUnitString = "atListPos";

    public static ExprAtListPosParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprAtListPosParameter exprUnit = new ExprAtListPosParameter();
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

        Object arrayObject = this.leftChildExprUnit.getResult(exprInitParameters);
        if (arrayObject == null) {
            return null;
        }

        Double pos = (Double) this.rightChildExprUnit.getResult(exprInitParameters);
        if (pos == null) {
            return null;
        }

        if (arrayObject instanceof ArrayList){
            List arrayObjectR = (ArrayList) arrayObject;
            return arrayObjectR.get(pos.intValue());
        } else{
            return null;
        }

    }

}
