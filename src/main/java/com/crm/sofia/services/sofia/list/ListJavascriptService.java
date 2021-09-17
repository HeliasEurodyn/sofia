package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.list.base.ListDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ListJavascriptService {

    public String generate(ListDTO listDTO) {
        String script = this.generateDynamicHandlersScript(listDTO);
        byte[] encodedBytes = Base64.getEncoder().encode(script.getBytes());
        String encodedScript = new String(encodedBytes);

        return encodedScript;
    }


    private String generateDynamicHandlersScript(ListDTO listDTO) {
        List<String> nativeHandlerLines = new ArrayList<>();
        String listNativeRowButtonClickHandlerString = this.generateListNativeRowButtonClickHandler(listDTO);
        nativeHandlerLines.add(listNativeRowButtonClickHandlerString);
        String listNativeHeaderButtonHandlerString = this.generateListNativeHeaderButtonClickHandler(listDTO);
        nativeHandlerLines.add(listNativeHeaderButtonHandlerString);
        String pointerVars = generatePointerVars();
        nativeHandlerLines.add(pointerVars);
        return String.join("\n", nativeHandlerLines);
    }

    private String generateListNativeRowButtonClickHandler(ListDTO listDTO) {
        List<String> nativeButtonClickHandlerLines = new ArrayList<>();
        nativeButtonClickHandlerLines.add("");
        nativeButtonClickHandlerLines.add("function listNativeRowButtonClickHandler(btnCode, row) {");

        /* List Row Action Buttons */
        listDTO.getListComponentActionFieldList().forEach(btn -> {

            nativeButtonClickHandlerLines.
                    add("if((btnCode == '" + btn.getCode() + "') && " +
                            "(typeof list_btn_" + btn.getCode() + "_click == \"function\")) " +
                            "list_btn_" + btn.getCode() + "_click(row);");


            btn.getListComponentActionFieldList().forEach(subBtn -> {

                nativeButtonClickHandlerLines.
                        add("if((btnCode == '" + subBtn.getCode() + "') && " +
                                "(typeof list_btn_" + subBtn.getCode() + "_click == \"function\")) " +
                                "list_btn_" + subBtn.getCode() + "_click(row);");
            });

        });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }

    private String generateListNativeHeaderButtonClickHandler(ListDTO listDTO) {
        List<String> nativeButtonClickHandlerLines = new ArrayList<>();
        nativeButtonClickHandlerLines.add("");
        nativeButtonClickHandlerLines.add("function listNativeHeaderButtonClickHandler(btnCode) {");

        /* List Header Buttons */
        listDTO.getListActionButtons().forEach(formAreaDTO -> {

            nativeButtonClickHandlerLines.
                    add("if((btnCode == '" + formAreaDTO.getCode() + "') && " +
                            "(typeof list_btn_" + formAreaDTO.getCode() + "_click == \"function\")) " +
                            "list_btn_" + formAreaDTO.getCode() + "_click();");

            formAreaDTO.getListActionButtons().forEach(formSubAreaDTO -> {

                nativeButtonClickHandlerLines.
                        add("if((btnCode == '" + formSubAreaDTO.getCode() + "') && " +
                                "(typeof list_btn_" + formSubAreaDTO.getCode() + "_click == \"function\")) " +
                                "list_btn_" + formSubAreaDTO.getCode() + "_click();");
            });

        });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }

    private String generatePointerVars() {
        List<String> pointerVarLines = new ArrayList<>();
        pointerVarLines.add("");
        pointerVarLines.add("var navigate;");
        pointerVarLines.add("");
        pointerVarLines.add("function defineNavigate(myCallback){navigate = myCallback;}");
        pointerVarLines.add("");
        return String.join("\n", pointerVarLines);
    }


}
