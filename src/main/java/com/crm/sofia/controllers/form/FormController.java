package com.crm.sofia.controllers.form;

import com.crm.sofia.dto.component.user.ComponentUiDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.dto.form.user.FormUiDTO;
import com.crm.sofia.services.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    FormDTO getObject(@RequestParam("id") String formId,
                      @RequestParam("selection-id") String selectionId) {
        return this.formService.getObjectAndRetrieveData(formId, selectionId);
    }

    @GetMapping(path = "ui")
    FormUiDTO getUiObject(@RequestParam("id") String formId) {
        return this.formService.getUiObject(formId);
    }

    @GetMapping(path = "data")
    ComponentUiDTO getData(@RequestParam("id") String formId,
                           @RequestParam("selection-id") String selectionId) {
        return this.formService.retrieveUiData(formId, selectionId);
    }

    @GetMapping(path = "clone-data")
    ComponentUiDTO getCloneData(@RequestParam("id") String formId,
                           @RequestParam("selection-id") String selectionId) {
        return this.formService.retrieveClonedData(formId, selectionId);
    }

    @GetMapping(path = "instance-version", produces = "text/plain")
    String getInstanceVersion(@RequestParam("id") String formId) {
        return this.formService.getInstanceVersion(formId);
    }

    @PostMapping
    public String postObjectData(@RequestParam("id") String formId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        return this.formService.save(formId, parameters);
    }

    @PutMapping
    public String putObjectData(@RequestParam("id") String formId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        return this.formService.save(formId, parameters);
    }

    @DeleteMapping
    public void deleteObjectData(@RequestParam("id") String formId, @RequestParam("selection-id") String selectionId) {
        this.formService.delete(formId, selectionId);
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
