package com.crm.sofia.services.sofia.form;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.form.designer.*;
import com.crm.sofia.mapper.sofia.form.designer.FormMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import com.crm.sofia.repository.sofia.form.FormRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.component.ComponentDesignerService;
import com.crm.sofia.services.sofia.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.sofia.expression.ExpressionService;
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
    private final FormCacheingService formCacheingService;

    public FormDesignerService(FormRepository formRepository,
                               FormMapper formMapper,
                               JWTService jwtService,
                               FormDynamicQueryService formDynamicQueryService,
                               ComponentDesignerService componentDesignerService,
                               ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService,
                               ExpressionService expressionService,
                               ResourceLoader resourceLoader,
                               FormCacheingService formCacheingService) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.jwtService = jwtService;
        this.formDynamicQueryService = formDynamicQueryService;
        this.componentDesignerService = componentDesignerService;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
        this.expressionService = expressionService;
        this.resourceLoader = resourceLoader;
        this.formCacheingService = formCacheingService;
    }

    @Transactional
    public FormDTO postObject(FormDTO formDTO) {
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

        String script = this.generateDynamicHandlersJavaScriptAndEncode(formDTO);
        formEntity.setScript(script);

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(), "form",
                        createdFormEntity.getId());

        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);
        formCacheingService.clearUiObject(createdFormDTO.getId());
        return createdFormDTO;
    }

    @Transactional
    public FormDTO putObject(FormDTO formDTO) {
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

        String script = this.generateDynamicHandlersJavaScriptAndEncode(formDTO);
        formEntity.setScript(script);

        FormEntity createdFormEntity = this.formRepository.save(formEntity);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(formDTO.getComponent().getComponentPersistEntityList(), "form",
                        createdFormEntity.getId());

        FormDTO createdFormDTO = this.formMapper.map(createdFormEntity);

        return createdFormDTO;
    }

    public List<FormDTO> getObject() {
        List<FormEntity> formEntities = this.formRepository.findAll();
        return this.formMapper.map(formEntities);
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
                this.componentPersistEntityFieldAssignmentService.retrieveFormFieldAssignments(
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
        this.componentPersistEntityFieldAssignmentService.deleteByFormIdAndEntityType(optionalFormEntity.get().getId(), "form");
        this.formRepository.deleteById(optionalFormEntity.get().getId());
    }

    private String generateDynamicHandlersJavaScriptAndEncode(FormDTO formDTO) {
        String script = this.generateDynamicHandlersScript(formDTO);
        byte[] encodedBytes = Base64.getEncoder().encode(script.getBytes());
        String encodedScript = new String(encodedBytes);

        return encodedScript;
    }

    private String generateDynamicHandlersScript(FormDTO formDTO) {
        List<String> nativeHandlerLines = new ArrayList<>();
        String nativeButtonClickHandlerString = this.generateNativeButtonClickHandler(formDTO);
        nativeHandlerLines.add(nativeButtonClickHandlerString);
        String nativeFormEventsHandlerString = this.generateNativeFormEventsHandler();
        nativeHandlerLines.add(nativeFormEventsHandlerString);
        String nativeFieldEventsHandlerString = this.generateNativeFieldEventsHandler(formDTO);
        nativeHandlerLines.add(nativeFieldEventsHandlerString);
        String pointerVars = generatePointerVars();
        nativeHandlerLines.add(pointerVars);
        return String.join("\n", nativeHandlerLines);
    }

    private String generatePointerVars() {
        List<String> pointerVarLines = new ArrayList<>();
        pointerVarLines.add("");
        pointerVarLines.add("var setSelectedTabNumber;");
        pointerVarLines.add("var textInputDialog;");
        pointerVarLines.add("var textDialog;");
        pointerVarLines.add("var openPopup;");
        pointerVarLines.add("var closePopup;");
        pointerVarLines.add("var printReport;");
        pointerVarLines.add("var dataSet;");
        pointerVarLines.add("var getFieldValue;");
        pointerVarLines.add("var setFieldValue;");
        pointerVarLines.add("var getComponentData;");
        pointerVarLines.add("var appendLineToTable;");
        pointerVarLines.add("var setFieldEditable;");
        pointerVarLines.add("var getFromBackend;");
        pointerVarLines.add("var setFormTitle;");
        pointerVarLines.add("var setFormDescription;");
        pointerVarLines.add("var setHeaderTabEditable;");
        pointerVarLines.add("var saveFormData;");
        pointerVarLines.add("var deleteFormData;");
        pointerVarLines.add("var setActionButtonEditable;");
        pointerVarLines.add("");
        pointerVarLines.add("function defineSelectedTabNumberFunction(myCallback){setSelectedTabNumber = myCallback;}");
        pointerVarLines.add("function defineSelectedTextInputDialog(myCallback){textInputDialog = myCallback;}");
        pointerVarLines.add("function defineSelectedTextDialog(myCallback){textDialog = myCallback;}");
        pointerVarLines.add("function defineSelectedOpenPopupDialog(myCallback){openPopup = myCallback;}");
        pointerVarLines.add("function defineSelectedClosePopupDialog(myCallback){closePopup = myCallback;}");
        pointerVarLines.add("function definePrintReport(myCallback){printReport = myCallback;}");
        pointerVarLines.add("function defineDataset(myDataSet){dataSet = myDataSet;}");
        pointerVarLines.add("function defineGetFieldValue(myCallback){getFieldValue = myCallback;}");
        pointerVarLines.add("function defineSetFieldValue(myCallback){setFieldValue = myCallback;}");
        pointerVarLines.add("function defineGetComponentData(myCallback){getComponentData = myCallback;}");
        pointerVarLines.add("function defineAppendLineToTable(myCallback){appendLineToTable = myCallback;}");
        pointerVarLines.add("function defineSetFieldEditable(myCallback){setFieldEditable = myCallback;}");
        pointerVarLines.add("function defineGetFromBackend(myCallback){getFromBackend = myCallback;}");
        pointerVarLines.add("function defineSetFormTitle(myCallback){setFormTitle = myCallback;}");
        pointerVarLines.add("function defineSetFormDescription(myCallback){setFormDescription = myCallback;}");
        pointerVarLines.add("function defineSetHeaderTabEditable(myCallback){setHeaderTabEditable = myCallback;}");
        pointerVarLines.add("function defineSetActionButtonEditable(myCallback){setActionButtonEditable = myCallback;}");
        pointerVarLines.add("function defineSaveFormData(myCallback){saveFormData = myCallback;}");
        pointerVarLines.add("function defineDeleteFormData(myCallback){deleteFormData = myCallback;}");

        pointerVarLines.add("");
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
        nativeButtonClickHandlerLines.add("");
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
                                add("if((btnCode == '" + formControlDTO.getFormControlButton().getCode() + "') && " +
                                        "(typeof btn_" + formControlDTO.getFormControlButton().getCode() + "_click == \"function\")) " +
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
                                            add("if((btnCode == '" + formControlButtonControl.getFormControlButton().getCode() + "') && " +
                                                    "(typeof btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click == \"function\")) " +
                                                    "btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click();");

                                });
                    });
        });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }

    private String generateNativeFormEventsHandler() {
        List<String> nativeFormEventsHandler = new ArrayList<>();
        nativeFormEventsHandler.add("");
        nativeFormEventsHandler.add("function nativeFormEventsHandler(type, metadata) {");

        nativeFormEventsHandler.
                add("if((type == 'onFormOpen') && " +
                        "(typeof onFormOpen == \"function\")) " +
                        "onFormOpen(metadata);");

        nativeFormEventsHandler.add("}");
        return String.join("\n", nativeFormEventsHandler);
    }

    private String generateNativeFieldEventsHandler(FormDTO formDTO) {
        List<String> assignedFields = new ArrayList<>();
        List<FormAreaDTO> formAreas = new ArrayList<>();

        formDTO.getFormTabs()
                .stream()
                .filter(formTabDTO -> formTabDTO.getFormAreas() != null)
                .forEach(formTabDTO -> formAreas.addAll(formTabDTO.getFormAreas()));

        formDTO.getFormPopups()
                .stream()
                .filter(formPopupDTO -> formPopupDTO.getFormAreas() != null)
                .forEach(formPopupDTO -> formAreas.addAll(formPopupDTO.getFormAreas()));

        List<String> nativeFieldEventHandlerLines = new ArrayList<>();
        nativeFieldEventHandlerLines.add("");
        nativeFieldEventHandlerLines.add("function nativeFieldEventsHandler(entityCode, fieldName, type, event) {");

        /* Fields */
        formAreas.forEach(formAreaDTO -> {
            formAreaDTO.getFormControls()
                    .stream()
                    .filter(formControlDTO -> formControlDTO.getType().equals("field"))
                    .forEach(formControlDTO -> {
                        List<String> fieldCodeParts = new ArrayList<>();
                        fieldCodeParts.add(formControlDTO.getFormControlField().getComponentPersistEntity().getCode());
                        fieldCodeParts.add("_");
                        fieldCodeParts.add(formControlDTO.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName());
                        String fieldCode = String.join("", fieldCodeParts);

                        if (assignedFields.contains(fieldCode)) {
                            return;
                        }

                        List<String> fieldFunctionDefParts = new ArrayList<>();
                        fieldFunctionDefParts.add("on_");
                        fieldFunctionDefParts.add(fieldCode);
                        fieldFunctionDefParts.add("_");
                        fieldFunctionDefParts.add("%%type%%");

                        List<String> fieldFunctionParts = new ArrayList<>();
                        fieldFunctionParts.addAll(fieldFunctionDefParts);
                        fieldFunctionParts.add("(event);");

                        List<String> statementParts = new ArrayList<>();
                        statementParts.add("if(");
                        statementParts.add("(entityCode == '" + formControlDTO.getFormControlField().getComponentPersistEntity().getCode() + "') && ");
                        statementParts.add("(fieldName == '" + formControlDTO.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName() + "') && ");
                        statementParts.add("(type == '%%type%%') && ");
                        statementParts.add("(typeof " + String.join("", fieldFunctionDefParts) + " == \"function\")");
                        statementParts.add(") ");
                        statementParts.add(String.join("", fieldFunctionParts));

                        String statement = String.join("", statementParts);

                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keydown"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keyup"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dblclick"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "click"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "change"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focus"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focusout"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "drag"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dragend"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mousemove"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseout"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseover"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseup"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "resize"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listselected"));
                        nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listcleared"));
                        assignedFields.add(fieldCode);
                    });
        });

        /* Table Fields */
        formAreas.forEach(formAreaDTO -> {
            formAreaDTO.getFormControls()
                    .stream()
                    .filter(formControlDTO -> formControlDTO.getType().equals("table"))
                    .forEach(formControlDTO -> {
                        formControlDTO.formControlTable.getFormControls()
                                .stream()
                                .forEach(formControl -> {
                                    List<String> fieldCodeParts = new ArrayList<>();
                                    fieldCodeParts.add(formControl.getFormControlField().getComponentPersistEntity().getCode());
                                    fieldCodeParts.add("_");
                                    fieldCodeParts.add(formControl.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName());
                                    String fieldCode = String.join("", fieldCodeParts);

                                    if (assignedFields.contains(fieldCode)) {
                                        return;
                                    }

                                    List<String> fieldFunctionDefParts = new ArrayList<>();
                                    fieldFunctionDefParts.add("on_");
                                    fieldFunctionDefParts.add(fieldCode);
                                    fieldFunctionDefParts.add("_");
                                    fieldFunctionDefParts.add("%%type%%");

                                    List<String> fieldFunctionParts = new ArrayList<>();
                                    fieldFunctionParts.addAll(fieldFunctionDefParts);
                                    fieldFunctionParts.add("(event);");

                                    List<String> statementParts = new ArrayList<>();
                                    statementParts.add("if(");
                                    statementParts.add("(entityCode == '" + formControl.getFormControlField().getComponentPersistEntity().getCode() + "') && ");
                                    statementParts.add("(fieldName == '" + formControl.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName() + "') && ");
                                    statementParts.add("(type == '%%type%%') && ");
                                    statementParts.add("(typeof " + String.join("", fieldFunctionDefParts) + " == \"function\")");
                                    statementParts.add(") ");
                                    statementParts.add(String.join("", fieldFunctionParts));

                                    String statement = String.join("", statementParts);

                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keydown"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keyup"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dblclick"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "click"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "change"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focus"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focusout"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "drag"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dragend"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mousemove"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseout"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseover"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseup"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "resize"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listselected"));
                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listcleared"));

                                    assignedFields.add(fieldCode);
                                });
                    });
        });

        nativeFieldEventHandlerLines.add("}");
        return String.join("\n", nativeFieldEventHandlerLines);
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
