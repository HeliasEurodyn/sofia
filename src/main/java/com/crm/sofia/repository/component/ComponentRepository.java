package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.CustomComponent;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponentRepository extends BaseRepository<CustomComponent> {

    List<CustomComponent> findAll();


    @Query(" SELECT DISTINCT c " +
            " FROM CustomComponent c " +
            " JOIN FETCH c.customComponentFieldList  " +
            " WHERE c.id =:id ")
    Optional<CustomComponent> findCustomObjectById(@Param("id") Long id);

}
