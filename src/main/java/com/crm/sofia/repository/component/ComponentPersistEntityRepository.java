package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComponentPersistEntityRepository extends BaseRepository<ComponentPersistEntity> {

    @Query(" SELECT cpe FROM ComponentPersistEntity cpe " +
            " LEFT OUTER JOIN FETCH cpe.persistEntity pe " +
            " WHERE pe.id =:id ")
    List<ComponentPersistEntity> findComponentEntitiesOfTableId(@Param("id") Long id);

}
