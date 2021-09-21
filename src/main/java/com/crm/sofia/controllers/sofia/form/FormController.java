package com.crm.sofia.controllers.sofia.form;

import com.crm.sofia.dto.sofia.component.user.ComponentUiDTO;
import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.dto.sofia.form.user.FormUiDTO;
import com.crm.sofia.services.sofia.form.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
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
    FormDTO getObject(@RequestParam("id") Long formId,
                      @RequestParam("selection-id") String selectionId) {
        return this.formService.getObjectAndRetrieveData(formId, selectionId);
    }

    @GetMapping(path = "ui")
    FormUiDTO getUiObject(@RequestParam("id") Long formId) {
        return this.formService.getUiObject(formId);
    }

    @GetMapping(path = "data")
    ComponentUiDTO getData(@RequestParam("id") Long formId,
                           @RequestParam("selection-id") String selectionId) {
        return this.formService.retrieveData(formId, selectionId);
    }

    @GetMapping(path = "instance-version", produces = "text/plain")
    String getInstanceVersion(@RequestParam("id") Long formId) {
        return this.formService.getInstanceVersion(formId);
    }

    @PostMapping
    public String postObjectData(@RequestParam("id") Long formId,
                                 @RequestBody Map<String, Map<String, Object>> parameters) {
        return this.formService.save(formId, parameters);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-javascript/{id}/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormJavaScript(@PathVariable("id") Long formId) {
        return this.formService.getJavaScript(formId);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-javascript/{id}/min/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormMinJavaScript(@PathVariable("id") Long formId) {
        return this.formService.getMinJavaScript(formId);
    }


    @Transactional
    @RequestMapping(value = "/dynamic-javascripts/factory.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormJavaScriptFactory() {
        return this.formService.getFormJavaScriptFactory();
    }


    @Transactional
    @RequestMapping(value = "/dynamic-cssscript/{id}/script.css", method = RequestMethod.GET, produces = "text/css;")
    String getFormCssScript(@PathVariable("id") Long formId) {
        return this.formService.getCssScript(formId);
    }
}
