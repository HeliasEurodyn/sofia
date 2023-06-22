package com.crm.sofia.mapper.rule;

import com.crm.sofia.dto.rule.RuleFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.rule.RuleField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class RuleFieldMapper extends BaseMapper<RuleFieldDTO, RuleField> {
}
