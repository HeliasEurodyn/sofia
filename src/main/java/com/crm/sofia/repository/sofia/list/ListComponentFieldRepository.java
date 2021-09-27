package com.crm.sofia.repository.sofia.list;

import com.crm.sofia.model.sofia.list.ListComponentField;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ListComponentFieldRepository extends BaseRepository<ListComponentField> {
    List<ListComponentField> findAll();
}

