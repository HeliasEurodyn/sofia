package com.crm.sofia.repository.sofia.form;

import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FormRepository extends BaseRepository<FormEntity> {
    List<FormEntity> findAll();

    @Query(" SELECT DISTINCT fs.script FROM FormEntity f " +
            " INNER JOIN f.formScripts fs " +
            " WHERE f.id =:id ")
    public List<String> getFormJavaScriptsByFormId(@Param("id") Long id);

    @Query(" SELECT DISTINCT f.id FROM FormEntity f ")
    public List<Long> getFormIds();

    @Query(" SELECT DISTINCT fs.script FROM FormEntity f " +
            " INNER JOIN f.formCssList fs " +
            " WHERE f.id =:id ")
    public List<String> getFormCssScriptsByFormId(@Param("id") Long id);

    @Query(" SELECT DISTINCT f.script FROM FormEntity f " +
            " WHERE f.id =:id ")
    public String getFormScript(@Param("id") Long id);

    @Query(" SELECT DISTINCT f.scriptMin FROM FormEntity f " +
            " WHERE f.id =:id ")
    public String getFormMinScript(@Param("id") Long id);

    @Query(" SELECT DISTINCT f.instanceVersion FROM FormEntity f " +
            " WHERE f.id =:id ")
    public String getInstanceVersion(@Param("id") Long id);

    @Query(" SELECT f.id FROM FormEntity f " +
            " WHERE f.jsonUrl =:jsonUrl ")
    public List<Long> getIdsByJsonUrl(@Param("jsonUrl") String jsonUrl);

    @Query(" SELECT f FROM FormEntity f " +
            " WHERE f.jsonUrl <> '' AND f.jsonUrl is not null ")
    public List<FormEntity> getIdsByExistingJsonUrls();


    @Modifying
    @Transactional
    @Query(value = "UPDATE form SET instance_version = instance_version + 1", nativeQuery = true)
    void increaseInstanceVersions();

    @Modifying
    @Query(value = "UPDATE FormEntity SET script = :script , scriptMin = :scriptMin  WHERE id = :id")
    void updateScripts(@Param("id") Long id, @Param("script") String script , @Param("scriptMin") String scriptMin);

}
