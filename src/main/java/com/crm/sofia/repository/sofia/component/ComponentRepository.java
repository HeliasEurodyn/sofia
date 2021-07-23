package com.crm.sofia.repository.sofia.component;

import com.crm.sofia.model.sofia.component.Component;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComponentRepository extends BaseRepository<Component> {

    List<Component> findAll();

}
