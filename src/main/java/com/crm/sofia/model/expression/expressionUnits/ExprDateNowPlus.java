package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class ExprDateNowPlus extends ExprUnit {

    static private Integer exprUnitLength = 11;
    static private String exprUnitString = "dateNowPlus";
//    private AppConstants.Types.ExprUnitReturningType exprUnitReturningType = AppConstants.Types.ExprUnitReturningType.Instant ;

    public static ExprDateNowPlus exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprDateNowPlus exprUnit = new ExprDateNowPlus();
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

        Object daysObject = (Double) this.childExprUnit.getResult();
        if (daysObject == null) {
            return null;
        }

        if (!(daysObject instanceof Double)){
            return null;
        }

        Double days = (Double) daysObject;

        return Instant.now().plus(days.intValue(), ChronoUnit.DAYS);
    }

//    @Override
//    public String checkBranchTypes(){
//
//        if(this.getChildExprUnit() == null) {
//            return "Error! parameter of " + exprUnitString + " should not be empty.";
//        }
//
//        AppConstants.Types.ExprUnitReturningType returningType = this.getChildExprUnit().getReturningType();
//
//        if(returningType.equals(AppConstants.Types.ExprUnitReturningType.Integer)) {
//            return "Error! parameter of " + exprUnitString + " should be an Integer.";
//        }
//
//
//        return "";
//
//    }

}
