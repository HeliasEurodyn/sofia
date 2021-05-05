package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.mapper.form.FormMapper;
import com.crm.sofia.model.expression.ExprResponce;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.expression.ExpressionService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final JWTService jwtService;
    private final FormDynamicQueryService formDynamicQueryService;
    private final ComponentService componentService;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    private final ExpressionService expressionService;

    public FormService(FormRepository formRepository,
                       FormMapper formMapper,
                       JWTService jwtService,
                       FormDynamicQueryService formDynamicQueryService,
                       ComponentService componentService,
                       ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                       ExpressionService expressionService) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.jwtService = jwtService;
        this.formDynamicQueryService = formDynamicQueryService;
        this.componentService = componentService;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.expressionService = expressionService;
    }

    @Transactional
    public FormDTO postObject(FormDTO formDTO) {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setCreatedOn(Instant.now());
        formEntity.setModifiedOn(Instant.now());
        formEntity.setCreatedBy(jwtService.getUserId());
        formEntity.setModifiedBy(jwtService.getUserId());
        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(),
                        createdFormEntity.getId());

        return this.formMapper.map(createdFormEntity);
    }

    @Transactional
    public FormDTO putObject(FormDTO formDTO) {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setModifiedOn(Instant.now());
        formEntity.setModifiedBy(jwtService.getUserId());

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(),
                        createdFormEntity.getId());


        return this.formMapper.map(createdFormEntity);
    }


    public List<FormDTO> getObject() {
        List<FormEntity> formEntities = this.formRepository.findAll();
        return this.formMapper.map(formEntities);
    }

    public FormDTO getObject(Long id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }
        FormDTO formDTO = this.formMapper.map(optionalFormEntity.get());
        formDTO = this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(formDTO);
        return formDTO;
    }

    public FormDTO getObjectAndRetrieveData(Long formId, String selectionId) {
        FormDTO formDTO = this.getObject(formId);

        if (selectionId.equals("") || selectionId.equals("0")) {
            formDTO = this.runDefaultValueExpressions(formDTO);
        } else {
            this.formDynamicQueryService.retrieveComponentData(formDTO.getComponent(), selectionId);
            formDTO = this.setDefaultValueExpressionsOnTableComponents(formDTO);
        }

        return formDTO;
    }

    private FormDTO setDefaultValueExpressionsOnTableComponents(FormDTO formDTO) {
        List<ComponentPersistEntityDTO> filteredComponentPersistEntityList =
                formDTO.getComponent().getComponentPersistEntityList()
                        .stream()
                        .filter(cpe -> (cpe.getMultiDataLine()==null?false:cpe.getMultiDataLine()) == true)
                        .collect(Collectors.toList());

        this.runDefaultValueExpressionsOnTree(filteredComponentPersistEntityList);

        return formDTO;
    }

    private FormDTO runDefaultValueExpressions(FormDTO formDTO) {
        this.runDefaultValueExpressionsOnTree(formDTO.getComponent().getComponentPersistEntityList());
        return formDTO;
    }

    private void runDefaultValueExpressionsOnTree(List<ComponentPersistEntityDTO> componentPersistEntityList) {

        componentPersistEntityList
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getDefaultValue().equals(""))
                            .forEach(cpef -> {
                                ExprResponce exprResponce = expressionService.create(cpef.getAssignment().getDefaultValue());
                                if (!exprResponce.getError()) {
                                    Object fieldValue = exprResponce.getExprUnit().getResult();
                                    cpef.setValue(fieldValue);
                                }
                            });

                    if (cpe.getComponentPersistEntityList() != null) {
                        this.runDefaultValueExpressionsOnTree(cpe.getComponentPersistEntityList());
                    }

                });

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList()));

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList()));

    }

    @Transactional
    @Modifying
    public void deleteObject(Long id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "FormEntity does not exist");
        }
        this.componentPersistEntityFieldAssignmentService.deleteByFormId(optionalFormEntity.get().getId());
        this.formRepository.deleteById(optionalFormEntity.get().getId());
    }

    public String save(Long formId, Map<String, Map<String, Object>> parameters) throws Exception {

        /*Retrieve form from Database*/
        FormDTO formDTO = this.getObject(formId);

        /* Μap parameters to component. */
        componentService.mapParametersToComponentDTO(formDTO.getComponent().getComponentPersistEntityList(), parameters);

        return this.formDynamicQueryService.generateQueriesAndSave(
                formDTO.getComponent().getComponentPersistEntityList(),
                new ArrayList<>());

        /* Check Insert or Update. */
//        Boolean hasPrimaryKeyValue = componentService.hasPrimaryKeyValue(formDTO.getComponent());

        /*Send to formDynamicQueryService to generate the queries & Save*/
//        if (hasPrimaryKeyValue) {
//            return this.formDynamicQueryService.generateQueriesAndUpdate(
//                    formDTO.getComponent().getComponentPersistEntityList(),
//                    new ArrayList<>());
//        } else {
//            return this.formDynamicQueryService.generateQueriesAndInsert(
//                    formDTO.getComponent().getComponentPersistEntityList(),
//                    new ArrayList<>());
//        }
    }

}
