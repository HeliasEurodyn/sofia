package com.crm.sofia.repository.report;

import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.report.Report;
import com.crm.sofia.model.report.ReportParameter;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends BaseRepository<Report> {
    List<Report> findAll();

    @Query(" SELECT r.reportUuid FROM Report r " +
            " WHERE r.id =:id ")
    String findUuid(@Param("id")long id);

    @Query(" SELECT r.reportType FROM Report r " +
            " WHERE r.id =:id ")
    String findType(@Param("id")long id);

    @Query(" SELECT r.reportFileData FROM Report r " +
            " WHERE r.id =:id ")
    byte[] findReportFileData(long id);

    @Query(" SELECT r.reportParameterList FROM Report r " +
            " WHERE r.id =:id ")
    List<ReportParameter> getReportParametersById(long id);

}
