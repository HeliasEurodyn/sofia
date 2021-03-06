package com.crm.sofia.repository.persistEntity;

import com.crm.sofia.model.persistEntity.PersistEntity;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersistEntityRepository extends BaseRepository<PersistEntity> {

    List<PersistEntity> findByEntitytype(String entityType);

}

