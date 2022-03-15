package com.crm.sofia.services.sofia.form;

import com.crm.sofia.dto.sofia.form.base.FormAreaDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.utils.JSMin;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FormJavascriptService {

    public String generateDynamicScript(FormDTO formDTO) {
        List<String> nativeHandlerLines = new ArrayList<>();

        String classInitString = this.generateClassInit(formDTO);
        nativeHandlerLines.add(classInitString);

        String pointerVars = generatePointerVars();
        nativeHandlerLines.add(pointerVars);

        String userScriptsString = this.generateUserFormScripts(formDTO);
        nativeHandlerLines.add(userScriptsString);

        String nativeButtonClickHandlerString = this.generateNativeButtonClickHandler(formDTO, userScriptsString);
        nativeHandlerLines.add(nativeButtonClickHandlerString);

        String nativeTableButtonClickHandlerString = this.generateNativeTableButtonClickHandler(formDTO, userScriptsString);
        nativeHandlerLines.add(nativeTableButtonClickHandlerString);

        String nativeFormEventsHandlerString = this.generateNativeFormEventsHandler();
        nativeHandlerLines.add(nativeFormEventsHandlerString);

        String nativeFieldEventsHandlerString = this.generateNativeFieldEventsHandler(formDTO, userScriptsString);
        nativeHandlerLines.add(nativeFieldEventsHandlerString);

        String nativeTableFieldEventsHandlerString = this.generateNativeTableFieldEventsHandler(formDTO, userScriptsString);
        nativeHandlerLines.add(nativeTableFieldEventsHandlerString);

        String classEndString = this.generateClassEnd();
        nativeHandlerLines.add(classEndString);

        return String.join("\n", nativeHandlerLines);
    }

    public String minify(String script) throws Exception {
        Reader reader = new StringReader(script);
        Writer writer = new StringWriter();
        JSMin jsMin = new JSMin(reader, writer);
        jsMin.jsmin();
        String scriptMin = writer.toString();
        return scriptMin;
    }

    private String generateUserFormScripts(FormDTO formDTO) {
        List<String> decodedScripts = new ArrayList<>();
        formDTO.getFormScripts().forEach(formScript -> {
            byte[] decodedBytes = Base64.getDecoder().decode(formScript.getScript());
            String decodedScript = new String(decodedBytes);
            decodedScripts.add(decodedScript);
        });

        return String.join("\n", decodedScripts);
    }

    private String generateClassInit(FormDTO formDTO) {
        List<String> classLines = new ArrayList<>();

        List<String> classDefLines = new ArrayList<>();
        classDefLines.add("class FormDynamicScript");
        classDefLines.add(formDTO.getId().toString());
        classDefLines.add(" {");
        String classDef = String.join("", classDefLines);

        String constructor = "constructor() {}";

        classLines.add(classDef);
        classLines.add(constructor);

        return String.join("\n", classLines);
    }

    private String generateClassEnd() {
        return "}";
    }


    private String generateNativeButtonClickHandler(FormDTO formDTO, String userScriptsString) {

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
        nativeButtonClickHandlerLines.add("nativeButtonClickHandler(btnCode) {");

        /* Buttons */
        formAreas.forEach(formAreaDTO -> {
            formAreaDTO.getFormControls()
                    .stream()
                    .filter(formControlDTO -> formControlDTO.getType().equals("button"))
                    .filter(formControlDTO ->
                            !(formControlDTO.getFormControlButton().getCode() == null ? "" : formControlDTO.getFormControlButton().getCode())
                                    .equals("")
                    )
                    .filter(formControlDTO -> userScriptsString.contains("btn_" + formControlDTO.getFormControlButton().getCode() + "_click("))
                    .forEach(formControlDTO -> {
                        nativeButtonClickHandlerLines.
                                add("if((btnCode == '" + formControlDTO.getFormControlButton().getCode() + "') && " +
                                        "(typeof this.btn_" + formControlDTO.getFormControlButton().getCode() + "_click == \"function\")) " +
                                        "this.btn_" + formControlDTO.getFormControlButton().getCode() + "_click();");

                    });
        });

        /* Header Buttons */
        formDTO.getFormActionButtons()
                .stream()
                .filter(formActionButton ->  formActionButton.getCode() != null)
                .filter(formActionButton ->  !formActionButton.getCode().equals(""))
                .filter(formActionButton -> userScriptsString.contains("btn_" + formActionButton.getCode() + "_click("))
                .forEach(formActionButton -> {
            nativeButtonClickHandlerLines.
                    add("if((btnCode == '" + formActionButton.getCode() + "') && " +
                            "(typeof this.btn_" + formActionButton.getCode() + "_click == \"function\")) " +
                            "this.btn_" + formActionButton.getCode() + "_click();");

        });

        formDTO.getFormActionButtons()
                .forEach(parentFormActionButton -> {
                    parentFormActionButton.getFormActionButtons().stream()
                            .filter(formActionButton ->  formActionButton.getCode() != null)
                            .filter(formActionButton ->  !formActionButton.getCode().equals(""))
                            .filter(formActionButton -> userScriptsString.contains("btn_" + formActionButton.getCode() + "_click("))
                            .forEach(formActionButton -> {
                                nativeButtonClickHandlerLines.
                                        add("if((btnCode == '" + formActionButton.getCode() + "') && " +
                                                "(typeof this.btn_" + formActionButton.getCode() + "_click == \"function\")) " +
                                                "this.btn_" + formActionButton.getCode() + "_click();");

                            });

                });

        /* Tabs' Buttons */
        formDTO.getFormTabs()
                .stream()
                .filter(formTab ->  formTab.getCode() != null)
                .filter(formTab ->  !formTab.getCode().equals(""))
                .filter(formTab -> userScriptsString.contains("btn_" + formTab.getCode() + "_click("))
                .forEach(formTab -> {
                    nativeButtonClickHandlerLines.
                            add("if((btnCode == '" + formTab.getCode() + "') && " +
                                    "(typeof this.btn_" + formTab.getCode() + "_click == \"function\")) " +
                                    "this.btn_" + formTab.getCode() + "_click();");

                });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }

    private String generateNativeTableButtonClickHandler(FormDTO formDTO, String userScriptsString) {

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
        nativeButtonClickHandlerLines.add("nativeTableButtonClickHandler(btnCode, formControlTable, dataLine) {");

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
                                .filter(formControlButtonControl -> userScriptsString.contains("table_btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click("))
                                .forEach(formControlButtonControl -> {
                                    nativeButtonClickHandlerLines.
                                            add("if((btnCode == '" + formControlButtonControl.getFormControlButton().getCode() + "') && " +
                                                    "(typeof this.table_btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click == \"function\")) " +
                                                    "this.table_btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click(formControlTable, dataLine);");

                                });
                    });
        });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }

    private String generateNativeFormEventsHandler() {
        List<String> nativeFormEventsHandler = new ArrayList<>();
        nativeFormEventsHandler.add("");
        nativeFormEventsHandler.add("nativeFormEventsHandler(type, metadata) {");

        nativeFormEventsHandler.
                add("if((type == 'onFormOpen') && " +
                        "(typeof this.onFormOpen == \"function\")) " +
                        "this.onFormOpen(metadata);");

        nativeFormEventsHandler.add("}");
        return String.join("\n", nativeFormEventsHandler);
    }

    private String generateNativeFieldEventsHandler(FormDTO formDTO, String userScriptsString) {
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
        nativeFieldEventHandlerLines.add("nativeFieldEventsHandler(entityCode, fieldName, type, event) {");

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
                        fieldFunctionDefParts.add("(");

                        List<String> fieldFunctionRefParts = new ArrayList<>();
                        fieldFunctionRefParts.add("this.");
                        fieldFunctionRefParts.add("on_");
                        fieldFunctionRefParts.add(fieldCode);
                        fieldFunctionRefParts.add("_");
                        fieldFunctionRefParts.add("%%type%%");

                        List<String> fieldFunctionParts = new ArrayList<>();
                        fieldFunctionParts.addAll(fieldFunctionRefParts);
                        fieldFunctionParts.add("(event);");

                        List<String> statementParts = new ArrayList<>();
                        statementParts.add("if(");
                        statementParts.add("(entityCode == '" + formControlDTO.getFormControlField().getComponentPersistEntity().getCode() + "') && ");
                        statementParts.add("(fieldName == '" + formControlDTO.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName() + "') && ");
                        statementParts.add("(type == '%%type%%') && ");
                        statementParts.add("(typeof " + String.join("", fieldFunctionRefParts) + " == \"function\")");
                        statementParts.add(") ");
                        statementParts.add(String.join("", fieldFunctionParts));

                        String statement = String.join("", statementParts);
                        String fieldFunctionDef = String.join("", fieldFunctionDefParts);

                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "keydown"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keydown"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "keyup")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keyup"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "dblclick"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dblclick"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "click"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "click"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "change"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "change"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "focus"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focus"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "focusout"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focusout"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "drag"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "drag"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "dragend")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dragend"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mousemove"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mousemove"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mouseout"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseout"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mouseover")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseover"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mouseup")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseup"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "resize")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "resize"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "listselected")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listselected"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "listcleared"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listcleared"));
                        if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "list_component_refreshed"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "list_component_refreshed"));


                        assignedFields.add(fieldCode);
                    });
        });

        nativeFieldEventHandlerLines.add("}");
        return String.join("\n", nativeFieldEventHandlerLines);
    }

    private String generateNativeTableFieldEventsHandler(FormDTO formDTO, String userScriptsString) {
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
        nativeFieldEventHandlerLines.add("nativeTableFieldEventsHandler(entityCode, fieldName, type, event, formControlTable, dataLine) {");

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
                                    fieldFunctionDefParts.add("on_table_");
                                    fieldFunctionDefParts.add(fieldCode);
                                    fieldFunctionDefParts.add("_");
                                    fieldFunctionDefParts.add("%%type%%");
                                    fieldFunctionDefParts.add("(");

                                    List<String> fieldFunctionRefParts = new ArrayList<>();
                                    fieldFunctionRefParts.add("this.");
                                    fieldFunctionRefParts.add("on_table_");
                                    fieldFunctionRefParts.add(fieldCode);
                                    fieldFunctionRefParts.add("_");
                                    fieldFunctionRefParts.add("%%type%%");

                                    List<String> fieldFunctionParts = new ArrayList<>();
                                    fieldFunctionParts.addAll(fieldFunctionRefParts);
                                    fieldFunctionParts.add("(event, formControlTable, dataLine);");

                                    List<String> statementParts = new ArrayList<>();
                                    statementParts.add("if(");
                                    statementParts.add("(entityCode == '" + formControl.getFormControlField().getComponentPersistEntity().getCode() + "') && ");
                                    statementParts.add("(fieldName == '" + formControl.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName() + "') && ");
                                    statementParts.add("(type == '%%type%%') && ");
                                    statementParts.add("(typeof " + String.join("", fieldFunctionRefParts) + " == \"function\")");
                                    statementParts.add(") ");
                                    statementParts.add(String.join("", fieldFunctionParts));

                                    String statement = String.join("", statementParts);
                                    String fieldFunctionDef = String.join("", fieldFunctionDefParts);

                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "keydown"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keydown"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "keyup"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keyup"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "dblclick"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dblclick"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "click"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "click"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "change"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "change"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "focus"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focus"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "focusout"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focusout"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "drag"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "drag"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "dragend")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dragend"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mousemove")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mousemove"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mouseout"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseout"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mouseover"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseover"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "mouseup"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseup"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "resize"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "resize"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "listselected"))) nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listselected"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "listcleared")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listcleared"));
                                    if(userScriptsString.contains(fieldFunctionDef.replace("%%type%%", "list_component_refreshed")))  nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "list_component_refreshed"));

                                    assignedFields.add(fieldCode);
                                });
                    });
        });

        nativeFieldEventHandlerLines.add("}");
        return String.join("\n", nativeFieldEventHandlerLines);
    }

    private String generatePointerVars() {
        List<String> pointerVarLines = new ArrayList<>();
        pointerVarLines.add("");
        pointerVarLines.add("formRef = null;");
        pointerVarLines.add("");
        pointerVarLines.add("setSelectedTabNumberRef = null;");
        pointerVarLines.add("textInputDialogRef = null;");
        pointerVarLines.add("textDialogRef = null;");
        pointerVarLines.add("notificationDialogRef = null;");
        pointerVarLines.add("openPopupRef = null;");
        pointerVarLines.add("closePopupRef = null;");
        pointerVarLines.add("printReportRef = null;");
        pointerVarLines.add("dataSet = null;");
        pointerVarLines.add("getFormFieldsByCodeRef = null;");
        pointerVarLines.add("getFieldValueRef = null;");
        pointerVarLines.add("setFieldValueRef = null;");
        pointerVarLines.add("getComponentDataRef = null;");
        pointerVarLines.add("appendLineToTableRef = null;");
        pointerVarLines.add("clearTableLinesRef = null;");
        pointerVarLines.add("getFormDatasetRef = null;");
        pointerVarLines.add("setFieldEditableRef = null;");
        pointerVarLines.add("getFromBackendRef = null;");
        pointerVarLines.add("setFormTitleRef = null;");
        pointerVarLines.add("setFormDescriptionRef = null;");
        pointerVarLines.add("setHeaderTabEditableRef = null;");
        pointerVarLines.add("saveFormDataRef = null;");
        pointerVarLines.add("deleteFormDataRef = null;");
        pointerVarLines.add("setActionButtonEditableRef = null;");
        pointerVarLines.add("getParamsRef = null;");
        pointerVarLines.add("setTableRowActiveByIdRef = null;");
        pointerVarLines.add("appendLineToComponentRef = null;");
        pointerVarLines.add("setAreaClassRef = null;");
        pointerVarLines.add("setAreaTitleRef = null;");
        pointerVarLines.add("setAreaDescriptionRef = null;");
        pointerVarLines.add("getAreaRef = null;");
        pointerVarLines.add("navigateRef = null;");
        pointerVarLines.add("saveAndBackFormDataRef = null;");
        pointerVarLines.add("");

        pointerVarLines.add("defineSelectedTabNumberFunction(myCallback){this.setSelectedTabNumberRef = myCallback;}");
        pointerVarLines.add("setSelectedTabNumber(selectedFormTabNumber){return this.setSelectedTabNumberRef(selectedFormTabNumber, this.formRef);}");

        pointerVarLines.add("defineSelectedTextInputDialog(myCallback){this.textInputDialogRef = myCallback;}");
        pointerVarLines.add("textInputDialog(title, description, callback){return this.textInputDialogRef(title, description, callback, this.formRef);}");

        pointerVarLines.add("defineSelectedTextDialog(myCallback){this.textDialogRef = myCallback;}");
        pointerVarLines.add("textDialog(title, description){return this.textDialogRef(title, description, this.formRef);}");

        pointerVarLines.add("defineNotificationDialog(myCallback){this.notificationDialogRef = myCallback;}");
        pointerVarLines.add("notificationDialog(title, description){return this.notificationDialogRef(horizontalPosition, verticalPosition, type, icon, message);}");

        pointerVarLines.add("defineSelectedOpenPopupDialog(myCallback){this.openPopupRef = myCallback;}");
        pointerVarLines.add("openPopup(code){return this.openPopupRef(code, this.formRef);}");

        pointerVarLines.add("defineSelectedClosePopupDialog(myCallback){this.closePopupRef = myCallback;}");
        pointerVarLines.add("closePopup(code){return this.closePopupRef(code);}");

        pointerVarLines.add("definePrintReport(myCallback){this.printReportRef = myCallback;}");
        pointerVarLines.add("printReport(reportId, parameters){return this.printReportRef(reportId, parameters);}");

        pointerVarLines.add("defineGetFieldValue(myCallback){this.getFieldValueRef = myCallback;}");
        pointerVarLines.add("getFieldValue(fieldCode){return this.getFieldValueRef(fieldCode, this.formRef);}");


        pointerVarLines.add("defineGetFormFieldsByCode(myCallback){this.getFormFieldsByCodeRef = myCallback;}");
        pointerVarLines.add("getFormFieldsByCode(fieldCode){return this.getFormFieldsByCodeRef(fieldCode, this.formRef);}");


        pointerVarLines.add("defineSetFieldValue(myCallback){this.setFieldValueRef = myCallback;}");
        pointerVarLines.add("setFieldValue(fieldCode, fieldValue){return this.setFieldValueRef(fieldCode, fieldValue, this.formRef);}");

        pointerVarLines.add("defineGetComponentData(myCallback){this.getComponentDataRef = myCallback;}");
        pointerVarLines.add("getComponentData(componentId, dataId, callback){return this.getComponentDataRef(componentId, dataId, callback);}");

        pointerVarLines.add("defineAppendLineToTable(myCallback){this.appendLineToTableRef = myCallback;}");
        pointerVarLines.add("appendLineToTable(code){return this.appendLineToTableRef(code, this.formRef);}");

        pointerVarLines.add("defineClearTableLines(myCallback){this.clearTableLinesRef = myCallback;}");
        pointerVarLines.add("clearTableLines(code){return this.clearTableLinesRef(code, this.formRef);}");

        pointerVarLines.add("defineGetFormDataset(myCallback){this.getFormDatasetRef = myCallback;}");
        pointerVarLines.add("getFormDataset(){return this.getFormDatasetRef(this.formRef);}");

        pointerVarLines.add("defineAppendLineToComponent(myCallback){this.appendLineToComponentRef = myCallback;}");
        pointerVarLines.add("appendLineToComponent(componentPersistEntity){return this.appendLineToComponentRef(componentPersistEntity, this.formRef);}");

        pointerVarLines.add("defineSetFieldEditable(myCallback){this.setFieldEditableRef = myCallback;}");
        pointerVarLines.add("setFieldEditable(fieldCode, editable){return this.setFieldEditableRef(fieldCode, editable, this.formRef);}");

        pointerVarLines.add("defineGetFromBackend(myCallback){this.getFromBackendRef = myCallback;}");
        pointerVarLines.add("getFromBackend(url, callback){return this.getFromBackendRef(url, callback);}");

        pointerVarLines.add("defineSetFormTitle(myCallback){this.setFormTitleRef = myCallback;}");
        pointerVarLines.add("setFormTitle(title){return this.setFormTitleRef(title, this.formRef);}");

        pointerVarLines.add("defineSetFormDescription(myCallback){this.setFormDescriptionRef = myCallback;}");
        pointerVarLines.add("setFormDescription(description){return this.setFormDescriptionRef(description, this.formRef);}");

        pointerVarLines.add("defineSetAreaClass(myCallback){this.setAreaClassRef = myCallback;}");
        pointerVarLines.add("setAreaClass(code, cssClass){return this.setAreaClassRef(code, cssClass, this.formRef);}");

        pointerVarLines.add("defineSetAreaTitle(myCallback){this.setAreaTitleRef = myCallback;}");
        pointerVarLines.add("setAreaTitle(code, title){return this.setAreaTitleRef(code, title, this.formRef);}");

        pointerVarLines.add("defineSetAreaDescription(myCallback){this.setAreaDescriptionRef = myCallback;}");
        pointerVarLines.add("setAreaDescription(code, description){return this.setAreaDescriptionRef(code, description, this.formRef);}");

        pointerVarLines.add("defineGetArea(myCallback){this.getAreaRef = myCallback;}");
        pointerVarLines.add("getArea(code){return this.getAreaRef(code, this.formRef);}");

        pointerVarLines.add("defineNavigator(myCallback){this.navigateRef = myCallback;}");
        pointerVarLines.add("navigate(command){return this.navigateRef(command);}");

        pointerVarLines.add("defineSetHeaderTabEditable(myCallback){this.setHeaderTabEditableRef = myCallback;}");
        pointerVarLines.add("setHeaderTabEditable(code, editable){return this.setHeaderTabEditableRef(code, editable, this.formRef);}");

        pointerVarLines.add("defineSetActionButtonEditable(myCallback){this.setActionButtonEditableRef = myCallback;}");
        pointerVarLines.add("setActionButtonEditable(code, editable){return this.setActionButtonEditableRef(code, editable, this.formRef);}");

        pointerVarLines.add("defineSaveFormData(myCallback){this.saveFormDataRef = myCallback;}");
        pointerVarLines.add("saveFormData(callback){return this.saveFormDataRef(callback, this.formRef);}");

        pointerVarLines.add("defineSaveAndBackFormData(myCallback){this.saveAndBackFormDataRef = myCallback;}");
        pointerVarLines.add("saveAndBackFormData(){return this.saveAndBackFormDataRef(this.formRef);}");

        pointerVarLines.add("defineDeleteFormData(myCallback){this.deleteFormDataRef = myCallback;}");
        pointerVarLines.add("deleteFormData(){return this.deleteFormDataRef(this.formRef);}");

        pointerVarLines.add("defineGetParams(myCallback){this.getParamsRef = myCallback;}");
        pointerVarLines.add("getParams(code){return this.getParamsRef(code, this.formRef);}");

        pointerVarLines.add("defineSetTableRowActiveById(myCallback){this.setTableRowActiveByIdRef = myCallback;}");
        pointerVarLines.add("setTableRowActiveById(code, id){return this.setTableRowActiveByIdRef(code, id, this.formRef);}");

        pointerVarLines.add("defineDataset(myDataSet){this.dataSet = myDataSet;}");

        pointerVarLines.add("");
        return String.join("\n", pointerVarLines);
    }

}
