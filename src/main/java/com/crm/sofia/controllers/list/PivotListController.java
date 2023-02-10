package com.crm.sofia.controllers.list;

import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.services.list.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/pivot-list")
public class PivotListController {

    private final ListService listService;

    public PivotListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping(path = "ui")
    ListDTO getUiObject(@RequestParam("id") String id,
                        @RequestParam(name = "language-id", defaultValue = "0") String languageId) {
        return this.listService.retrieveListAndCalcDefaultExpression(id, languageId);
    }

    @GetMapping(path = "/results")
    ListResultsDataDTO getObject(@RequestParam Map<String, String> parameters,
                                 @RequestParam("id") String id,
                                 @RequestParam(name = "language-id", defaultValue = "0") String languageId) {
        ListDTO listDTO = this.listService.retrieveListWithBaseQuery(id, languageId);
        return this.listService.getPivotObjectDataByParameters(parameters, listDTO);
    }

    @GetMapping(path = "instance-version", produces = "text/plain")
    String getInstanceVersion(@RequestParam("id") String formId) {
        return this.listService.getInstanceVersion(formId);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/min/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getMinJavaScript(@PathVariable("id") String id) {
        return this.listService.getMinJavaScript(id);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getJavaScript(@PathVariable("id") String id) {
        return this.listService.getJavaScript(id);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-cssscript/{id}/script.css", method = RequestMethod.GET, produces = "text/css;")
    String getFormCssScript(@PathVariable("id") String id) {
        return this.listService.getCssScript(id);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-javascripts/factory.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormJavaScripty() {
        return this.listService.getJavaScriptFactory();
    }


}
