package com.crm.sofia.services.sofia.chart;

import com.crm.sofia.dto.sofia.chart.ChartDTO;
import com.crm.sofia.dto.sofia.chart.ChartFieldDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
import com.crm.sofia.mapper.sofia.chart.ChartMapper;
import com.crm.sofia.model.sofia.chart.Chart;
import com.crm.sofia.model.sofia.expression.ExprResponce;
import com.crm.sofia.native_repository.sofia.chart.ChartNativeRepository;
import com.crm.sofia.repository.sofia.chart.ChartRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.expression.ExpressionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ChartService {

    private final ChartRepository chartRepository;
    private final ChartMapper chartMapper;
    private final ChartNativeRepository chartNativeRepository;
    private final ExpressionService expressionService;

    public ChartService(ChartRepository chartRepository,
                        ChartMapper chartMapper,
                        ChartNativeRepository chartNativeRepository,
                        ExpressionService expressionService) {
        this.chartRepository = chartRepository;
        this.chartMapper = chartMapper;
        this.chartNativeRepository = chartNativeRepository;
        this.expressionService = expressionService;
    }

    public ChartDTO getObject(Long id, Map<String, String> parameters) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }

        ChartDTO chartDTO = this.chartMapper.map(optionalchart.get());

        /* Calc Default Values */
        for (ListComponentFieldDTO filterDto: chartDTO.getFilterList()) {

            if (filterDto.getDefaultValue() == null) continue;
            if (filterDto.getDefaultValue().equals("")) continue;

            ExprResponce exprResponce = expressionService.create(filterDto.getDefaultValue());
            if (!exprResponce.getError()) {
                Object defaultFieldValue = exprResponce.getExprUnit().getResult();
                if(filterDto.getType().equals("list")) {
                    filterDto.setDefaultValue(defaultFieldValue.toString());
                    Object fieldValue = this.tryRetrieveSqlListValue(defaultFieldValue);
                    filterDto.setFieldValue(fieldValue);
                } else {
                    filterDto.setFieldValue(defaultFieldValue);
                    filterDto.setDefaultValue(defaultFieldValue.toString());
                }
            }
        }

        /* Define Parameters */
        chartDTO.getFilterList()
                .stream()
                .filter(filter -> !filter.getCode().equals(""))
                .forEach(filter -> {
                parameters.put(filter.getCode(), (filter.getFieldValue() == null?"":filter.getFieldValue().toString()));
                });

        /* Calc Data */
        List<ChartFieldDTO> chartFieldDTOS = this.chartNativeRepository.getData(
                chartDTO.getChartFieldList(),
                chartDTO.getQuery(),
                parameters);
        chartDTO.setChartFieldList(chartFieldDTOS);
        chartDTO.setQuery("");
        return chartDTO;
    }

    public Object tryRetrieveSqlListValue(Object fieldValue){
        if ( Collection.class.isAssignableFrom( fieldValue.getClass() ) ){
            List<ArrayList> dataLines = (List<ArrayList>) fieldValue;
            if ( dataLines.size() == 0 ){
                return null;
            }
            if ( Collection.class.isAssignableFrom( dataLines.get(0).getClass() ) ) {
                List<ArrayList> dataLine = (List<ArrayList>) dataLines.get(0);
                if ( dataLine.size() == 0 ){
                    return null;
                }
                return dataLine.get(0);
            }
        }
        return null;
    }

    public List<ChartFieldDTO> getData(Long id, Map<String, String> parameters) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }

        ChartDTO chartDTO = this.chartMapper.map(optionalchart.get());

        return this.chartNativeRepository.getData(chartDTO.getChartFieldList(), chartDTO.getQuery(), parameters);
    }
}
