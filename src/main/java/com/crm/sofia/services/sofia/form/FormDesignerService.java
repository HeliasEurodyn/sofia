package com.crm.sofia.services.sofia.form;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.form.base.*;
import com.crm.sofia.mapper.sofia.form.designer.FormMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.repository.sofia.form.FormRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.component.ComponentPersistEntityFieldAssignmentService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@Service
public class FormDesignerService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final JWTService jwtService;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    private final FormCacheingService formCacheingService;
    private final FormJavascriptService formJavascriptService;

    public FormDesignerService(FormRepository formRepository,
                               FormMapper formMapper,
                               JWTService jwtService,
                               ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                               FormCacheingService formCacheingService,
                               FormJavascriptService formJavascriptService) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.jwtService = jwtService;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.formCacheingService = formCacheingService;
        this.formJavascriptService = formJavascriptService;
    }

    @Transactional
    public FormDTO postObject(FormDTO formDTO) throws Exception {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setCreatedOn(Instant.now());
        formEntity.setModifiedOn(Instant.now());
        formEntity.setCreatedBy(jwtService.getUserId());
        formEntity.setModifiedBy(jwtService.getUserId());

        Long instanceVersion = formEntity.getInstanceVersion();
        if (instanceVersion == null) {
            instanceVersion = 0L;
        } else {
            instanceVersion += 1L;
        }
        formEntity.setInstanceVersion(instanceVersion);

        String script = this.formJavascriptService.generateDynamicScript(formDTO);
        String scriptMin = this.formJavascriptService.minify(script);
        formEntity.setScript(script);
        formEntity.setScriptMin(scriptMin);

        formEntity.setScript(script);

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(), "form",
                        createdFormEntity.getId());

        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);
        return createdFormDTO;
    }

    @Transactional
    public FormDTO putObject(FormDTO formDTO) throws Exception {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setModifiedOn(Instant.now());
        formEntity.setModifiedBy(jwtService.getUserId());
        Long instanceVersion = formEntity.getInstanceVersion();
        if (instanceVersion == null) {
            instanceVersion = 0L;
        } else {
            instanceVersion += 1L;
        }
        formEntity.setInstanceVersion(instanceVersion);

        String script = this.formJavascriptService.generateDynamicScript(formDTO);
        String scriptMin = this.formJavascriptService.minify(script);
        formEntity.setScript(script);
        formEntity.setScriptMin(scriptMin);

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(), "form",
                        createdFormEntity.getId());

        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);
        this.formCacheingService.clearUiObject(createdFormDTO.getId());

        return createdFormDTO;
    }

    public List<FormDTO> getObject() {
        List<FormEntity> formEntities = this.formRepository.findAll();
        return this.formMapper.mapEntitiesForList(formEntities);
    }

    public FormDTO getObject(Long id) {

        /* Retrieve */
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Map */
        FormDTO formDTO = this.formMapper.map(optionalFormEntity.get());

        /* Retrieve Field Assignments */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );
        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        /* Shorting */
        formDTO.getFormTabs().sort(Comparator.comparingLong(FormTabDTO::getShortOrder));
        formDTO.getFormTabs().forEach(formTab -> {
            formTab.getFormAreas().sort(Comparator.comparingLong(FormAreaDTO::getShortOrder));
            formTab.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormControlDTO::getShortOrder));
                formArea.getFormControls().forEach(formControl -> {
                    if (formControl.getType().equals("table")) {
                        formControl.getFormControlTable().getFormControls().sort(Comparator.comparingLong(FormControlTableControlDTO::getShortOrder));
                        formControl.getFormControlTable().getFormControlButtons().sort(Comparator.comparingLong(FormControlTableControlDTO::getShortOrder));
                    }
                });
            });
        });
        formDTO.getFormPopups().sort(Comparator.comparingLong(FormPopupDto::getShortOrder));
        formDTO.getFormPopups().forEach(formPopup -> {
            formPopup.getFormAreas().sort(Comparator.comparingLong(FormAreaDTO::getShortOrder));
            formPopup.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormControlDTO::getShortOrder));
                formArea.getFormControls().forEach(formControl -> {
                    if (formControl.getType().equals("table")) {
                        formControl.getFormControlTable().getFormControls().sort(Comparator.comparingLong(FormControlTableControlDTO::getShortOrder));
                        formControl.getFormControlTable().getFormControlButtons().sort(Comparator.comparingLong(FormControlTableControlDTO::getShortOrder));
                    }
                });
            });
        });

        formDTO.getFormActionButtons().sort(Comparator.comparingLong(FormActionButtonDTO::getShortOrder));

        /* Return */
        return formDTO;
    }

    @Transactional
    @Modifying
    public void deleteObject(Long id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "FormEntity does not exist");
        }
        this.componentPersistEntityFieldAssignmentService.deleteByIdAndEntityType(optionalFormEntity.get().getId(), "form");
        this.formRepository.deleteById(optionalFormEntity.get().getId());
    }

    public boolean clearCache() {
        this.formCacheingService.clear();
        this.formRepository.increaseInstanceVersions();
        return true;
    }

}