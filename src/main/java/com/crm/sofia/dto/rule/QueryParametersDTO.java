package com.crm.sofia.dto.rule;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class QueryParametersDTO {

    List<RuleExecutionParametersDTO> ruleExecParameters;

    List<QueryFieldDTO> queryFields;

    public List<RuleFieldDTO> getUniqueRuleFields() {
        return ruleExecParameters.stream()
                .flatMap(RuleExecutionParametersDTO::getUniqueRuleFields)
                .distinct()
                .collect(Collectors.toList());
    }
}
