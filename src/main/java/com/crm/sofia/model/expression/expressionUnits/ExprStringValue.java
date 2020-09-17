package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.model.expression.ExprUnit;
import lombok.Data;

@Data
public class ExprStringValue extends ExprUnit {

//    static private Integer exprUnitLength = 0;
//    static private String exprUnitString = "";

    private Integer currentExprUnitLength = 0;
    private String currentExprUnitString = "";
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.String ;

    public static ExprStringValue exrtactExprUnit(String expression, Integer expressionPosition) {

        String expressionPart = "'";
        String expressionPositionString = "";
        String prevExpressionPositionString = "";
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

            if(expressionPositionString.equals("'") && !prevExpressionPositionString.equals("/") )
            {
                expressionClosedNormally = true;
                break;
            }
            prevExpressionPositionString = expression.substring(i, i + 1);
        }

        if(expressionClosedNormally){
            ExprStringValue exprUnit = new ExprStringValue();
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
