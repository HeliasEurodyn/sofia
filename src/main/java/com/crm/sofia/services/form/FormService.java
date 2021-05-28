package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.form.*;
import com.crm.sofia.mapper.form.FormMapper;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.model.form.FormScript;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.component.crud.ComponentSaverService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    private final ComponentRetrieverService componentRetrieverService;
    private final ComponentSaverService componentSaverService;

    public FormService(FormRepository formRepository,
                       FormMapper formMapper,
                       ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                       ComponentRetrieverService componentRetrieverService,
                       ComponentSaverService componentSaverService) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.componentRetrieverService = componentRetrieverService;
        this.componentSaverService = componentSaverService;
    }

    public FormDTO getObject(Long id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }
        FormDTO formDTO = this.formMapper.mapForm(optionalFormEntity.get());

        formDTO.getFormTabs().sort(Comparator.comparingLong(FormTabDTO::getShortOrder));
        formDTO.getFormTabs().forEach(formTab -> {
            formTab.getFormAreas().sort(Comparator.comparingLong(FormAreaDTO::getShortOrder));
            formTab.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormControlDTO::getShortOrder));
            });
        });

        return formDTO;
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




}
