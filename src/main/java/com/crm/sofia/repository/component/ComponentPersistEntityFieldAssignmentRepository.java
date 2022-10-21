package com.crm.sofia.repository.component;

import com.crm.sofia.model.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentPersistEntityFieldAssignmentRepository extends BaseRepository<ComponentPersistEntityFieldAssignment> {

    List<ComponentPersistEntityFieldAssignment> findByEntityIdAndEntityType(String entityId, String entityType);

    void deleteComponentPersistEntityFieldAssignmentByEntityIdAndEntityType(String entityId, String entityType);

}
