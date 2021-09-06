package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.form.base.FormAreaDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.dto.sofia.list.base.GroupEntryDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.dto.sofia.list.base.ListResultsDataDTO;
import com.crm.sofia.dto.sofia.list.user.ListComponentFieldUiDTO;
import com.crm.sofia.dto.sofia.list.user.ListUiDTO;
import com.crm.sofia.mapper.sofia.list.designer.ListMapper;
import com.crm.sofia.mapper.sofia.list.user.ListUiMapper;
import com.crm.sofia.model.sofia.expression.ExprResponce;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.native_repository.sofia.list.ListNativeRepository;
import com.crm.sofia.repository.sofia.list.ListRepository;
import com.crm.sofia.services.sofia.expression.ExpressionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//        String pointerVars = generatePointerVars();
//        nativeHandlerLines.add(pointerVars);
        return String.join("\n", nativeHandlerLines);
    }

    private String generateListNativeRowButtonClickHandler(ListDTO listDTO) {
        List<String> nativeButtonClickHandlerLines = new ArrayList<>();
        nativeButtonClickHandlerLines.add("");
        nativeButtonClickHandlerLines.add("function listNativeRowButtonClickHandler(btnCode, row) {");

        /* List Row Action Buttons */
        listDTO.getListComponentActionFieldList().forEach(formAreaDTO -> {

            nativeButtonClickHandlerLines.
                    add("if((btnCode == '" + formAreaDTO.getCode() + "') && " +
                            "(typeof btn_" + formAreaDTO.getCode() + "_click == \"function\")) " +
                            "btn_" + formAreaDTO.getCode() + "_click(row);");
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
                            "(typeof btn_" + formAreaDTO.getCode() + "_click == \"function\")) " +
                            "btn_" + formAreaDTO.getCode() + "_click();");
        });

        nativeButtonClickHandlerLines.add("}");
        return String.join("\n", nativeButtonClickHandlerLines);
    }
}
