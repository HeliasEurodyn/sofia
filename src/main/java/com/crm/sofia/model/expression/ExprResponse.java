package com.crm.sofia.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExprResponse {
    String errorMessage = "";
    Boolean error = false;
    String expression = "";
    ExprUnit exprUnit = null;
}
