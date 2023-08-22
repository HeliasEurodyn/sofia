package com.crm.sofia.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExprResponse implements Serializable {

    String errorMessage = "";
    Boolean error = false;
    String expression = "";
    ExprUnit exprUnit = null;

    public Object getResult(ExprInitParameters exprInitParameters) {
            return this.exprUnit.getResult(exprInitParameters);
    }

}
