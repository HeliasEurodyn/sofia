package com.crm.sofia.repository.form;

import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.model.form.FormScript;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormRepository extends BaseRepository<FormEntity> {
    List<FormEntity> findAll();

//    @Query( " SELECT f FROM FormEntity f " +
//            " INNER JOIN FETCH f.component c " +
//            " INNER JOIN FETCH c.componentPersistEntityList l " +
//            " WHERE f.id =:id " +
//            " AND l.persistEntity.entitytype = 'Table' " +
//            " AND l.allowSave = true " )
//    FormEntity retrieveFormForSave(@Param("id") Long id);

    @Query( " SELECT DISTINCT fs.script FROM FormEntity f " +
            " INNER JOIN f.formScripts fs " +
            " WHERE f.id =:id ")
    public List<String> getFormScriptsByFormId(@Param("id") Long id);

    @Query( " SELECT DISTINCT f.script FROM FormEntity f " +
            " WHERE f.id =:id ")
    public String getFormScript(@Param("id") Long id);

}
