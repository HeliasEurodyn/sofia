package com.crm.sofia.repository.sofia.component;

import com.crm.sofia.model.sofia.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ComponentPersistEntityFieldAssignmentRepository extends BaseRepository<ComponentPersistEntityFieldAssignment> {

    List<ComponentPersistEntityFieldAssignment> findByEntityIdAndEntityType(String entityId, String entityType);

    void deleteComponentPersistEntityFieldAssignmentByEntityIdAndEntityType(String entityId, String entityType);

}
