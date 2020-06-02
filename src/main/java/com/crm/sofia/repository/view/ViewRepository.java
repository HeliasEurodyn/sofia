package com.crm.sofia.repository.view;

import com.crm.sofia.model.view.View;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository extends BaseRepository<View> {

    List<View> findAll();

}

