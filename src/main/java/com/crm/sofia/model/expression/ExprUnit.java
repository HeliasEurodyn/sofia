package com.crm.sofia.model.expression;

import com.crm.sofia.config.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ExprUnit {

    //    private String expression;
    private Integer expressionPosition;

    //    static private Integer exprUnitLength;
    //    static private String exprUnitString;
    //    protected String expressionPart;

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

    public abstract Object getResult();

//    public abstract String checkBranchTypes();
//    public abstract AppConstants.Types.ExprUnitReturningType getReturningType();

//    public abstract void joinTree(List<ExprUnit> exprUnits, int listPosition, int priority);
}
