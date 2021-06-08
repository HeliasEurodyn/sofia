package com.crm.sofia.repository.report;

import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.report.Report;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends BaseRepository<Report> {
    List<Report> findAll();
}
