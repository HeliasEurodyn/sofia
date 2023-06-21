package com.crm.sofia.controllers.list;

import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.services.list.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/list")
public class ListController {
    @Autowired
    private  ListService listService;
    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping(path = "ui")
    ListDTO getUiObject(@RequestParam("id") String id,
                          @RequestParam(defaultValue = "0", name = "language-id") String languageId) {
       return this.listService.retrieveListAndCalcDefaultExpression(id, languageId);
    }

    @GetMapping(path = "/results")
    ListResultsDataDTO getObject(@RequestParam Map<String, String> parameters,
                                 @RequestParam("id") String id,
                                 @RequestParam(defaultValue = "0", name = "language-id") String languageId) {
        ListDTO listDTO = this.listService.retrieveListWithBaseQuery(id, languageId);
        return this.listService.getObjectDataByParameters(parameters,0L, listDTO);
    }

    @GetMapping(path = "/results/page/{page}")
    ListResultsDataDTO getPageObject(@RequestParam Map<String, String> parameters,
                                     @PathVariable("page") Long page,
                                     @RequestParam("id") String id,
                                     @RequestParam(name = "language-id", defaultValue = "0") String languageId) {
        ListDTO listDTO = this.listService.retrieveListWithBaseQuery(id,languageId);
        return this.listService.getObjectDataByParameters(parameters,page,listDTO);
    }

    @GetMapping(path = "/left-grouping/results")
    List<GroupEntryDTO> getObjectLeftGroupingData(@RequestParam Map<String, String> parameters,
                                                  @RequestParam("id") String id,
                                                  @RequestParam(name = "language-id", defaultValue = "0") String languageId) {
        ListDTO listDTO = this.listService.retrieveListWithBaseQuery(id, languageId);
        return this.listService.getObjectLeftGroupingDataByParameters(parameters, listDTO);
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

    @PutMapping(path = "/update-field")
    void updateField(@RequestParam("id") String id,
                     @RequestParam("field") String field,
                     @RequestParam("field-value") Object fieldValue,
                     @RequestParam("rel") Object rel,
                     @RequestParam(name = "language-id", defaultValue = "0") String languageId) {
        this.listService.updateField(id, field, fieldValue, rel, languageId);
    }

    @GetMapping(path = "/test")
    Object test() {
        return this.listService.test();
    }

}
