package com.crm.sofia.repository.dashboard;

import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.dashboard.Dashboard;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DashboardRepository extends BaseRepository<Dashboard> {

    List<Dashboard> findAll();

//    @Query("SELECT d FROM Dashboard d " +
//            "INNER JOIN FETCH d.dashboardAreaList dl " +
//            "INNER JOIN FETCH dl.dashboardItemList  " +
//            "WHERE d.id =:id ")
//    Dashboard findDashboardById(@Param("id") Long id);
}
