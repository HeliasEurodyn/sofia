package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.form.FormAreaDTO;
import com.crm.sofia.dto.form.FormControlDTO;
import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.dto.form.FormTabDTO;
import com.crm.sofia.mapper.form.FormMapper;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.component.ComponentDesignerService;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.expression.ExpressionService;
import org.springframework.core.io.ResourceLoader;
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
    private final FormDynamicQueryService formDynamicQueryService;
    private final ComponentDesignerService componentDesignerService;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;
    private final ExpressionService expressionService;
    private final ResourceLoader resourceLoader;

    public FormDesignerService(FormRepository formRepository,
                               FormMapper formMapper,
                               JWTService jwtService,
                               FormDynamicQueryService formDynamicQueryService,
                               ComponentDesignerService componentDesignerService,
                               ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                               ExpressionService expressionService,
                               ResourceLoader resourceLoader) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.jwtService = jwtService;
        this.formDynamicQueryService = formDynamicQueryService;
        this.componentDesignerService = componentDesignerService;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.expressionService = expressionService;
        this.resourceLoader = resourceLoader;
    }

    @Transactional
    public FormDTO postObject(FormDTO formDTO) {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setCreatedOn(Instant.now());
        formEntity.setModifiedOn(Instant.now());
        formEntity.setCreatedBy(jwtService.getUserId());
        formEntity.setModifiedBy(jwtService.getUserId());

        String script = this.generateDynamicHandlersScriptEndEncode(formDTO);
        formEntity.setScript(script);

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(),
                        createdFormEntity.getId());

        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);
        return createdFormDTO;
    }

    @Transactional
    public FormDTO putObject(FormDTO formDTO) {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setModifiedOn(Instant.now());
        formEntity.setModifiedBy(jwtService.getUserId());

        String script = this.generateDynamicHandlersScriptEndEncode(formDTO);
        formEntity.setScript(script);

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(),
                        createdFormEntity.getId());

        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);

        return createdFormDTO;
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

        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFormFieldAssignments(
                        formDTO.getComponent().getComponentPersistEntityList(),
                        "form",
                        formDTO.getId()
                );

        formDTO.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        formDTO.getFormTabs().sort(Comparator.comparingLong(FormTabDTO::getShortOrder));
        formDTO.getFormTabs().forEach(formTab -> {
            formTab.getFormAreas().sort(Comparator.comparingLong(FormAreaDTO::getShortOrder));
            formTab.getFormAreas().forEach(formArea -> {
                formArea.getFormControls().sort(Comparator.comparingLong(FormControlDTO::getShortOrder));
            });
        });

        return formDTO;
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

    private String generateDynamicHandlersScriptEndEncode(FormDTO formDTO) {
        String script = this.generateDynamicHandlersScript(formDTO);
        byte[] encodedBytes = Base64.getEncoder().encode(script.getBytes());
        String encodedScript = new String(encodedBytes);

        return encodedScript;
    }

    private String generateDynamicHandlersScript(FormDTO formDTO) {
        List<String> nativeHandlerLines = new ArrayList<>();
        String nativeButtonClickHandlerString = this.generateNativeButtonClickHandler(formDTO);
        nativeHandlerLines.add(nativeButtonClickHandlerString);
        String pointerVars = generatePointerVars();
        nativeHandlerLines.add(pointerVars);
        return String.join("\n", nativeHandlerLines);
    }

    private String generatePointerVars() {
        List<String> pointerVarLines = new ArrayList<>();
        pointerVarLines.add("\n");
        pointerVarLines.add("var setSelectedTabNumber;");
        pointerVarLines.add("var textInputDialog;");
        pointerVarLines.add("var openPopup;");
        pointerVarLines.add("var closePopup;");

        pointerVarLines.add("\n");
        pointerVarLines.add("function defineSelectedTabNumberFunction(myCallback){setSelectedTabNumber = myCallback;}");
        pointerVarLines.add("function defineSelectedTextInputDialog(myCallback){textInputDialog = myCallback;}");
        pointerVarLines.add("function defineSelectedOpenPopupDialog(myCallback){openPopup = myCallback;}");
        pointerVarLines.add("function defineSelectedClosePopupDialog(myCallback){closePopup = myCallback;}");
        pointerVarLines.add("\n");
        return String.join("\n", pointerVarLines);
    }

    private String generateNativeButtonClickHandler(FormDTO formDTO) {

        List<FormAreaDTO> formAreas = new ArrayList<>();

        formDTO.getFormTabs()
                .stream()
                .filter(formTabDTO -> formTabDTO.getFormAreas() != null)
                .forEach(formTabDTO -> formAreas.addAll(formTabDTO.getFormAreas()));

        formDTO.getFormPopups()
                .stream()
                .filter(formPopupDTO -> formPopupDTO.getFormAreas() != null)
                .forEach(formPopupDTO -> formAreas.addAll(formPopupDTO.getFormAreas()));

        List<String> nativeButtonClickHandlerLines = new ArrayList<>();
        nativeButtonClickHandlerLines.add("\n");
        nativeButtonClickHandlerLines.add("function nativeButtonClickHandler(btnCode) {");

        /* Buttons */
        formAreas.forEach(formAreaDTO -> {
            formAreaDTO.getFormControls()
                    .stream()
                    .filter(formControlDTO -> formControlDTO.getType().equals("button"))
                    .filter(formControlDTO ->
                            !(formControlDTO.getFormControlButton().getCode() == null ? "" : formControlDTO.getFormControlButton().getCode())
                                    .equals("")
                    )
                    .forEach(formControlDTO -> {
                        nativeButtonClickHandlerLines.
                                add("if(btnCode == '" + formControlDTO.getFormControlButton().getCode() + "') " +
                                        "btn_" + formControlDTO.getFormControlButton().getCode() + "_click();");

                    });
        });

        /* Table Buttons */
        formAreas.forEach(formAreaDTO -> {
            formAreaDTO.getFormControls()
                    .stream()
                    .filter(formControlDTO -> formControlDTO.getType().equals("table"))
                    .forEach(formControlDTO -> {
                        formControlDTO.formControlTable.getFormControlButtons()
                                .stream()
                                .filter(formControlButtonControl ->
                                        !(formControlButtonControl.getFormControlButton().getCode() == null ? "" : formControlButtonControl.getFormControlButton().getCode())
                                                .equals("")
                                )
                                .forEach(formControlButtonControl -> {
                                    nativeButtonClickHandlerLines.
                                            add("if(btnCode == '" + formControlButtonControl.getFormControlButton().getCode() + "') " +
                                                    "btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click();");

                                });
                    });
        });


        nativeButtonClickHandlerLines.add("}");
        nativeButtonClickHandlerLines.add("\n");
        return String.join("\n", nativeButtonClickHandlerLines);
    }


