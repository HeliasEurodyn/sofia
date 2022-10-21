package com.crm.sofia.mapper.settings;

import com.crm.sofia.dto.sofia.settings.SettingsDto;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.settings.Settings;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class SettingsMapper extends BaseMapper<SettingsDto, Settings> {
}
