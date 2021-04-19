package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldAssignmentDTO;
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

    public void extractAndSaveFieldAssignments(List<ComponentPersistEntityDTO> componentPersistEntityList, Long formId) {

        this.deleteByFormId(formId);

        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignments = new ArrayList<>();

        componentPersistEntityList
                .stream()
                .forEach(persistEntity -> {
                    persistEntity.getComponentPersistEntityFieldList()
                            .stream()
                            .forEach(
                                    persistEntityField -> {
                                        Long fieldId = persistEntityField.getId();
                                        ComponentPersistEntityFieldAssignmentDTO fieldAssignment =
                                                persistEntityField.getAssignment();
                                        fieldAssignment.setFormId(formId);
                                        fieldAssignment.setFieldId(fieldId);
                                        fieldAssignments.add(fieldAssignment);
                                    }
                            );

                });

        this.postObjects(fieldAssignments);
    }

    private void postObjects(List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs) {
        List<ComponentPersistEntityFieldAssignment> fieldAssignments =
                this.componentPersistEntityFieldAssignmentMapper.mapDTOs(fieldAssignmentDTOs);
        componentPersistEntityFieldAssignmentRepository.saveAll(fieldAssignments);
    }

    public FormDTO retrieveFieldAssignments(FormDTO formDTO) {
        Long formId = formDTO.getId();

        List<ComponentPersistEntityFieldAssignment> fieldAssignments =
                componentPersistEntityFieldAssignmentRepository.findByFormId(formId);

        List<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentDTOs =
                this.componentPersistEntityFieldAssignmentMapper.map(fieldAssignments);

        formDTO.getComponent().getComponentPersistEntityList()
                .stream()
                .forEach(persistEntity -> {
                    persistEntity.getComponentPersistEntityFieldList()
                            .stream()
                            .forEach(
                                    componentPersistEntityField -> {
                                        Long fieldId = componentPersistEntityField.getId();
                                        Optional<ComponentPersistEntityFieldAssignmentDTO> fieldAssignmentOptional =
                                           fieldAssignmentDTOs
                                                .stream()
                                                .filter(fieldAssignment -> fieldAssignment.getFieldId().equals(fieldId))
                                                .findFirst();

                                        if(fieldAssignmentOptional.isPresent()){
                                            ComponentPersistEntityFieldAssignmentDTO fieldAssignment = fieldAssignmentOptional.get();
                                            componentPersistEntityField.setAssignment(fieldAssignment);
                                        }

                                    }
                            );

                });


        return formDTO;
    }

    public void deleteByFormId(Long formId) {
        componentPersistEntityFieldAssignmentRepository.deleteComponentPersistEntityFieldAssignmentByFormId(formId);
    }
}
