package com.crm.sofia.repository.sofia.list;

import com.crm.sofia.model.sofia.list.ListComponent;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ListComponentRepository extends BaseRepository<ListComponent> {
    List<ListComponent> findAll();
}

