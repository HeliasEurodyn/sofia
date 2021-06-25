package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ComponentPersistEntityFieldAssignmentRepository extends BaseRepository<ComponentPersistEntityFieldAssignment> {

    List<ComponentPersistEntityFieldAssignment> findByEntityIdAndEntityType(Long entityId, String entityType);

    void deleteComponentPersistEntityFieldAssignmentByEntityIdAndEntityType(Long entityId, String entityType);

}
