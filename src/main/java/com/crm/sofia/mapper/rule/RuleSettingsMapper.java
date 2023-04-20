package com.crm.sofia.mapper.rule;

import com.crm.sofia.dto.rule.RuleSettingsDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.rule.RuleSettings;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class RuleSettingsMapper extends BaseMapper<RuleSettingsDTO, RuleSettings> {
}
