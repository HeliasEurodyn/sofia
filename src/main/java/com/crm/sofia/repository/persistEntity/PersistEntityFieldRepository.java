package com.crm.sofia.repository.persistEntity;

import com.crm.sofia.model.persistEntity.PersistEntity;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface PersistEntityFieldRepository<M extends PersistEntityField> extends PagingAndSortingRepository<M, Long> {
}

