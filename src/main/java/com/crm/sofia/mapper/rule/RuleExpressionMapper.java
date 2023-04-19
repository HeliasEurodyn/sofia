package com.crm.sofia.mapper.rule;

import com.crm.sofia.dto.rule.RuleExpressionDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.rule.RuleExpression;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {RuleMapper.class})
public abstract class RuleExpressionMapper extends BaseMapper<RuleExpressionDTO, RuleExpression> {
}
