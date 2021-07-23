package com.crm.sofia.repository.sofia.component;

import com.crm.sofia.model.sofia.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.common.BaseRepository;

import java.util.List;

public interface ComponentPersistEntityFieldAssignmentRepository extends BaseRepository<ComponentPersistEntityFieldAssignment> {

    List<ComponentPersistEntityFieldAssignment> findByEntityIdAndEntityType(Long entityId, String entityType);

    void deleteComponentPersistEntityFieldAssignmentByEntityIdAndEntityType(Long entityId, String entityType);

}
