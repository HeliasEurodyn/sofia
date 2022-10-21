package com.crm.sofia.mapper.report;

import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.report.Report;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ReportMapper extends BaseMapper<ReportDTO, Report> {
}
