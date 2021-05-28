package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.services.form.FormDesignerService;
import com.crm.sofia.services.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/form-designer")
public class FormDesignerController {

    private final FormDesignerService formDesignerService;

    public FormDesignerController(FormDesignerService formDesignerService) {
        this.formDesignerService = formDesignerService;
    }

    @GetMapping
    List<FormDTO> getObject() {
        return this.formDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    FormDTO getObject(@RequestParam("id") Long id) {
        return this.formDesignerService.getObject(id);
    }

    @PostMapping
    public FormDTO postObject(@RequestBody FormDTO dto) {
        FormDTO createdDTO = this.formDesignerService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public FormDTO putObject(@RequestBody FormDTO dto) {
        FormDTO createdDTO = this.formDesignerService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.formDesignerService.deleteObject(id);
    }

}