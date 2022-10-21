package com.crm.sofia.repository.html_dashboard;

import com.crm.sofia.model.sofia.html_dashboard.HtmlDashboard;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HtmlDashboardRepository extends BaseRepository<HtmlDashboard> {
    List<HtmlDashboard> findAll();
}
