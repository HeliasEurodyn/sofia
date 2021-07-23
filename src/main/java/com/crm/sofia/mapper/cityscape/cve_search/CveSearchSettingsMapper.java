package com.crm.sofia.mapper.cityscape.cve_search;

import com.crm.sofia.dto.cityscape.cve_search.CveSearchSettingsDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.cityscape.cve_search.CveSearchSettings;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class CveSearchSettingsMapper extends BaseMapper<CveSearchSettingsDTO, CveSearchSettings> {
}
