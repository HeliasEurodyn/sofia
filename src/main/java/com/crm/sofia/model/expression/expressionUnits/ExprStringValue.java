package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;
import lombok.Data;

@Data
public class ExprStringValue extends ExprUnit {

    private Integer currentExprUnitLength = 0;
    private String currentExprUnitString = "";

    public static ExprStringValue exrtactExprUnit(String expression, Integer expressionPosition) {

        String expressionPart = "'";
        String expressionPositionString = "";
        Boolean expressionClosedNormally = false;

        if (expression.length() < expressionPosition ) {
            return null;
        }

        if(!expression.substring(expressionPosition, expressionPosition + 1).equals("'")){
            return null;
        }

        for (int i = expressionPosition+1; i < expression.length(); i++) {
            expressionPositionString = expression.substring(i, i + 1);
            expressionPart += expressionPositionString;

            if(expressionPositionString.equals("'"))
            {
                expressionClosedNormally = true;
                break;
            }

        }

        if(expressionClosedNormally){
            ExprStringValue exprUnit = new ExprStringValue();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            exprUnit.setCurrentExprUnitLength(expressionPart.length());
            exprUnit.setCurrentExprUnitString(expressionPart);
            return exprUnit;
        }

        return null;
    }

    @Override
    public Integer getExprUnitLength() {
        return getCurrentExprUnitLength();
    }

    @Override
    public Object getResult() {
        return currentExprUnitString.substring(1,currentExprUnitString.length()-1);
    }

}
