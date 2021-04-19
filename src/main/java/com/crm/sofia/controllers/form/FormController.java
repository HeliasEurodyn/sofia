package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.services.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/data/by-id")
    FormDTO getObject(@RequestParam("id") Long formId,
                      @RequestParam("selection-id") String selectionId) {
        return this.formService.getObjectAndRetrieveData(formId, selectionId);
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

    @PostMapping(path = "/data")
    public String postObjectData(@RequestParam("id") Long formId,
                               @RequestBody Map<String, Map<String, Object>> parameters) throws Exception {
        return this.formService.save(formId, parameters);
    }


    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.formService.deleteObject(id);
    }

}
