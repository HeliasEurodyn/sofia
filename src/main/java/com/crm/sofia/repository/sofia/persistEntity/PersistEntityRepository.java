package com.crm.sofia.repository.sofia.persistEntity;

import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersistEntityRepository extends BaseRepository<PersistEntity> {

    List<PersistEntity> findByEntitytypeOrderByModifiedOn(String entityType);

}

