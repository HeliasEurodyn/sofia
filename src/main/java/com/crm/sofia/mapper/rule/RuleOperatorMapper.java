package com.crm.sofia.mapper.rule;

import com.crm.sofia.dto.rule.RuleOperatorDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.rule.RuleOperator;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class RuleOperatorMapper extends BaseMapper<RuleOperatorDTO, RuleOperator> {
}
