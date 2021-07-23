package com.crm.sofia.repository.sofia.chart;

import com.crm.sofia.model.sofia.chart.Chart;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ChartRepository extends BaseRepository<Chart> {

    public List<Chart> findAll();

}
