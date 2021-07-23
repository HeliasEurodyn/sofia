package com.crm.sofia.mapper.sofia.dashboard;

import com.crm.sofia.dto.sofia.dashboard.DashboardDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.dashboard.Dashboard;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DashboardMapper extends BaseMapper<DashboardDTO, Dashboard> {
}
