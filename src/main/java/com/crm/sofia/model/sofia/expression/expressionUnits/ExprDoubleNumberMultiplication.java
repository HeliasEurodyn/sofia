package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

import java.math.BigInteger;

public class ExprDoubleNumberMultiplication extends ExprUnit {

    static private Integer exprUnitLength = 20;
    static private String exprUnitString = "numberMultiplication";

//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Double ;


    public static ExprDoubleNumberMultiplication exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprDoubleNumberMultiplication exprUnit = new ExprDoubleNumberMultiplication();
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
    public Object getResult() {

        Double firstNumber;
        Double secondNumber;

        Object firstNumberObject = this.leftChildExprUnit.getResult();
        if (firstNumberObject == null) {
            return null;
        }

        Object secondNumberObject =  this.rightChildExprUnit.getResult();
        if (secondNumberObject == null) {
            return null;
        }

        if (firstNumberObject instanceof Double){
            firstNumber = (Double) firstNumberObject;
        }else if (firstNumberObject instanceof String){
            firstNumber = Double.valueOf((String)firstNumberObject);
        }else if (firstNumberObject instanceof BigInteger){
            BigInteger firstNumberBigInt = (BigInteger) firstNumberObject;
            firstNumber = firstNumberBigInt.doubleValue();
        }else if (firstNumberObject instanceof BigInteger){
            Integer firstNumberInt = (Integer) firstNumberObject;
            firstNumber = firstNumberInt.doubleValue();
        } else{
            return null;
        }

        if (secondNumberObject instanceof Double){
            secondNumber = (Double) secondNumberObject;
        }else if (secondNumberObject instanceof String){
            secondNumber = Double.valueOf((String)secondNumberObject);
        }else if (secondNumberObject instanceof BigInteger){
            BigInteger secondNumberBigInt = (BigInteger) secondNumberObject;
            secondNumber = secondNumberBigInt.doubleValue();
        }else if (secondNumberObject instanceof BigInteger){
            Integer secondNumberInt = (Integer) secondNumberObject;
            secondNumber = secondNumberInt.doubleValue();
        } else{
            return null;
        }

        return firstNumber*secondNumber;
    }


}
