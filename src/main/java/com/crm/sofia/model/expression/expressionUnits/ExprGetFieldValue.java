package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprUnit;

import java.util.Optional;

public class ExprGetFieldValue extends ExprUnit {

    static private Integer exprUnitLength = 13;
    static private String exprUnitString = "getFieldValue";

    public static ExprGetFieldValue exrtactExprUnit(String expression, Integer expressionPosition) {

        if (expression.length() < expressionPosition + exprUnitLength) {
            return null;
        }

        String expressionPart = expression.substring(expressionPosition, expressionPosition + exprUnitLength);
        if (expressionPart.equals(exprUnitString)) {
            ExprGetFieldValue exprUnit = new ExprGetFieldValue();
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
        String fieldName = (String) this.childExprUnit.getResult(exprInitParameters);
        String[] fieldParts = fieldName.split("\\.");

        Optional<ComponentPersistEntityDTO> optionalCpe = exprInitParameters.getComponentDTO().flatComponentPersistEntityTree()
                .filter(cpe -> cpe.getCode().equals(fieldParts[0]))
                .findFirst();

        if(!optionalCpe.isPresent()){
            return null;
        }

        Optional<ComponentPersistEntityFieldDTO> optionalCpef = optionalCpe.get().getComponentPersistEntityFieldList().stream()
                .filter(cpef -> cpef.getPersistEntityField().getName().equals(fieldParts[1]))
                .findFirst();

        return optionalCpef.map(ComponentPersistEntityFieldDTO::getValue).orElse(null);
    }
}
