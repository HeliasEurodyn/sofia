package com.crm.sofia.repository.view;

import com.crm.sofia.model.view.View;
import com.crm.sofia.repository.common.BaseRepository;
import com.crm.sofia.repository.persistEntity.PersistEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository extends PersistEntityRepository<View> {

    List<View> findAll();

}

