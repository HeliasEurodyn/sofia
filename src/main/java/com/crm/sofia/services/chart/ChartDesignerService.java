package com.crm.sofia.services.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.chart.ChartFieldDTO;
import com.crm.sofia.mapper.chart.ChartMapper;
import com.crm.sofia.model.sofia.chart.Chart;
import com.crm.sofia.native_repository.chart.ChartNativeRepository;
import com.crm.sofia.repository.chart.ChartRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ChartDesignerService {

    private final ChartRepository chartRepository;
    private final ChartMapper chartMapper;
    private final ChartNativeRepository chartNativeRepository;
    private final JWTService jwtService;

    public ChartDesignerService(ChartRepository chartRepository,
                                ChartMapper chartMapper,
                                ChartNativeRepository chartNativeRepository,
                                JWTService jwtService) {
        this.chartRepository = chartRepository;
        this.chartMapper = chartMapper;
        this.chartNativeRepository = chartNativeRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public List<ChartFieldDTO> generateDataFields(String sql) {
        List<ChartFieldDTO> chartFieldList = this.chartNativeRepository.generateFields(sql);

        return this.chartNativeRepository.getData(chartFieldList, sql, new HashMap<String, String>());
    }

    @Transactional
    public List<ChartFieldDTO> getData(String id) {
        ChartDTO chartDTO = this.getObject(id);
        String query = chartDTO.getQuery();
        query = query.replace("##asset_id##", this.jwtService.getUserId().toString());

        return this.chartNativeRepository.getData(chartDTO.getChartFieldList(), query,  new HashMap<String, String>());
    }

    public List<ChartDTO> getObject() {
        List<Chart> charts = this.chartRepository.findAll();
        return this.chartMapper.map(charts);
    }

    public ChartDTO getObject(String id) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }

        ChartDTO chart = this.chartMapper.map(optionalchart.get());
        String encQuery = Base64.getEncoder().encodeToString(chart.getQuery().getBytes(StandardCharsets.UTF_8));
        chart.setQuery(encQuery);
        return chart;
    }

    @Transactional
    public ChartDTO postObject(ChartDTO dto) {

        String decQuery = new String(Base64.getDecoder().decode(dto.getQuery()));
        dto.setQuery(decQuery);

        Chart chart = this.chartMapper.map(dto);
        Chart createdChart = this.chartRepository.save(chart);
        return this.chartMapper.map(createdChart);
    }

    public void deleteObject(String id) {
        Optional<Chart> optionalChart = this.chartRepository.findById(id);
        if (!optionalChart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }
        this.chartRepository.deleteById(optionalChart.get().getId());
    }

}
