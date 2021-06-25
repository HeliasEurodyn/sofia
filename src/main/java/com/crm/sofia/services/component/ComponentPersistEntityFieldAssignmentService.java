package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldAssignmentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.component.ComponentPersistEntityFieldAssignmentMapper;
import com.crm.sofia.model.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.component.ComponentPersistEntityFieldAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComponentPersistEntityFieldAssignmentService {

    private final ComponentPersistEntityFieldAssignmentRepository componentPersistEntityFieldAssignmentRepository;
    private final ComponentPersistEntityFieldAssignmentMapper componentPersistEntityFieldAssignmentMapper;

    public ComponentPersistEntityFieldAssignmentService(
            ComponentPersistEntityFieldAssignmentRepository componentPersistEntityFieldAssignmentRepository,
            ComponentPersistEntityFieldAssignmentMapper componentPersistEntityFieldAssignmentMapper) {
        this.componentPersistEntityFieldAssignmentRepository = componentPersistEntityFieldAssignmentRepository;
        this.componentPersistEntityFieldAssignmentMapper = componentPersistEntityFieldAssignmentMapper;
    }

    public void saveFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList,String entityType, Long entityId) {
        this.deleteByFormIdAndEntityType(entityId,entityType);
        this.saveFieldAssignmentsTree(componentPersistEntityList,entityType, entityId);
    }

    public List<ComponentPersistEntityDTO> retrieveFormFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                        String entityType,
                                                                        Long entityId ) {

        List<ComponentPersistEntityFieldAssignment> fieldAssignments =
                componentPersistEntityFieldAssignmentRepository.findByEntityIdAndEntityType(entityId, entityType);

        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs =
                this.componentPersistEntityFieldAssignmentMapper.map(fieldAssignments);

        this.retrieveFieldAssignmentsTree(componentPersistEntityList,
                fieldAssignmentDTOs);

        return componentPersistEntityList;
    }

    public void deleteByFormIdAndEntityType(Long entityId, String entityType) {
        componentPersistEntityFieldAssignmentRepository.deleteComponentPersistEntityFieldAssignmentByEntityIdAndEntityType(entityId,entityType);
    }

    public void createFieldAssignment(ComponentPersistEntityFieldDTO componentPersistEntityField, String entityType) {
        ComponentPersistEntityFieldAssignmentDTO assignment = new ComponentPersistEntityFieldAssignmentDTO();
        assignment = new ComponentPersistEntityFieldAssignmentDTO();
        assignment.setDescription(componentPersistEntityField.getPersistEntityField().getName());
        assignment.setType(componentPersistEntityField.getPersistEntityField().getType());
        assignment.setEntityType(entityType);
        assignment.setVisible(true);
        assignment.setEditable(true);
        assignment.setRequired(false);

        componentPersistEntityField.setAssignment(assignment);
    }

    private void saveFieldAssignmentsTree(List<ComponentPersistEntityDTO> componentPersistEntityList,String entityType, Long entityId) {
        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignments = new ArrayList<>();
        componentPersistEntityList
                .stream()
                .forEach(persistEntity -> {

                    persistEntity.getComponentPersistEntityFieldList()
                            .stream()
                            .forEach(persistEntityField -> {
                                        if (persistEntityField.getAssignment() == null) {
                                            this.createFieldAssignment(persistEntityField,entityType);
                                        }
                                        Long fieldId = persistEntityField.getId();
                                        ComponentPersistEntityFieldAssignmentDTO fieldAssignment =
                                                persistEntityField.getAssignment();
                                        fieldAssignment.setEntityId(entityId);
                                        fieldAssignment.setEntityType(entityType);
                                        fieldAssignment.setFieldId(fieldId);
                                        fieldAssignments.add(fieldAssignment);
                                    }
                            );

                    if (persistEntity.getComponentPersistEntityList() != null) {
                        this.saveFieldAssignmentsTree(persistEntity.getComponentPersistEntityList(),entityType, entityId);
                    }
                });
        this.postObjects(fieldAssignments);
    }

    private void postObjects(List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs) {
        List<ComponentPersistEntityFieldAssignment> fieldAssignments =
                this.componentPersistEntityFieldAssignmentMapper.mapDTOs(fieldAssignmentDTOs);
        componentPersistEntityFieldAssignmentRepository.saveAll(fieldAssignments);
    }

    private void retrieveFieldAssignmentsTree(
            List<ComponentPersistEntityDTO> componentPersistEntityList,
            List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs) {

        componentPersistEntityList
                .stream()
                .forEach(componentPersistEntity -> {
                    componentPersistEntity.getComponentPersistEntityFieldList()
                            .stream()
                            .forEach(componentPersistEntityField -> {
                                        Long fieldId = componentPersistEntityField.getId();
                                        Optional<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentOptional =
                                                fieldAssignmentDTOs
                                                        .stream()
                                                        .filter(fieldAssignment -> fieldAssignment.getFieldId().equals(fieldId))
                                                        .findFirst();

                                        if (fieldAssignmentOptional.isPresent()) {
                                            ComponentPersistEntityFieldAssignmentDTO fieldAssignment = fieldAssignmentOptional.get();
                                            componentPersistEntityField.setAssignment(fieldAssignment);
                                        }
                                    }
                            );

                    if (componentPersistEntity.getComponentPersistEntityList() != null) {
                        this.retrieveFieldAssignmentsTree(componentPersistEntity.getComponentPersistEntityList(),
                                fieldAssignmentDTOs);
                    }
                });
    }

}
