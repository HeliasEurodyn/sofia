package com.crm.sofia.services.form;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.sofia.form.base.*;
import com.crm.sofia.mapper.sofia.form.designer.FormMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormDesignerService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final JWTService jwtService;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
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

        FormEntity createdFormEntity = this.formRepository.save(formEntity);
        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        createdFormDTO.getId());

        String script = this.formJavascriptService.generateDynamicScript(createdFormDTO);
        String scriptMin = this.formJavascriptService.minify(script);
        this.formRepository.updateScripts(createdFormDTO.getId(),script, scriptMin );

        return createdFormDTO;
    }

    @Transactional
    @CacheEvict(value = "form_ui_cache", key = "#formDTO.id")
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

        return createdFormDTO;
    }

    public List<FormDTO> getObject() {
        List<FormEntity> formEntities = this.formRepository.findAllByOrderByModifiedOn();
        return this.formMapper.mapEntitiesForList(formEntities);
    }

    public FormDTO getObject(String id) {

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

        /* Shorting Component*/
        List<ComponentPersistEntityDTO> sorted = this.shortCPEList(formDTO.getComponent().getComponentPersistEntityList());
        formDTO.getComponent().setComponentPersistEntityList(sorted);

        /* Return */
        return formDTO;
    }

    public List<ComponentPersistEntityDTO> shortCPEList(List<ComponentPersistEntityDTO> componentPersistEntityList) {
        if(componentPersistEntityList == null){
            return null;
        }

        componentPersistEntityList
                .stream()
                .filter(cpe -> cpe.getShortOrder() == null)
                .forEach(cpe -> cpe.setShortOrder(0L));

        List<ComponentPersistEntityDTO> sorted =
                componentPersistEntityList
                        .stream()
                        .sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());

        sorted.stream().forEach(cpe -> {

            for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : cpe.getComponentPersistEntityFieldList()) {
                if (componentPersistEntityFieldDTO.getPersistEntityField().getShortOrder() == null) {
                    componentPersistEntityFieldDTO.getPersistEntityField().setShortOrder(0L);
                }
            }

            for (ComponentPersistEntityFieldDTO cpef : cpe.getComponentPersistEntityFieldList()) {
                cpef.setShortOrder(cpef.getPersistEntityField().getShortOrder());
            }

            List<ComponentPersistEntityFieldDTO> sortedCpefList =
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .sorted(Comparator.comparingLong(ComponentPersistEntityFieldDTO::getShortOrder)).collect(Collectors.toList());

            cpe.setComponentPersistEntityFieldList(sortedCpefList);
        });

        sorted.stream().forEach(cpe -> {
            List<ComponentPersistEntityDTO> sortedCPE = this.shortCPEList(cpe.getComponentPersistEntityList());
            cpe.setComponentPersistEntityList(sortedCPE);
        });

        return sorted;
    }

    @Transactional
    @Modifying
    public void deleteObject(String id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "FormEntity does not exist");
        }
        this.componentPersistEntityFieldAssignmentService.deleteByIdAndEntityType(optionalFormEntity.get().getId(), "form");
        this.formRepository.deleteById(optionalFormEntity.get().getId());
    }

    public boolean clearCache() {
        this.formRepository.increaseInstanceVersions();
        return true;
    }

    public List<String> getBusinessUnits(){
        List<String> businessUnits = formRepository.findBusinessUnitsDistinct();
        return  businessUnits;
    }

}
