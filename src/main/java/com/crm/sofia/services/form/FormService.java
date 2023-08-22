package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.component.user.ComponentUiDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.dto.form.user.*;
import com.crm.sofia.exception.ExpressionException;
import com.crm.sofia.mapper.component.ComponentJsonMapper;
import com.crm.sofia.mapper.component.ComponentUiMapper;
import com.crm.sofia.mapper.form.designer.FormMapper;
import com.crm.sofia.mapper.form.user.FormUiMapper;
import com.crm.sofia.model.expression.ExprResponse;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.native_repository.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentDeleterService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.component.crud.ComponentSaverService;
import com.crm.sofia.services.expression.ExpressionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FormService {

//    private final FormRepository formRepository;
//    private final FormMapper formMapper;
//    private final FormUiMapper formUiMapper;
//    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
//    private final ComponentRetrieverService componentRetrieverService;
//    private final ComponentSaverService componentSaverService;
//    private final ComponentRetrieverNativeRepository componentRetrieverNativeRepository;
//    private final ComponentUiMapper componentUiMapper;
//    private final ComponentJsonMapper componentJsonMapper;
//    private final ComponentDeleterService componentDeleterService;

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private FormUiMapper formUiMapper;
    @Autowired
    private ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    @Autowired
    private ComponentRetrieverService componentRetrieverService;
    @Autowired
    private ComponentSaverService componentSaverService;
    @Autowired
    private ComponentUiMapper componentUiMapper;
    @Autowired
    private ComponentJsonMapper componentJsonMapper;
    @Autowired
    private ComponentDeleterService componentDeleterService;
    @Autowired
    private ComponentRetrieverNativeRepository componentRetrieverNativeRepository;

    @Autowired
    private ExpressionService expressionService;

    @Cacheable(value = "form_db_cache", key = "#id")
    public FormDTO getObject(String id) {

        log.debug("form_db_cache " + id);

        /* Retrieve FormDTO */
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Map FormDTO */
        FormDTO formDTO = this.formMapper.map(optionalFormEntity.get());

        /* Shorting Component*/
        List<ComponentPersistEntityDTO> sorted = this.shortCPEList(formDTO.getComponent().getComponentPersistEntityList());
        formDTO.getComponent().setComponentPersistEntityList(sorted);

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );

        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        /* Return */
        return formDTO;
    }

    public List<ComponentPersistEntityDTO> shortCPEList(List<ComponentPersistEntityDTO> componentPersistEntityList) {
        if (componentPersistEntityList == null) {
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

            cpe.getComponentPersistEntityFieldList()
                    .stream()
                    .filter(cpef -> cpef.getPersistEntityField().getShortOrder() == null)
                    .forEach(cpef -> cpef.getPersistEntityField().setShortOrder(0L));

            cpe.getComponentPersistEntityFieldList()
                    .stream()
                    .forEach(cpef -> cpef.setShortOrder(cpef.getPersistEntityField().getShortOrder()));

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

    public FormDTO getObjectByJsonUrl(String jsonUrl) {

        /* Retrieve */
        List<String> formEntityIdsList = this.formRepository.getIdsByJsonUrl(jsonUrl);
        if (formEntityIdsList.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Return */
        return this.getObject(formEntityIdsList.get(0));
    }

    @Cacheable(value = "form_uil_cache", key = "{ #id, #languageId }")
    public FormUiDTO getUiObject(String id, String languageId) {

        System.out.println("Get UiForm object from Database");

        /* Retrieve */
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Map */
        FormUiDTO formUiDTO = this.formUiMapper.mapForm(optionalFormEntity.get(), languageId);

        /* Short */
        formUiDTO.getFormTabs().sort(Comparator.comparingLong(FormUiTabDTO::getShortOrder));
        formUiDTO.getFormTabs().forEach(formTab -> {
            formTab.getFormAreas().sort(Comparator.comparingLong(FormUiAreaDTO::getShortOrder));
            formTab.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormUiControlDTO::getShortOrder));
                formArea.getFormControls().forEach(formControl -> {
                    if (formControl.getType().equals("table")) {
                        formControl.getFormControlTable().getFormControls().sort(Comparator.comparingLong(com.crm.sofia.dto.form.user.FormControlTableControlDTO::getShortOrder));
                        formControl.getFormControlTable().getFormControlButtons().sort(Comparator.comparingLong(com.crm.sofia.dto.form.user.FormControlTableControlDTO::getShortOrder));
                    }
                });
            });
        });
        formUiDTO.getFormPopups().sort(Comparator.comparingLong(com.crm.sofia.dto.form.user.FormPopupDto::getShortOrder));
        formUiDTO.getFormPopups().forEach(formPopup -> {
            formPopup.getFormAreas().sort(Comparator.comparingLong(FormUiAreaDTO::getShortOrder));
            formPopup.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormUiControlDTO::getShortOrder));
                formArea.getFormControls().forEach(formControl -> {
                    if (formControl.getType().equals("table")) {
                        formControl.getFormControlTable().getFormControls().sort(Comparator.comparingLong(com.crm.sofia.dto.form.user.FormControlTableControlDTO::getShortOrder));
                        formControl.getFormControlTable().getFormControlButtons().sort(Comparator.comparingLong(com.crm.sofia.dto.form.user.FormControlTableControlDTO::getShortOrder));
                    }
                });
            });
        });

        formUiDTO.getFormActionButtons().sort(Comparator.comparingLong(FormActionButtonUiDTO::getShortOrder));

        /* Return */
        return formUiDTO;
    }

    public ComponentUiDTO retrieveClonedData(FormDTO formDTO, String selectionId) {

        /* Retrieve Data */
        ComponentDTO componentDTO =
                componentRetrieverService.retrieveComponentWithData(formDTO.getComponent(), selectionId);


        return componentUiMapper.clearIdsAndMapToUi(componentDTO);
    }

    public ComponentUiDTO retrieveUiData(FormDTO formDTO, String selectionId) {

        this.executeBackendActions(formDTO,  Map.of("selection-id",selectionId), "before_retrieve");

        /* Retrieve Data */
        ComponentDTO componentDTO =
                componentRetrieverService.retrieveComponentWithData(formDTO.getComponent(), selectionId);

        this.executeBackendActions(formDTO,  Map.of("selection-id",selectionId), "after_retrieve");

        return componentUiMapper.mapToUi(componentDTO);
    }

    public Map retrieveJsonData(String jsonUrl, String selectionId) {

        /* Retrieve Component */
        FormDTO formDTO = this.getObjectByJsonUrl(jsonUrl);

        /* Retrieve Data */
        ComponentDTO componentDTO = componentRetrieverService.retrieveComponentWithData(formDTO.getComponent(), selectionId);

        return componentJsonMapper.mapToJson(componentDTO);
    }

    public String save(FormDTO formDTO, Map<String, Map<String, Object>> parameters) {

        this.executeBackendActions(formDTO, Map.of(), "before_save");
        /* Μap parameters to component And save */
        String id = componentSaverService.save(formDTO.getComponent(), parameters);

        this.executeBackendActions(formDTO,  Map.of("new-id",id), "after_save");

        return  id;
    }

    public void delete(FormDTO formDTO, String selectionId) {
        this.componentDeleterService.retrieveComponentAndDelete(formDTO.getComponent(), selectionId);
    }

    public String saveJsonData(String jsonUrl, Map<String, Map<String, Object>> parameters) {

        /* Retrieve form from Database */
        FormDTO formDTO = this.getObjectByJsonUrl(jsonUrl);

        this.executeBackendActions(formDTO, Map.of(), "before_save");

        /* Μap parameters to component And save */
        String id = componentSaverService.save(formDTO.getComponent(), parameters);

        this.executeBackendActions(formDTO,  Map.of("new-id",id), "after_save");

        return id;
    }

    public String getJavaScript(String formId) {
        String script = this.formRepository.getFormScript(formId);
        return script;
    }

    public String getMinJavaScript(String formId) {
        String script = this.formRepository.getFormMinScript(formId);
        return script;
    }

    public String getJavaScriptFactory() {
        List<String> formIds = this.formRepository.getFormIds();
        List<String> scriptLines = new ArrayList<>();
        scriptLines.add("function newFormDynamicScript(id) {");
        formIds.forEach(id -> {
            String ifClause =
                    String.join("",
                            "if (id == '", id, "'",
                            " ) return new FormDynamicScript", id.replace("-", "_"), "();");
            scriptLines.add(ifClause);
        });
        scriptLines.add("}");

        return String.join("\n", scriptLines);
    }

    public String getCssScript(String formId) {
        List<String> decodedScripts = new ArrayList<>();
        List<String> formScripts = this.formRepository.getFormCssScriptsByFormId(formId);

        formScripts.forEach(formScript -> {
            byte[] decodedBytes = Base64.getDecoder().decode(formScript);
            String decodedScript = new String(decodedBytes);
            decodedScripts.add(decodedScript);
        });
        return String.join("\n\n", decodedScripts);
    }


    public String getInstanceVersion(String id) {
        return this.formRepository.getInstanceVersion(id);
    }

    public void executeBackendActions(FormDTO formDTO, Map<String, Object> parameters, String triggerType) {
        formDTO.getFormBackendActionsList()
                .stream()
                .filter(action -> action.getTrigger_on().equals(triggerType))
                .forEach(action -> {
                    ExprResponse exprResponse = expressionService.createCacheable(action.getEditor(), action.getId());

                    Object response = expressionService.getResult(exprResponse, parameters, formDTO.getComponent());

                    if(response instanceof ExpressionException){
                        throw (ExpressionException) response;
                    }
                });
    }

}
