package com.crm.sofia.services.list;

import org.springframework.stereotype.Service;

@Service
public class ListJavascriptService {

//    public String generateDynamicScript(ListDTO listDTO) {
//
//        List<String> nativeHandlerLines = new ArrayList<>();
//
//        String classInitString = this.generateClassInit(listDTO);
//        nativeHandlerLines.add(classInitString);
//
//        String pointerVars = generatePointerVars();
//        nativeHandlerLines.add(pointerVars);
//
//        String userScriptsString = this.generateUserScripts(listDTO);
//        nativeHandlerLines.add(userScriptsString);
//
//        String nativeFormEventsHandlerString = this.generateNativeListEventsHandler();
//        nativeHandlerLines.add(nativeFormEventsHandlerString);
//
//        String listNativeRowButtonClickHandlerString = this.generateListNativeRowButtonClickHandler(listDTO);
//        nativeHandlerLines.add(listNativeRowButtonClickHandlerString);
//
//        String listNativeHeaderButtonHandlerString = this.generateListNativeHeaderButtonClickHandler(listDTO);
//        nativeHandlerLines.add(listNativeHeaderButtonHandlerString);
//
//        String classEndString = this.generateClassEnd();
//        nativeHandlerLines.add(classEndString);
//
//        return String.join("\n", nativeHandlerLines);
//    }

//    private String generateNativeListEventsHandler() {
//        List<String> nativeFormEventsHandler = new ArrayList<>();
//        nativeFormEventsHandler.add("");
//        nativeFormEventsHandler.add("nativeListEventsHandler(type, metadata) {");
//
//        nativeFormEventsHandler.
//                add("if((type == 'onListOpen') && " +
//                        "(typeof this.onListOpen == \"function\")) " +
//                        "this.onListOpen(metadata);");
//
//        nativeFormEventsHandler.
//                add("if((type == 'onListDataLoaded') && " +
//                        "(typeof this.onListDataLoaded == \"function\")) " +
//                        "this.onListDataLoaded(metadata);");
//
//        nativeFormEventsHandler.add("}");
//        return String.join("\n", nativeFormEventsHandler);
//    }

//    private String generateUserScripts(ListDTO listDTO) {
//        List<String> decodedScripts = new ArrayList<>();
//        listDTO.getListScripts().forEach(listScript -> {
//            byte[] decodedBytes = Base64.getDecoder().decode(listScript.getScript());
//            String decodedScript = new String(decodedBytes);
//            decodedScripts.add(decodedScript);
//        });
//
//        return String.join("\n", decodedScripts);
//    }

//    private String generateClassInit(ListDTO listDTO) {
//        List<String> classLines = new ArrayList<>();
//
//        List<String> classDefLines = new ArrayList<>();
//        classDefLines.add("class ListDynamicScript");
//        classDefLines.add(listDTO.getId().replace("-","_"));
//        classDefLines.add(" {");
//        String classDef = String.join("", classDefLines);
//
//        String constructor = "constructor() {}";
//
//        classLines.add(classDef);
//        classLines.add(constructor);
//
//        return String.join("\n", classLines);
//    }

//    private String generateClassEnd() {
//        return "}";
//    }

//    private String generateListNativeRowButtonClickHandler(ListDTO listDTO) {
//        List<String> nativeButtonClickHandlerLines = new ArrayList<>();
//        nativeButtonClickHandlerLines.add("");
//        nativeButtonClickHandlerLines.add("listNativeRowButtonClickHandler(btnCode, row) {");
//
//        /* List Row Action Buttons */
//        listDTO.getListComponentActionFieldList().forEach(btn -> {
//
//            nativeButtonClickHandlerLines.
//                    add("if((btnCode == '" + btn.getCode() + "') && " +
//                            "(typeof this.list_btn_" + btn.getCode() + "_click == \"function\")) " +
//                            "this.list_btn_" + btn.getCode() + "_click(row);");
//
//
//            btn.getListComponentActionFieldList().forEach(subBtn -> {
//
//                nativeButtonClickHandlerLines.
//                        add("if((btnCode == '" + subBtn.getCode() + "') && " +
//                                "(typeof this.list_btn_" + subBtn.getCode() + "_click == \"function\")) " +
//                                "this.list_btn_" + subBtn.getCode() + "_click(row);");
//            });
//
//        });
//
//        nativeButtonClickHandlerLines.add("}");
//        return String.join("\n", nativeButtonClickHandlerLines);
//    }
//
//    private String generateListNativeHeaderButtonClickHandler(ListDTO listDTO) {
//        List<String> nativeButtonClickHandlerLines = new ArrayList<>();
//        nativeButtonClickHandlerLines.add("");
//        nativeButtonClickHandlerLines.add("listNativeHeaderButtonClickHandler(btnCode) {");
//
//        /* List Header Buttons */
//        listDTO.getListActionButtons().forEach(formAreaDTO -> {
//
//            nativeButtonClickHandlerLines.
//                    add("if((btnCode == '" + formAreaDTO.getCode() + "') && " +
//                            "(typeof this.list_btn_" + formAreaDTO.getCode() + "_click == \"function\")) " +
//                            "this.list_btn_" + formAreaDTO.getCode() + "_click();");
//
//            formAreaDTO.getListActionButtons().forEach(formSubAreaDTO -> {
//
//                nativeButtonClickHandlerLines.
//                        add("if((btnCode == '" + formSubAreaDTO.getCode() + "') && " +
//                                "(typeof this.list_btn_" + formSubAreaDTO.getCode() + "_click == \"function\")) " +
//                                "this.list_btn_" + formSubAreaDTO.getCode() + "_click();");
//            });
//
//        });
//
//        nativeButtonClickHandlerLines.add("}");
//        return String.join("\n", nativeButtonClickHandlerLines);
//    }
//
//    private String generatePointerVars() {
//        List<String> pointerVarLines = new ArrayList<>();
//        pointerVarLines.add("");
//        pointerVarLines.add("listRef = null;");
//        pointerVarLines.add("");
//        pointerVarLines.add("navigateRef = null;");
//        pointerVarLines.add("getFromBackendRef = null;");
//        pointerVarLines.add("");
//        pointerVarLines.add("defineListNavigator(myCallback){this.navigateRef = myCallback;}");
//        pointerVarLines.add("navigate(command){return this.navigateRef(command);}");
//
//        pointerVarLines.add("defineGetFromBackend(myCallback){this.getFromBackendRef = myCallback;}");
//        pointerVarLines.add("getFromBackend(url, callback){return this.getFromBackendRef(url, callback);}");
//
//        pointerVarLines.add("");
//        return String.join("\n", pointerVarLines);
//    }
//
//    public String minify(String script) throws Exception {
//        Reader reader = new StringReader(script);
//        Writer writer = new StringWriter();
//        JSMin jsMin = new JSMin(reader, writer);
//        jsMin.jsmin();
//        String scriptMin = writer.toString();
//        return scriptMin;
//    }
}
