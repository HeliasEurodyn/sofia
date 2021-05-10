package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.Component;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComponentRepository extends BaseRepository<Component> {

    List<Component> findAll();

    @Query(" SELECT c FROM Component c " +
            " LEFT OUTER JOIN FETCH c.componentPersistEntityList l " +
            " LEFT OUTER JOIN FETCH l.persistEntity lt " +
            " WHERE lt.id =:id ")
    List<Component> findComponentsThatContainTable(@Param("id") Long id);

}
