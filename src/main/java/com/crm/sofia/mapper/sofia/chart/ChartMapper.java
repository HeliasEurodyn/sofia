package com.crm.sofia.mapper.sofia.chart;

import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.chart.Chart;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ChartMapper extends BaseMapper<ChartDTO, Chart> {
}