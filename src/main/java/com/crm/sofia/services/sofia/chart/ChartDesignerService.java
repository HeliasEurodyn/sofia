package com.crm.sofia.services.sofia.chart;

import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.dto.sofia.chart.ChartFieldDTO;
import com.crm.sofia.mapper.sofia.chart.ChartMapper;
import com.crm.sofia.model.sofia.chart.Chart;
import com.crm.sofia.native_repository.sofia.chart.ChartNativeRepository;
import com.crm.sofia.repository.sofia.chart.ChartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChartDesignerService {

    private final ChartRepository chartRepository;
    private final ChartMapper chartMapper;
    private final ChartNativeRepository chartNativeRepository;

    public ChartDesignerService(ChartRepository chartRepository,
                                ChartMapper chartMapper,
                                ChartNativeRepository chartNativeRepository) {
        this.chartRepository = chartRepository;
        this.chartMapper = chartMapper;
        this.chartNativeRepository = chartNativeRepository;
    }

    @Transactional
    public List<ChartFieldDTO> generateDataFields(String sql) {
        List<ChartFieldDTO> chartFieldList = this.chartNativeRepository.generateFields(sql);
        return this.chartNativeRepository.getData(chartFieldList, sql);
    }

    @Transactional
    public List<ChartFieldDTO> getData(Long id) {
        ChartDTO chartDTO = this.getObject(id);
        return this.chartNativeRepository.getData(chartDTO.getChartFieldList(), chartDTO.getQuery());
    }

    public List<ChartDTO> getObject() {
        List<Chart> charts = this.chartRepository.findAll();
        return this.chartMapper.map(charts);
    }

    public ChartDTO getObject(Long id) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }
        return this.chartMapper.map(optionalchart.get());
    }

    @Transactional
    public ChartDTO postObject(ChartDTO dto) {
        Chart chart = this.chartMapper.map(dto);
        Chart createdChart = this.chartRepository.save(chart);
        return this.chartMapper.map(createdChart);
    }

    public void deleteObject(Long id) {
        Optional<Chart> optionalChart = this.chartRepository.findById(id);
        if (!optionalChart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }
        this.chartRepository.deleteById(optionalChart.get().getId());
    }

}
