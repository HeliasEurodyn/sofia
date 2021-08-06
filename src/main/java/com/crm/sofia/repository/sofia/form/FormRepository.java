package com.crm.sofia.repository.sofia.form;

import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormRepository extends BaseRepository<FormEntity> {
    List<FormEntity> findAll();

    @Query(" SELECT DISTINCT fs.script FROM FormEntity f " +
            " INNER JOIN f.formScripts fs " +
            " WHERE f.id =:id ")
    public List<String> getFormJavaScriptsByFormId(@Param("id") Long id);

    @Query(" SELECT DISTINCT fs.script FROM FormEntity f " +
            " INNER JOIN f.formCssList fs " +
            " WHERE f.id =:id ")
    public List<String> getFormCssScriptsByFormId(@Param("id") Long id);

    @Query(" SELECT DISTINCT f.script FROM FormEntity f " +
            " WHERE f.id =:id ")
    public String getFormScript(@Param("id") Long id);

    @Query(" SELECT DISTINCT f.instanceVersion FROM FormEntity f " +
            " WHERE f.id =:id ")
    public String getInstanceVersion(@Param("id") Long id);

    @Query(" SELECT f FROM FormEntity f " +
            " WHERE f.jsonUrl =:jsonUrl ")
    public List<FormEntity> getByJsonUrl(@Param("jsonUrl") String jsonUrl);

}
