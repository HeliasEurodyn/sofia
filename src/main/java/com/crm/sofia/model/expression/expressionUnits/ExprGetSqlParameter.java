package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ExprGetSqlParameter extends ExprUnit {

    static private Integer exprUnitLength = 6;
    static private String exprUnitString = "getSql";

    public static ExprGetSqlParameter exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprGetSqlParameter exprUnit = new ExprGetSqlParameter();
            exprUnit.setExpressionPart(expressionPart);
            exprUnit.setExpressionPosition(expressionPosition);
          //  exprUnit.setEntityManager(entityManager);
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

            List<List<Object>> responses = new ArrayList<>();
            for (Object queryResultLine : queryResults) {
                Object[] queryResultLineArray = (Object[]) queryResultLine;
                List<Object> line = new ArrayList<>();
                for (Object queryResult : queryResultLineArray) {
                    line.add(queryResult);
                }
                responses.add(line);
            }

            return responses;
        }
        else {
            List<List<Object>> responses = new ArrayList<>();
            for (Object queryResult : queryResults) {
                List<Object> line = new ArrayList<>();
                line.add(queryResult);
                responses.add(line);
            }

            return responses;
        }

    }

}
