package com.crm.sofia.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ExprUnit implements Serializable {

    private Integer expressionPosition;

    protected String expressionPart = "'";
    protected Integer priority;
    protected Boolean isOnTree = false;

    protected ExprUnit parentExprUnit = null;
    protected ExprUnit leftChildExprUnit = null;
    protected ExprUnit rightChildExprUnit = null;
    protected ExprUnit childExprUnit = null;

    public abstract Integer getExprUnitLength();

    public ExprUnit getTopParrent() {
        if (parentExprUnit == null) {
            return this;
        } else {
            return this.parentExprUnit.getTopParrent();
        }
    }

    public abstract Object getResult(ExprInitParameters exprInitParameters);
}
