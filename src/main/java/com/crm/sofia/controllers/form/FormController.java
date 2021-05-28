package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.services.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping(path = "/by-id")
    FormDTO getObject(@RequestParam("id") Long formId,
                      @RequestParam("selection-id") String selectionId) {
        return this.formService.getObjectAndRetrieveData(formId, selectionId);
    }

    @PostMapping
    public String postObjectData(@RequestParam("id") Long formId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        return this.formService.save(formId, parameters);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-script/{id}/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormScript(@PathVariable("id") Long formId) {
        return this.formService.getFormScript(formId);
    }

}
