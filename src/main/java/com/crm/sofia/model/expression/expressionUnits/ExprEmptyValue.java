package com.crm.sofia.model.expression.expressionUnits;

import com.crm.sofia.model.expression.ExprUnit;
import lombok.Data;

@Data
public class ExprEmptyValue extends ExprUnit {

    public static ExprEmptyValue exrtactExprUnit() {
        return null;
    }

    @Override
    public Integer getExprUnitLength() {
        return 0;
    }

    @Override
    public Object getResult() {
        return null;
    }

}
