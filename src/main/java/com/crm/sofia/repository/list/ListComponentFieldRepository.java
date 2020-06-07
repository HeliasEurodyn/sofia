package com.crm.sofia.repository.list;

import com.crm.sofia.model.list.ListComponentField;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ListComponentFieldRepository extends BaseRepository<ListComponentField> {
    List<ListComponentField> findAll();
}


