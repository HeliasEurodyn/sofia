package com.crm.sofia.services.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldAssignmentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.sofia.component.ComponentPersistEntityFieldAssignmentMapper;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.repository.sofia.component.ComponentPersistEntityFieldAssignmentRepository;
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

    public void saveFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList,String entityType, String entityId) {
        this.deleteByIdAndEntityType(entityId,entityType);
        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignments =
                generateFieldAssignmentsFromTree(componentPersistEntityList, entityType, entityId);
        this.postObjects(fieldAssignments);
    }

    public List<ComponentPersistEntityDTO> retrieveFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                    String entityType,
                                                                    String entityId ) {

        List<ComponentPersistEntityFieldAssignment> fieldAssignments =
                componentPersistEntityFieldAssignmentRepository.findByEntityIdAndEntityType(entityId, entityType);

        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs =
                this.componentPersistEntityFieldAssignmentMapper.map(fieldAssignments);

        this.retrieveFieldAssignmentsTree(componentPersistEntityList,
                fieldAssignmentDTOs);

        return componentPersistEntityList;
    }

    public void deleteByIdAndEntityType(String entityId, String entityType) {
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

    private List<ComponentPersistEntityFieldAssignmentDTO> generateFieldAssignmentsFromTree(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                                            String entityType,
                                                                                            String entityId) {
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
                                        String fieldId = persistEntityField.getId();
                                        ComponentPersistEntityFieldAssignmentDTO fieldAssignment =
                                                persistEntityField.getAssignment();
                                        fieldAssignment.setEntityId(entityId);
                                        fieldAssignment.setEntityType(entityType);
                                        fieldAssignment.setFieldId(fieldId);
                                        fieldAssignments.add(fieldAssignment);
                                    }
                            );

                    if (persistEntity.getComponentPersistEntityList() != null) {
                        List<ComponentPersistEntityFieldAssignmentDTO> curFieldAssignments =
                                this.generateFieldAssignmentsFromTree(persistEntity.getComponentPersistEntityList(),entityType, entityId);
                        fieldAssignments.addAll(curFieldAssignments);
                    }
                });

        return fieldAssignments;
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
                                        String fieldId = componentPersistEntityField.getId();
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
