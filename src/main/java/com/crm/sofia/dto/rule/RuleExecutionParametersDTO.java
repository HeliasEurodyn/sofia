package com.crm.sofia.dto.rule;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@JsonIgnoreProperties({"rule","createdOn","createdBy","shortOrder","version"})
public class RuleExecutionParametersDTO extends BaseDTO {

    private String ruleId;

    private String joinType;

    private RuleDTO rule;

}
