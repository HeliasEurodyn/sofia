package com.crm.sofia.controllers.rule;

import com.crm.sofia.dto.rule.RuleDTO;
import com.crm.sofia.dto.rule.RuleExecutionParametersDTO;
import com.crm.sofia.dto.rule.RuleSettingsDTO;
import com.crm.sofia.services.rule.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping(path = "/by-id")
    RuleDTO getObject(@RequestParam("id") String id) {
        RuleDTO ruleDTO = this.ruleService.getObject(id);
        return ruleDTO;
    }

    @GetMapping(path = "/settings/by-id")
    RuleSettingsDTO getObjectSettings(@RequestParam("id") String id) {
        return this.ruleService.getObjectSettings(id);
    }

    @PostMapping
    public RuleDTO postObject(@RequestBody RuleDTO dto) {
        RuleDTO createdDTO = this.ruleService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public RuleDTO putObject(@RequestBody RuleDTO dto) {
        RuleDTO createdDTO = this.ruleService.putObject(dto);
        return createdDTO;
    }

//    @PostMapping(path = "/execute-query")
//    public Object executeQuery(@RequestBody List<RuleDTO> rules, @RequestParam("query-id") String queryId ) {
//        return this.ruleService.executeQuery(rules, queryId);
//    }

    @PostMapping(path = "/execute-query")
    public Object executeQuery(@RequestBody List<RuleExecutionParametersDTO> ruleExecParameters, @RequestParam("query-id") String queryId ) {
        return this.ruleService.executeQuery(ruleExecParameters, queryId);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.ruleService.deleteObject(id);
    }

}
