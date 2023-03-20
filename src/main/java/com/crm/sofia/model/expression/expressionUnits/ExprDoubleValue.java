package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class ExprDoubleValue extends ExprUnit {

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Double ;


    private Integer currentExprUnitLength = 0;
    private String currentExprUnitString = "";
    private Boolean isPossitive = true;

    public static ExprDoubleValue exrtactExprUnit(String expression, Integer expressionPosition) {

        String expressionPart = "";
        String expressionPositionString = "";

        Boolean isPossitive = true;
        Pattern pattern = Pattern.compile("(^\\d$)|(^\\.$)");

        if (expression.length() < expressionPosition ) {
            return null;
        }

        if(expression.substring(expressionPosition, expressionPosition + 1).equals("-")){
            expressionPosition += 1;
            isPossitive = false;
        }

        if(expression.substring(expressionPosition, expressionPosition + 1).equals(".")){
            return null;
        }

        for (int i = expressionPosition; i < expression.length(); i++) {
            expressionPositionString = expression.substring(i, i + 1);


            if(pattern.matcher(expressionPositionString).matches()){
                expressionPart += expressionPositionString;
            } else {
                break;
            }

        }

        if(expressionPositionString.equals(".")){
            return null;
        }

        if(expressionPart.length() > 0){
            ExprDoubleValue exprUnit = new ExprDoubleValue();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            if(isPossitive) exprUnit.setCurrentExprUnitLength(expressionPart.length());
            else exprUnit.setCurrentExprUnitLength(expressionPart.length()+1);
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
        Double valueDouble = Double.parseDouble(valueString);

        if(this.isPossitive) return valueDouble;
        else return (-1*valueDouble);

    }

}
