package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.services.form.FormService;
import com.crm.sofia.utils.ExcelGenerator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/form")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }


    @GetMapping
    List<FormDTO> getObject() {
        return this.formService.getObject();
    }

    @GetMapping(path = "/by-id")
    FormDTO getObject(@RequestParam("id") Long id) {
        return this.formService.getObject(id);
    }

    @PostMapping
    public FormDTO postObject(@RequestBody FormDTO dto) {
        FormDTO createdDTO = this.formService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public FormDTO putObject(@RequestBody FormDTO dto) {
        FormDTO createdDTO = this.formService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.formService.deleteObject(id);
    }

}