//    @Transactional
//    @RequestMapping(value = "testjavascript.js", method = RequestMethod.GET, produces = "text/javascript;")
//    public String home() throws Exception {
//        File file = ResourceUtils.getFile("classpath:test.js");
//        return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
//    }

//    public void saveScriptsToResources(FormDTO formDTO) throws IOException {
//        this.recreateFolder(formDTO.getId());
//        String script = this.generateScript(formDTO);
//       // File file = ResourceUtils.getFile("generated-scripts/form/" + formDTO.getId().toString() + "/script.js");
//        File file = new ClassPathResource("generated-scripts/form/" + formDTO.getId().toString() + "/script.js").getFile();
//        Files.write(file.toPath(), script.getBytes());
//    }

//    public void recreateFolder(Long formId) throws IOException {
//
//      //  Path path = Paths.get("generated-scripts/form/" + formId.toString());
//       // Path path = Paths.get(new ClassPathResource("generated-scripts/form/" + formId.toString()).getPath());
//
//        Resource resource = resourceLoader.getResource("classpath:generated-scripts/form/" + formId.toString());
//        // Boolean folderExists = Files.exists(path);
////        Boolean folderExists = Files.exists(path);
//        if (resource.exists()) {
//            File folderFile = new ClassPathResource("generated-scripts/form/" + formId.toString()).getFile();
//            folderFile.delete();
//            //File folderFile = ResourceUtils.getFile("generated-scripts/form/" + formId.toString());
//            //folderFile.delete();
//        }
//
//        //File folderFile = ResourceUtils.getFile("generated-scripts/form/" + formId.toString());
//       // folderFile.mkdirs();
//        File folderFile = new ClassPathResource("generated-scripts/form/" + formId.toString()).getFile();
//        folderFile.mkdirs();
//    }

//    public String generateScript(FormDTO formDTO) {
//        List<String> decodedScripts = new ArrayList<>();
//        formDTO.getFormScripts().forEach(formScriptDTO -> {
//            byte[] decodedBytes = Base64.getDecoder().decode(formScriptDTO.getScript());
//            String decodedScript = new String(decodedBytes);
//            decodedScripts.add(decodedScript);
//        });
//        return String.join("\n\n  ", decodedScripts);
//    }

}
