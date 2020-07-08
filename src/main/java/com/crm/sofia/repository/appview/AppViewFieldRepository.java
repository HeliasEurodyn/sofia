package com.crm.sofia.repository.appview;

import com.crm.sofia.model.appview.AppViewField;
import com.crm.sofia.repository.common.BaseRepository;
import com.crm.sofia.repository.persistEntity.PersistEntityFieldRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppViewFieldRepository extends PersistEntityFieldRepository<AppViewField> {
}
