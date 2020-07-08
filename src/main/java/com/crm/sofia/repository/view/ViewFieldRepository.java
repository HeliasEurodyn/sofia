package com.crm.sofia.repository.view;

import com.crm.sofia.model.view.ViewField;
import com.crm.sofia.repository.common.BaseRepository;
import com.crm.sofia.repository.persistEntity.PersistEntityFieldRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewFieldRepository extends PersistEntityFieldRepository<ViewField> {
}
