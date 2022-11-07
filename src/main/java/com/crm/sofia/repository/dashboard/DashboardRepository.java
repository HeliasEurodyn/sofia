package com.crm.sofia.repository.dashboard;

import com.crm.sofia.model.dashboard.Dashboard;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends BaseRepository<Dashboard> {

    List<Dashboard> findAll();

}
