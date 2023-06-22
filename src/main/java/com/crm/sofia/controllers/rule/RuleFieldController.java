package com.crm.sofia.controllers.rule;

import com.crm.sofia.dto.rule.RuleFieldDTO;
import com.crm.sofia.services.rule.RuleFieldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/rule-field")
public class RuleFieldController {

    private final RuleFieldService ruleFieldService;

    public RuleFieldController(RuleFieldService ruleFieldService) {
        this.ruleFieldService = ruleFieldService;
    }

    @GetMapping
    List<RuleFieldDTO> getObject() {
        return this.ruleFieldService.getObject();
    }

    @GetMapping(path = "/by-id")
    RuleFieldDTO getObject(@RequestParam("id") String id) {
        return this.ruleFieldService.getObject(id);
    }

    @PostMapping
    public RuleFieldDTO postObject(@RequestBody RuleFieldDTO dto) {
        RuleFieldDTO createdDTO = this.ruleFieldService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public RuleFieldDTO putObject(@RequestBody RuleFieldDTO dto) {
        RuleFieldDTO createdDTO = this.ruleFieldService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.ruleFieldService.deleteObject(id);
    }

}
