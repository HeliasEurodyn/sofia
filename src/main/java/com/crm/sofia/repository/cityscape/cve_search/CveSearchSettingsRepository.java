package com.crm.sofia.repository.cityscape.cve_search;

import com.crm.sofia.model.cityscape.cve_search.CveSearchSettings;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CveSearchSettingsRepository extends BaseRepository<CveSearchSettings> {
    List<CveSearchSettings> findAll();
}
