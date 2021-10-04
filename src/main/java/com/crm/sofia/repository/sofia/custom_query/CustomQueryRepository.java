package com.crm.sofia.repository.sofia.custom_query;

import com.crm.sofia.model.sofia.custom_query.CustomQuery;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomQueryRepository extends BaseRepository<CustomQuery> {

    List<CustomQuery> findAll();
}
