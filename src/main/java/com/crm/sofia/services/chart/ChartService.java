package com.crm.sofia.services.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.chart.ChartFieldDTO;
import com.crm.sofia.mapper.chart.ChartMapper;
import com.crm.sofia.model.chart.Chart;
import com.crm.sofia.repository.chart.ChartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChartService {

    private final ChartRepository chartRepository;
    private final ChartMapper chartMapper;
    private final ChartDynamicQueryService chartDynamicQueryService;

    public ChartService(ChartRepository chartRepository,
                        ChartMapper chartMapper,
                        ChartDynamicQueryService chartDynamicQueryService) {
        this.chartRepository = chartRepository;
        this.chartMapper = chartMapper;
        this.chartDynamicQueryService = chartDynamicQueryService;
    }

    public ChartDTO getObject(Long id) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }
        ChartDTO chartDTO = this.chartMapper.map(optionalchart.get());
        List<ChartFieldDTO> chartFieldDTOS = this.chartDynamicQueryService.getData(chartDTO.getChartFieldList(), chartDTO.getQuery());
        chartDTO.setChartFieldList(chartFieldDTOS);
        return chartDTO;
    }

}
