package com.crm.sofia.model.sofia.expression.expressionUnits;

import com.crm.sofia.model.sofia.expression.ExprUnit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ExprGetSqlValParameter extends ExprUnit {

    static private Integer exprUnitLength = 9;
    static private String exprUnitString = "getSqlVal";
    EntityManager entityManager;

    public static ExprGetSqlValParameter exrtactExprUnit(String expression, Integer expressionPosition, EntityManager entityManager) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprGetSqlValParameter exprUnit = new ExprGetSqlValParameter();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
            exprUnit.setEntityManager(entityManager);
            return exprUnit;
        }

        return null;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Integer getExprUnitLength() {
        return exprUnitLength;
    }


    @Override
    public Object getResult() {

        Object keyObject = (String) this.childExprUnit.getResult();
        if (keyObject == null) {
            return null;
        }

        if (!(keyObject instanceof String)) {
            return null;
        }

        String queryString = (String) keyObject;
        queryString = queryString.replace("#q#","'");
        Query query = entityManager.createNativeQuery(queryString);
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
