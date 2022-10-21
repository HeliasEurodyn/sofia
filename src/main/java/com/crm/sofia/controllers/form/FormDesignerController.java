package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.services.form.FormDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    FormDTO getObject(@RequestParam("id") String id) {
        return this.formDesignerService.getObject(id);
    }

    @PostMapping
    public FormDTO postObject(@RequestBody FormDTO dto) throws Exception {
        FormDTO createdDTO = this.formDesignerService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public FormDTO putObject(@RequestBody FormDTO dto) throws Exception {
        FormDTO createdDTO = this.formDesignerService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.formDesignerService.deleteObject(id);
    }

    @RequestMapping(value = "/clear-cache", method = RequestMethod.GET)
    boolean clearCache() {
        return this.formDesignerService.clearCache();
    }

    @GetMapping(path = "/business-units")
    List<String> getBusinessUnits() {
        return this.formDesignerService.getBusinessUnits();
    }

}
