package com.crm.sofia.dto.rule;

import com.crm.sofia.dto.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class RuleDTO extends BaseDTO {

    private String code;

    private String name;

    private String description;

    private String expressionPreview;

    private List<RuleExpressionDTO> ruleExpressionList;

    public RuleDTO(String id, String name, Instant modifiedOn) {
        this.setId(id);
        this.name = name;
    }

    public Stream<RuleFieldDTO> getUniqueRuleFields() {
        return ruleExpressionList.stream()
                .flatMap(RuleExpressionDTO::getRuleFieldStream)
                .distinct();
    }

}
