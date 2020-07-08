package com.crm.sofia.repository.appview;

import com.crm.sofia.model.appview.AppView;
import com.crm.sofia.repository.common.BaseRepository;
import com.crm.sofia.repository.persistEntity.PersistEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppViewRepository extends PersistEntityRepository<AppView> {
    List<AppView> findAll();
}
