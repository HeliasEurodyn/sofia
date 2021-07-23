package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;
import lombok.Data;

@Data
public class ExprParameter extends ExprUnit {

    private Integer currentExprUnitLength = 0;
    private String currentExprUnitString = "";
    Object parameterValue;
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Any ;

    public static ExprParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        String expressionPart = "[";
        String expressionPositionString = "";
        Boolean expressionClosedNormally = false;

        if ((expression.length()-1) < expressionPosition+2 ) {
            return null;
        }

        if(!expression.substring(expressionPosition, expressionPosition + 1).equals("[")){
            return null;
        }

        if(expression.substring(expressionPosition, expressionPosition + 2).equals("]")){
            return null;
        }

        for (int i = expressionPosition+1; i < expression.length(); i++) {
            expressionPositionString = expression.substring(i, i + 1);
            expressionPart += expressionPositionString;

            if(expressionPositionString.equals("]"))
            {
                expressionClosedNormally = true;
                break;
            }
        }

        if(expressionClosedNormally){
            ExprParameter exprUnit = new ExprParameter();
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
    public Object getResult(){
        return parameterValue;
    }
}
