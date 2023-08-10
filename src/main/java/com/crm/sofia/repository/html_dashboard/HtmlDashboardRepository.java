package com.crm.sofia.repository.html_dashboard;

import com.crm.sofia.model.html_dashboard.HtmlDashboard;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HtmlDashboardRepository extends BaseRepository<HtmlDashboard> {
    List<HtmlDashboard> findAll();

    @Query(" SELECT DISTINCT l.id FROM HtmlDashboard l ")
    public List<String> getListIds();

    @Query(" SELECT DISTINCT l.script FROM HtmlDashboard l " +
            " WHERE l.id =:id ")
    public String getListScript(@Param("id") String id);

    @Query(" SELECT DISTINCT l.scriptMin FROM HtmlDashboard l " +
            " WHERE l.id =:id ")
    String getListMinScript(String id);


}
