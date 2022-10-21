package com.crm.sofia.mapper.dashboard;

import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.dashboard.Dashboard;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DashboardMapper extends BaseMapper<DashboardDTO, Dashboard> {
}
