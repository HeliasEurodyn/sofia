package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldAssignmentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.form.FormDTO;
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

    public void saveFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList, Long formId) {
        this.deleteByFormId(formId);
        this.saveFieldAssignmentsTree(componentPersistEntityList, formId);
    }

    public List<ComponentPersistEntityDTO> retrieveFormFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                        String entityType,
                                                                        Long entityId ) {

        List<ComponentPersistEntityFieldAssignment> fieldAssignments =
                componentPersistEntityFieldAssignmentRepository.findByFormId(entityId);

        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs =
                this.componentPersistEntityFieldAssignmentMapper.map(fieldAssignments);

        this.retrieveFieldAssignmentsTree(componentPersistEntityList,
                fieldAssignmentDTOs);

        return componentPersistEntityList;
    }

    public void deleteByFormId(Long formId) {
        componentPersistEntityFieldAssignmentRepository.deleteComponentPersistEntityFieldAssignmentByFormId(formId);
    }

    public void createFieldAssignment(ComponentPersistEntityFieldDTO componentPersistEntityField) {
        ComponentPersistEntityFieldAssignmentDTO assignment = new ComponentPersistEntityFieldAssignmentDTO();
        assignment = new ComponentPersistEntityFieldAssignmentDTO();
        assignment.setDescription(componentPersistEntityField.getPersistEntityField().getName());
        assignment.setType(componentPersistEntityField.getPersistEntityField().getType());
        assignment.setVisible(true);
        assignment.setEditable(true);
        assignment.setRequired(false);

        componentPersistEntityField.setAssignment(assignment);
    }

    private void saveFieldAssignmentsTree(List<ComponentPersistEntityDTO> componentPersistEntityList, Long formId) {
        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignments = new ArrayList<>();
        componentPersistEntityList
                .stream()
                .forEach(persistEntity -> {

                    persistEntity.getComponentPersistEntityFieldList()
                            .stream()
                            .forEach(persistEntityField -> {
                                        if (persistEntityField.getAssignment() == null) {
                                            this.createFieldAssignment(persistEntityField);
                                        }
                                        Long fieldId = persistEntityField.getId();
                                        ComponentPersistEntityFieldAssignmentDTO fieldAssignment =
                                                persistEntityField.getAssignment();
                                        fieldAssignment.setFormId(formId);
                                        fieldAssignment.setFieldId(fieldId);
                                        fieldAssignments.add(fieldAssignment);
                                    }
                            );

                    if (persistEntity.getComponentPersistEntityList() != null) {
                        this.saveFieldAssignmentsTree(persistEntity.getComponentPersistEntityList(), formId);
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
