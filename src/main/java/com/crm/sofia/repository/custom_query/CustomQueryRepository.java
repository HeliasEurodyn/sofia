package com.crm.sofia.repository.custom_query;

import com.crm.sofia.model.custom_query.CustomQuery;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomQueryRepository extends BaseRepository<CustomQuery> {

    List<CustomQuery> findAll();
}
