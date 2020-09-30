package com.crm.sofia.repository.list;

import com.crm.sofia.model.list.ListEntity;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ListRepository extends BaseRepository<ListEntity> {
    List<ListEntity> findAll();

    ListEntity findFirstByName(String name);

}
