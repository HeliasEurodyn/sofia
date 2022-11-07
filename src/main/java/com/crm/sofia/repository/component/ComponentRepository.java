package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.Component;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends BaseRepository<Component> {

    List<Component> findAll();

}
