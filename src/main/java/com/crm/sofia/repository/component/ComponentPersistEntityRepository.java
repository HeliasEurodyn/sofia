package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentPersistEntityRepository extends BaseRepository<ComponentPersistEntity> {

    @Query(" SELECT cpe FROM ComponentPersistEntity cpe " +
            " LEFT OUTER JOIN FETCH cpe.persistEntity pe " +
            " WHERE pe.id =:id ")
    List<ComponentPersistEntity> findComponentEntitiesOfTableId(@Param("id") String id);

}
