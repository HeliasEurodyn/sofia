package com.crm.sofia.controllers.rule;


import com.crm.sofia.dto.rule.RuleOperatorDTO;
import com.crm.sofia.services.rule.RuleOperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/rule-operator")
public class RuleOparetorController {

    private final RuleOperatorService ruleOperatorService;

    public RuleOparetorController(RuleOperatorService ruleOperatorService) {
        this.ruleOperatorService = ruleOperatorService;
    }

    @GetMapping
    List<RuleOperatorDTO> getObject() {
        return this.ruleOperatorService.getObject();
    }

    @GetMapping(path = "/by-id")
    RuleOperatorDTO getObject(@RequestParam("id") String id) {
        return this.ruleOperatorService.getObject(id);
    }

    @PostMapping
    public RuleOperatorDTO postObject(@RequestBody RuleOperatorDTO dto) {
        RuleOperatorDTO createdDTO = this.ruleOperatorService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public RuleOperatorDTO putObject(@RequestBody RuleOperatorDTO dto) {
        RuleOperatorDTO createdDTO = this.ruleOperatorService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.ruleOperatorService.deleteObject(id);
    }

}
