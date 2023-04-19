package com.crm.sofia.mapper.rule;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.rule.RuleDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.rule.Rule;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {RuleExpressionMapper.class})
public abstract class RuleMapper extends BaseMapper<RuleDTO, Rule> {
}
