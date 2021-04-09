package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ComponentPersistEntityFieldAssignmentRepository extends BaseRepository<ComponentPersistEntityFieldAssignment> {

    List<ComponentPersistEntityFieldAssignment> findByFormId(Long formId);

    void deleteComponentPersistEntityFieldAssignmentByFormId(Long aLong);

}
