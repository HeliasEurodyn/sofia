package com.crm.sofia.services.sofia.form;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.user.ComponentUiDTO;
import com.crm.sofia.dto.sofia.form.designer.FormAreaDTO;
import com.crm.sofia.dto.sofia.form.designer.FormControlDTO;
import com.crm.sofia.dto.sofia.form.designer.FormDTO;
import com.crm.sofia.dto.sofia.form.designer.FormTabDTO;
import com.crm.sofia.dto.sofia.form.user.FormUiAreaDTO;
import com.crm.sofia.dto.sofia.form.user.FormUiControlDTO;
import com.crm.sofia.dto.sofia.form.user.FormUiDTO;
import com.crm.sofia.dto.sofia.form.user.FormUiTabDTO;
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

    public FormService(FormRepository formRepository,
                       FormMapper formMapper,
                       FormUiMapper formUiMapper,
                       ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                       ComponentRetrieverService componentRetrieverService,
                       ComponentSaverService componentSaverService,
                       ComponentService componentService,
                       ComponentUiMapper componentUiMapper) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.formUiMapper = formUiMapper;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.componentRetrieverService = componentRetrieverService;
        this.componentSaverService = componentSaverService;
        this.componentService = componentService;
        this.componentUiMapper = componentUiMapper;
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
            });
        });

        /* Return */
        return formDTO;
    }

    public FormUiDTO getUiObject(Long id) {

        /* Retrieve */
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }

        /* Map */
        FormUiDTO formUiDTO = this.formUiMapper.mapForm(optionalFormEntity.get());

        /* Shorting */
        formUiDTO.getFormTabs().sort(Comparator.comparingLong(FormUiTabDTO::getShortOrder));
        formUiDTO.getFormTabs().forEach(formTab -> {
            formTab.getFormAreas().sort(Comparator.comparingLong(FormUiAreaDTO::getShortOrder));
            formTab.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormUiControlDTO::getShortOrder));
            });
        });

        /* Return */
        return formUiDTO;
    }

    public ComponentUiDTO retrieveData(Long formId, String selectionId) {

        /* Retrieve Component */
        FormDTO formDTO = this.getObject(formId);
        ComponentDTO componentDTO = formDTO.getComponent();

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFormFieldAssignments(
                        componentDTO.getComponentPersistEntityList(),
                        "form",
                        formId
                );
        componentDTO.setComponentPersistEntityList(componentPersistEntityList);

        /* Retrieve Data */
        componentDTO = componentRetrieverService.retrieveComponentWithData(componentDTO, selectionId);

        return componentUiMapper.mapComponent(componentDTO);
    }

    public FormDTO getObjectAndRetrieveData(Long formId, String selectionId) {

        /* Retrieve form from Database */
        FormDTO formDTO = this.getObject(formId);

        /* Retrieve Form Component field Assignments from Database */
        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFormFieldAssignments(
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
                this.componentPersistEntityFieldAssignmentService.retrieveFormFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );

        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        /* Μap parameters to component And save */
        return componentSaverService.save(formDTO.getComponent(), parameters);
    }

    public String getFormScript(Long formId) {

        List<String> decodedScripts = new ArrayList<>();
        List<String> formScripts = this.formRepository.getFormScriptsByFormId(formId);
        String formScriptWithHandlers = this.formRepository.getFormScript(formId);
        formScripts.add(formScriptWithHandlers);

        formScripts.forEach(formScript -> {
            byte[] decodedBytes = Base64.getDecoder().decode(formScript);
            String decodedScript = new String(decodedBytes);
            decodedScripts.add(decodedScript);
        });
        return String.join("\n\n", decodedScripts);
    }

    public String getVersion(Long id) {
        return this.formRepository.getInstanceVersion(id);
    }
}
