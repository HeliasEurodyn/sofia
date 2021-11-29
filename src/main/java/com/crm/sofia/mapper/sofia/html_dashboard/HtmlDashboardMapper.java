package com.crm.sofia.mapper.sofia.html_dashboard;


import com.crm.sofia.dto.sofia.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.html_dashboard.HtmlDashboard;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class HtmlDashboardMapper extends BaseMapper<HtmlDashboardDTO, HtmlDashboard> {
}
