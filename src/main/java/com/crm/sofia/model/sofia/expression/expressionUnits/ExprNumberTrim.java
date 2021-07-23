package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

public class ExprNumberTrim extends ExprUnit {

    static private Integer exprUnitLength = 10;
    static private String exprUnitString = "numberTrim";


//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Double ;

    public static ExprNumberTrim exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprNumberTrim exprUnit = new ExprNumberTrim();
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

        Object numberObject = this.leftChildExprUnit.getResult();
        if (numberObject == null) {
            return null;
        }

        if (!(numberObject instanceof Double)){
            return null;
        }

        Object trimSizeObject =  this.rightChildExprUnit.getResult();
        if (trimSizeObject == null) {
            return null;
        }

        if (!(trimSizeObject instanceof Integer)){
            return null;
        }

        Double number = (Double) numberObject;
        Integer trimSize = (Integer) trimSizeObject;

        return trim(number,trimSize);
    }


    Double trim(Double number,Integer trimSize ){
        Double multiplier = Math.pow(10, trimSize);
        int tempNumber = (int)(number * multiplier);
        double shortNumber = ((double)tempNumber)/multiplier;
        return shortNumber;
    }

}
