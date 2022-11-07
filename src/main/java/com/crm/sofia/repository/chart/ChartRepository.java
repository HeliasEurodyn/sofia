package com.crm.sofia.repository.chart;

import com.crm.sofia.model.chart.Chart;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends BaseRepository<Chart> {

    public List<Chart> findAll();

}
