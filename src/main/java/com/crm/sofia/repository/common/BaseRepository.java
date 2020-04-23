package com.crm.sofia.repository.common;

import com.crm.sofia.model.common.BaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface BaseRepository<M extends BaseEntity> extends PagingAndSortingRepository<M, Long> {

}
