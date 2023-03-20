package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class ExprIntegerValue extends ExprUnit {

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Integer ;

//    static private Integer exprUnitLength = 12;
//    static private String exprUnitString = "integerValue";

    private Integer currentExprUnitLength = 0;
    private String currentExprUnitString = "";
    private Boolean isPossitive = true;

    public static ExprIntegerValue exrtactExprUnit(String expression, Integer expressionPosition) {

        String expressionPart = "";
        String expressionPositionString = "";

        Boolean isPossitive = true;
        Pattern pattern = Pattern.compile("^\\d$");

        if (expression.length() < expressionPosition ) {
            return null;
        }

        if(expression.substring(expressionPosition, expressionPosition + 1).equals("-")){
            expressionPosition += 1;
            isPossitive = false;
        }


        for (int i = expressionPosition; i < expression.length(); i++) {
            expressionPositionString = expression.substring(i, i + 1);


            if(pattern.matcher(expressionPositionString).matches()){
                expressionPart += expressionPositionString;
            } else {
                break;
            }

        }

        if(expressionPart.length() > 0){
            ExprIntegerValue exprUnit = new ExprIntegerValue();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            exprUnit.setCurrentExprUnitLength(expressionPart.length());
            exprUnit.setCurrentExprUnitString(expressionPart);
            exprUnit.setIsPossitive(isPossitive);
            return exprUnit;
        }

        return null;
    }

    @Override
    public Integer getExprUnitLength() {
        return getCurrentExprUnitLength();
    }

    @Override
    public Object getResult(ExprInitParameters exprInitParameters) {
        String valueString = this.getCurrentExprUnitString();
        return Integer.parseInt(valueString);
    }

}
