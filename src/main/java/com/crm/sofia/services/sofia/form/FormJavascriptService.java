package com.crm.sofia.services.sofia.form;

import com.crm.sofia.dto.sofia.form.base.FormAreaDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FormJavascriptService {

    public String generateDynamicHandlersJavaScriptAndEncode(FormDTO formDTO) {
        String script = this.generateDynamicHandlersScript(formDTO);
        byte[] encodedBytes = Base64.getEncoder().encode(script.getBytes());
        String encodedScript = new String(encodedBytes);

        return encodedScript;
    }

    private String generateDynamicHandlersScript(FormDTO formDTO) {
        List<String> nativeHandlerLines = new ArrayList<>();
        String nativeButtonClickHandlerString = this.generateNativeButtonClickHandler(formDTO);
        nativeHandlerLines.add(nativeButtonClickHandlerString);
        String nativeTableButtonClickHandlerString = this.generateNativeTableButtonClickHandler(formDTO);
        nativeHandlerLines.add(nativeTableButtonClickHandlerString);
        String nativeFormEventsHandlerString = this.generateNativeFormEventsHandler();
        nativeHandlerLines.add(nativeFormEventsHandlerString);
        String nativeFieldEventsHandlerString = this.generateNativeFieldEventsHandler(formDTO);
        nativeHandlerLines.add(nativeFieldEventsHandlerString);
        String nativeTableFieldEventsHandlerString = this.generateNativeTableFieldEventsHandler(formDTO);
        nativeHandlerLines.add(nativeTableFieldEventsHandlerString);
        String pointerVars = generatePointerVars();
        nativeHandlerLines.add(pointerVars);
        return String.join("\n", nativeHandlerLines);
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

        /* Header Buttons */
        formDTO.getFormActionButtons().forEach(formActionButton -> {
            nativeButtonClickHandlerLines.
                    add("if((btnCode == '" + formActionButton.getCode() + "') && " +
                            "(typeof btn_" + formActionButton.getCode() + "_click == \"function\")) " +
                            "btn_" + formActionButton.getCode() + "_click();");

        });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }

    private String generateNativeTableButtonClickHandler(FormDTO formDTO) {

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
        nativeButtonClickHandlerLines.add("function nativeTableButtonClickHandler(btnCode, formControlTable, dataLine) {");

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
                                                    "(typeof table_btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click == \"function\")) " +
                                                    "table_btn_" + formControlButtonControl.getFormControlButton().getCode() + "_click(formControlTable, dataLine);");

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

//        /* Table Fields */
//        formAreas.forEach(formAreaDTO -> {
//            formAreaDTO.getFormControls()
//                    .stream()
//                    .filter(formControlDTO -> formControlDTO.getType().equals("table"))
//                    .forEach(formControlDTO -> {
//                        formControlDTO.formControlTable.getFormControls()
//                                .stream()
//                                .forEach(formControl -> {
//                                    List<String> fieldCodeParts = new ArrayList<>();
//                                    fieldCodeParts.add(formControl.getFormControlField().getComponentPersistEntity().getCode());
//                                    fieldCodeParts.add("_");
//                                    fieldCodeParts.add(formControl.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName());
//                                    String fieldCode = String.join("", fieldCodeParts);
//
//                                    if (assignedFields.contains(fieldCode)) {
//                                        return;
//                                    }
//
//                                    List<String> fieldFunctionDefParts = new ArrayList<>();
//                                    fieldFunctionDefParts.add("on_");
//                                    fieldFunctionDefParts.add(fieldCode);
//                                    fieldFunctionDefParts.add("_");
//                                    fieldFunctionDefParts.add("%%type%%");
//
//                                    List<String> fieldFunctionParts = new ArrayList<>();
//                                    fieldFunctionParts.addAll(fieldFunctionDefParts);
//                                    fieldFunctionParts.add("(event);");
//
//                                    List<String> statementParts = new ArrayList<>();
//                                    statementParts.add("if(");
//                                    statementParts.add("(entityCode == '" + formControl.getFormControlField().getComponentPersistEntity().getCode() + "') && ");
//                                    statementParts.add("(fieldName == '" + formControl.getFormControlField().getComponentPersistEntityField().getPersistEntityField().getName() + "') && ");
//                                    statementParts.add("(type == '%%type%%') && ");
//                                    statementParts.add("(typeof " + String.join("", fieldFunctionDefParts) + " == \"function\")");
//                                    statementParts.add(") ");
//                                    statementParts.add(String.join("", fieldFunctionParts));
//
//                                    String statement = String.join("", statementParts);
//
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keydown"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "keyup"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dblclick"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "click"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "change"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focus"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "focusout"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "drag"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "dragend"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mousemove"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseout"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseover"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "mouseup"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "resize"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listselected"));
//                                    nativeFieldEventHandlerLines.add(statement.replace("%%type%%", "listcleared"));
//
//                                    assignedFields.add(fieldCode);
//                                });
//                    });
//        });

        nativeFieldEventHandlerLines.add("}");
        return String.join("\n", nativeFieldEventHandlerLines);
    }

    private String generateNativeTableFieldEventsHandler(FormDTO formDTO) {
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
        nativeFieldEventHandlerLines.add("function nativeTableFieldEventsHandler(entityCode, fieldName, type, event, formControlTable, dataLine) {");

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

                                    List<String> fieldFunctionParts = new ArrayList<>();
                                    fieldFunctionParts.addAll(fieldFunctionDefParts);
                                    fieldFunctionParts.add("(event, formControlTable, dataLine);");

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
        pointerVarLines.add("var getParams;");
        pointerVarLines.add("var setTableRowActiveById;");
        pointerVarLines.add("var appendLineToComponent;");
        pointerVarLines.add("var setAreaClass;");
        pointerVarLines.add("var setAreaTitle;");
        pointerVarLines.add("var setAreaDescription;");
        pointerVarLines.add("var getArea;");
        pointerVarLines.add("var navigate;");
        pointerVarLines.add("var saveAndBackFormData;");
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
        pointerVarLines.add("function defineAppendLineToComponent(myCallback){appendLineToComponent = myCallback;}");
        pointerVarLines.add("function defineSetFieldEditable(myCallback){setFieldEditable = myCallback;}");
        pointerVarLines.add("function defineGetFromBackend(myCallback){getFromBackend = myCallback;}");
        pointerVarLines.add("function defineSetFormTitle(myCallback){setFormTitle = myCallback;}");
        pointerVarLines.add("function defineSetFormDescription(myCallback){setFormDescription = myCallback;}");
        pointerVarLines.add("function defineSetAreaClass(myCallback){setAreaClass = myCallback;}");
        pointerVarLines.add("function defineSetAreaTitle(myCallback){setAreaTitle = myCallback;}");
        pointerVarLines.add("function defineSetAreaDescription(myCallback){setAreaDescription = myCallback;}");
        pointerVarLines.add("function defineGetArea(myCallback){getArea = myCallback;}");
        pointerVarLines.add("function defineNavigate(myCallback){navigate = myCallback;}");
        pointerVarLines.add("function defineSetHeaderTabEditable(myCallback){setHeaderTabEditable = myCallback;}");
        pointerVarLines.add("function defineSetActionButtonEditable(myCallback){setActionButtonEditable = myCallback;}");
        pointerVarLines.add("function defineSaveFormData(myCallback){saveFormData = myCallback;}");
        pointerVarLines.add("function defineSaveAndBackFormData(myCallback){saveAndBackFormData = myCallback;}");
        pointerVarLines.add("function defineDeleteFormData(myCallback){deleteFormData = myCallback;}");
        pointerVarLines.add("function defineGetParams(myCallback){getParams = myCallback;}");
        pointerVarLines.add("function defineSetTableRowActiveById(myCallback){setTableRowActiveById = myCallback;}");

        pointerVarLines.add("");
        return String.join("\n", pointerVarLines);
    }

}
