package com.crm.sofia.dto.expression;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ExprUnitDTO {
    private String type;
    private String expressionPart;
    private ExprUnitDTO leftChildExprUnit = null;
    private ExprUnitDTO rightChildExprUnit = null;
    private ExprUnitDTO childExprUnit = null;
}
