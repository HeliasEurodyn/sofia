package com.crm.sofia.services.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.chart.ChartFieldDTO;
import com.crm.sofia.dto.list.base.ListComponentFieldDTO;
import com.crm.sofia.mapper.chart.ChartMapper;
import com.crm.sofia.model.chart.Chart;
import com.crm.sofia.model.expression.ExprResponse;
import com.crm.sofia.native_repository.chart.ChartNativeRepository;
import com.crm.sofia.repository.chart.ChartRepository;
import com.crm.sofia.services.expression.ExpressionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public ChartDTO getObject(String id, Map<String, String> parameters) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }

        ChartDTO chartDTO = this.chartMapper.map(optionalchart.get());

        /* Calc Default Values */
        for (ListComponentFieldDTO filterDto: chartDTO.getFilterList()) {

            if (filterDto.getDefaultValue() == null) continue;
            if (filterDto.getDefaultValue().equals("")) continue;

            ExprResponse exprResponse = expressionService.create(filterDto.getDefaultValue());
            if (!exprResponse.getError()) {
                Object defaultFieldValue = exprResponse.getExprUnit().getResult();
                if(filterDto.getType().equals("list")) {
                    filterDto.setDefaultValue((defaultFieldValue==null?"":defaultFieldValue.toString()));
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

        if(fieldValue == null){
            return null;
        }

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

    public List<ChartFieldDTO> getData(String id, Map<String, String> parameters) {
        Optional<Chart> optionalchart = this.chartRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Chart does not exist");
        }

        ChartDTO chartDTO = this.chartMapper.map(optionalchart.get());

        return this.chartNativeRepository.getData(chartDTO.getChartFieldList(), chartDTO.getQuery(), parameters);
    }
}
