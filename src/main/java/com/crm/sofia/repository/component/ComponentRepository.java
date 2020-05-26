package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.Component;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ComponentRepository extends BaseRepository<Component> {

    List<Component> findAll();

}
