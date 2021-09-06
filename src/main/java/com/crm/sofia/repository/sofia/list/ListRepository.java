package com.crm.sofia.repository.sofia.list;

import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ListRepository extends BaseRepository<ListEntity> {
    List<ListEntity> findAll();

    ListEntity findFirstByName(String name);

    @Query(" SELECT DISTINCT l.instanceVersion FROM ListEntity l " +
            " WHERE l.id =:id ")
    public String getInstanceVersion(@Param("id") Long id);

    @Query(" SELECT l.id FROM ListEntity l " +
            " WHERE l.jsonUrl =:jsonUrl ")
    public List<Long> getIdByJsonUrl(@Param("jsonUrl") String jsonUrl);

    @Modifying
    @Transactional
    @Query(value = "UPDATE list SET instance_version = instance_version + 1", nativeQuery = true)
    void increaseInstanceVersions();

    @Query(" SELECT DISTINCT ls.script FROM ListEntity l " +
            " INNER JOIN l.listScripts ls " +
            " WHERE l.id =:id ")
    List<String> getJavaScriptsById(@Param("id")Long id);

    @Query(" SELECT DISTINCT l.script FROM ListEntity l " +
            " WHERE l.id =:id ")
    public String getListScript(@Param("id") Long id);


    @Query(" SELECT DISTINCT ls.script FROM ListEntity l " +
            " INNER JOIN l.listCssList ls " +
            " WHERE l.id =:id ")
    public List<String> getCssScriptsById(@Param("id") Long id);

}
