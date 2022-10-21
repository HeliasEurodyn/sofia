package com.crm.sofia.repository.settings;

import com.crm.sofia.model.settings.Settings;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettingsRepository extends BaseRepository<Settings> {
    List<Settings> findAll();

    @Query(" SELECT DISTINCT s.sidebarImage FROM Settings s " +
            " WHERE s.id =:id ")
    public String getSidebarImage(@Param("id") String id);

    @Query(" SELECT DISTINCT s.loginImage FROM Settings s " +
            " WHERE s.id =:id ")
    public String getLoginImage(@Param("id") String id);

    @Query(" SELECT DISTINCT s.icon FROM Settings s " +
            " WHERE s.id =:id ")
    public String getIcon(@Param("id") String id);

    @Query(" SELECT DISTINCT s.name FROM Settings s " +
            " WHERE s.id =:id ")
    public String getName(@Param("id") String id);
}
