package com.crm.sofia.repository.persistEntity;

import com.crm.sofia.model.persistEntity.PersistEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface PersistEntityRepository<M extends PersistEntity> extends PagingAndSortingRepository<M, Long> {
}

