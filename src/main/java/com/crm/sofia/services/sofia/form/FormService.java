package com.crm.sofia.services.sofia.form;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.user.ComponentUiDTO;
import com.crm.sofia.dto.sofia.form.base.*;
import com.crm.sofia.dto.sofia.form.base.FormControlTableControlDTO;
import com.crm.sofia.dto.sofia.form.base.FormPopupDto;
import com.crm.sofia.dto.sofia.form.user.*;
import com.crm.sofia.mapper.sofia.component.ComponentJsonMapper;
import com.crm.sofia.mapper.sofia.component.ComponentUiMapper;
import com.crm.sofia.mapper.sofia.form.designer.FormMapper;
import com.crm.sofia.mapper.sofia.form.user.FormUiMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.repository.sofia.form.FormRepository;
import com.crm.sofia.services.sofia.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.sofia.component.ComponentService;
import com.crm.sofia.services.sofia.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.sofia.component.crud.ComponentSaverService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class FormService {

    private final FormRepository formRepository;

    private final FormMapper formMapper;
    private final FormUiMapper formUiMapper;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    private final ComponentRetrieverService componentRetrieverService;
    private final ComponentSaverService componentSaverService;
    private final ComponentService componentService;
    private final ComponentUiMapper componentUiMapper;
    private final ComponentJsonMapper componentJsonMapper;
    private final FormCacheingService formCacheingService;

    public FormService(FormRepository formRepository,
                       FormMapper formMapper,
                       FormUiMapper formUiMapper,
                       ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                       ComponentRetrieverService componentRetrieverService,
                       ComponentSaverService componentSaverService,
                       ComponentService componentService,
                       ComponentUiMapper componentUiMapper,
                       ComponentJsonMapper componentJsonMapper,
                       FormCacheingService formCacheingService) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.formUiMapper = formUiMapper;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.componentRetrieverService = componentRetrieverService;
        this.componentSaverService = componentSaverService;
        this.componentService = componentService;
        this.componentUiMapper = componentUiMapper;
        this.componentJsonMapper = componentJsonMapper;
        this.formCacheingService = formCacheingService;
    }

    public FormDTO getObject(Long id) {

        /* Retrieve */
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Map */
        FormDTO formDTO = this.formMapper.mapForm(optionalFormEntity.get());

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

        /* Return */
        return formDTO;
    }


    public FormDTO getObject(String jsonUrl) {

        /* Retrieve */
        List<Long> formEntityIdsList = this.formRepository.getIdsByJsonUrl(jsonUrl);
        if (formEntityIdsList.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Return */
        return this.getObject(formEntityIdsList.get(0));
    }

    public FormUiDTO getUiObject(Long id) {

        if (this.formCacheingService.hasUiObject(id)) {
            return this.formCacheingService.getUiObject(id);
        }

        /* Retrieve */
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Map */
        FormUiDTO formUiDTO = this.formUiMapper.mapForm(optionalFormEntity.get());

        /* Short */
        formUiDTO.getFormTabs().sort(Comparator.comparingLong(FormUiTabDTO::getShortOrder));
        formUiDTO.getFormTabs().forEach(formTab -> {
            formTab.getFormAreas().sort(Comparator.comparingLong(FormUiAreaDTO::getShortOrder));
            formTab.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormUiControlDTO::getShortOrder));
                formArea.getFormControls().forEach(formControl -> {
                    if (formControl.getType().equals("table")) {
                        formControl.getFormControlTable().getFormControls().sort(Comparator.comparingLong(com.crm.sofia.dto.sofia.form.user.FormControlTableControlDTO::getShortOrder));
                        formControl.getFormControlTable().getFormControlButtons().sort(Comparator.comparingLong(com.crm.sofia.dto.sofia.form.user.FormControlTableControlDTO::getShortOrder));
                    }
                });
            });
        });
        formUiDTO.getFormPopups().sort(Comparator.comparingLong(com.crm.sofia.dto.sofia.form.user.FormPopupDto::getShortOrder));
        formUiDTO.getFormPopups().forEach(formPopup -> {
            formPopup.getFormAreas().sort(Comparator.comparingLong(FormUiAreaDTO::getShortOrder));
            formPopup.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormUiControlDTO::getShortOrder));
                formArea.getFormControls().forEach(formControl -> {
                    if (formControl.getType().equals("table")) {
                        formControl.getFormControlTable().getFormControls().sort(Comparator.comparingLong(com.crm.sofia.dto.sofia.form.user.FormControlTableControlDTO::getShortOrder));
                        formControl.getFormControlTable().getFormControlButtons().sort(Comparator.comparingLong(com.crm.sofia.dto.sofia.form.user.FormControlTableControlDTO::getShortOrder));
                    }
                });
            });
        });

        formUiDTO.getFormActionButtons().sort(Comparator.comparingLong(FormActionButtonUiDTO::getShortOrder));

        /* Cache */
        this.formCacheingService.putUiObject(id, formUiDTO);

        /* Return */
        return formUiDTO;
    }

    public ComponentUiDTO retrieveData(Long formId, String selectionId) {

        /* Retrieve Component */
        FormDTO formDTO = this.getObject(formId);
        ComponentDTO componentDTO = formDTO.getComponent();

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        componentDTO.getComponentPersistEntityList(),
                        "form",
                        formId
                );
        componentDTO.setComponentPersistEntityList(componentPersistEntityList);

        /* Retrieve Data */
        componentDTO = componentRetrieverService.retrieveComponentWithData(componentDTO, selectionId);

        return componentUiMapper.mapToUi(componentDTO);
    }

    public Map retrieveJsonData(String jsonUrl, String selectionId) {

        /* Retrieve Component */
        FormDTO formDTO = this.getObject(jsonUrl);
        ComponentDTO componentDTO = formDTO.getComponent();

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        componentDTO.getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );
        componentDTO.setComponentPersistEntityList(componentPersistEntityList);

        /* Retrieve Data */
        componentDTO = componentRetrieverService.retrieveComponentWithData(componentDTO, selectionId);

        return componentJsonMapper.mapToJson(componentDTO);
    }

    public FormDTO getObjectAndRetrieveData(Long formId, String selectionId) {

        /* Retrieve form from Database */
        FormDTO formDTO = this.getObject(formId);

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );
        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        /* Retrieve Data */
        ComponentDTO componentDTO = componentRetrieverService.retrieveComponentWithData(formDTO.getComponent(), selectionId);
        formDTO.setComponent(componentDTO);

        return formDTO;
    }

    public String save(Long formId, Map<String, Map<String, Object>> parameters) {

        /* Retrieve form from Database */
        FormDTO formDTO = this.getObject(formId);

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );

        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        /* Μap parameters to component And save */
        return componentSaverService.save(formDTO.getComponent(), parameters);
    }

    public String saveJsonData(String jsonUrl, Map<String, Map<String, Object>> parameters) {

        /* Retrieve form from Database */
        FormDTO formDTO = this.getObject(jsonUrl);

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );

        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        /* Μap parameters to component And save */
        return componentSaverService.save(formDTO.getComponent(), parameters);
    }


    public String getJavaScript(Long formId) {
        String script = this.formRepository.getFormScript(formId);
        return script;
    }

    public String getMinJavaScript(Long formId) {
        String script = this.formRepository.getFormMinScript(formId);
        return script;
    }

    public String getFormJavaScriptFactory() {
        List<Long> formIds = this.formRepository.getFormIds();
        List<String> scriptLines = new ArrayList<>();
        scriptLines.add("function newFormDynamicScript(id) {");
        formIds.forEach(id -> {
           String ifClause =
                   String.join("",
                           "if (id == " , id.toString(),
                           " ) return new FormDynamicScript",id.toString() , "();" );
           scriptLines.add(ifClause);
        });
        scriptLines.add("}");

        return String.join("\n", scriptLines);

    }

    public String getCssScript(Long formId) {
        List<String> decodedScripts = new ArrayList<>();
        List<String> formScripts = this.formRepository.getFormCssScriptsByFormId(formId);

        formScripts.forEach(formScript -> {
            byte[] decodedBytes = Base64.getDecoder().decode(formScript);
            String decodedScript = new String(decodedBytes);
            decodedScripts.add(decodedScript);
        });
        return String.join("\n\n", decodedScripts);
    }


    public String getInstanceVersion(Long id) {
        return this.formRepository.getInstanceVersion(id);
    }

}
