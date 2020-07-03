package com.crm.sofia.repository.appview;

import com.crm.sofia.model.appview.AppView;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppViewRepository extends BaseRepository<AppView> {
    List<AppView> findAll();
}
