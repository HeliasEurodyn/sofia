package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class ExprGetSqlValParameter extends ExprUnit {

    static private Integer exprUnitLength = 9;
    static private String exprUnitString = "getSqlVal";

    public static ExprGetSqlValParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprGetSqlValParameter exprUnit = new ExprGetSqlValParameter();
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

        Object keyObject = (String) this.childExprUnit.getResult(exprInitParameters);
        if (keyObject == null) {
            return null;
        }

        if (!(keyObject instanceof String)) {
            return null;
        }

        String queryString = (String) keyObject;
        queryString = queryString.replace("#q#","'");
        Query query = exprInitParameters.getEntityManager().createNativeQuery(queryString);
        List<Object> queryResults = query.getResultList();

        if (queryResults.isEmpty()) {
            return null;
        }

        if (queryResults.size() == 0) {
            return null;
        }

        if (queryResults.get(0) instanceof Object[]) {
            Object queryResultData =  queryResults.get(0);
            Object[] queryResult = (Object[]) queryResultData;
            return queryResult[0];
        }
        else {
            return queryResults.get(0);
        }

    }

}
