package com.crm.sofia.repository.cityscape.cve_search;

import com.crm.sofia.model.cityscape.cve_search.CveSearchSettings;
import com.crm.sofia.model.sofia.chart.Chart;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface CveSearchSettingsRepository extends BaseRepository<CveSearchSettings> {
    public List<CveSearchSettings> findAll();
}
