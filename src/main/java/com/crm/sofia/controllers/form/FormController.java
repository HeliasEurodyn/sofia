package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.component.user.ComponentUiDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.dto.form.user.FormUiDTO;
import com.crm.sofia.services.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/form")
public class FormController {
    @Autowired
    private FormService formService;
    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping(path = "ui")
    FormUiDTO getUiObject(@RequestParam("id") String formId,
                          @RequestParam(defaultValue = "0", name = "language-id") String languageId) {
        return this.formService.getUiObject(formId, languageId);
    }

    @GetMapping(path = "data")
    ComponentUiDTO getData(@RequestParam("id") String formId,
                           @RequestParam("selection-id") String selectionId) {
        FormDTO formDTO = this.formService.getObject(formId);
        return this.formService.retrieveUiData(formDTO, selectionId);
    }

    @GetMapping(path = "clone-data")
    ComponentUiDTO getCloneData(@RequestParam("id") String formId,
                           @RequestParam("selection-id") String selectionId) {
        FormDTO formDTO = this.formService.getObject(formId);
        return this.formService.retrieveClonedData(formDTO, selectionId);
    }

    @GetMapping(path = "instance-version", produces = "text/plain")
    String getInstanceVersion(@RequestParam("id") String formId) {
        return this.formService.getInstanceVersion(formId);
    }

    @PostMapping
    public String postObjectData(@RequestParam("id") String formId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        FormDTO formDTO = this.formService.getObject(formId);
        return this.formService.save(formDTO, parameters);
    }

    @PutMapping
    public String putObjectData(@RequestParam("id") String formId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
       FormDTO formDTO = this.formService.getObject(formId);
       return this.formService.save(formDTO, parameters);
    }

    @DeleteMapping
    public void deleteObjectData(@RequestParam("id") String formId, @RequestParam("selection-id") String selectionId) {
        /* Retrieve FormDTO from Database */
        FormDTO formDTO = this.formService.getObject(formId);

        this.formService.delete(formDTO, selectionId);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormJavaScript(@PathVariable("id") String formId) {
        return this.formService.getJavaScript(formId);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/min/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormMinJavaScript(@PathVariable("id") String formId) {
        return this.formService.getMinJavaScript(formId);
    }

    @RequestMapping(value = "/dynamic-javascripts/factory.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormJavaScriptFactory() {
        return this.formService.getJavaScriptFactory();
    }

    @RequestMapping(value = "/dynamic-cssscript/{id}/script.css", method = RequestMethod.GET, produces = "text/css;")
    String getFormCssScript(@PathVariable("id") String formId) {
        return this.formService.getCssScript(formId);
    }

}
