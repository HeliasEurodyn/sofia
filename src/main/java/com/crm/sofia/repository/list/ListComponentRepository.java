package com.crm.sofia.repository.list;

import com.crm.sofia.model.list.ListComponent;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ListComponentRepository extends BaseRepository<ListComponent> {
    List<ListComponent> findAll();
}

