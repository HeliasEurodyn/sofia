package com.crm.sofia.controllers.cityscape.rmt;

import com.crm.sofia.dto.cityscape.rtm.RmtDTO;
import com.crm.sofia.services.cityscape.rtm.RmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/rmt")
public class RmtController {

    private final RmtService rmtService;

    public RmtController(RmtService rmtService) {
        this.rmtService = rmtService;
    }

    @Operation(summary = "Get List (Returns generic model list containing, Risk Assessments - Services - Composite Assets)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(path = "/list")
    public List<RmtDTO> getObjectById() {
        return this.rmtService.runRiskAssessmentList();
    }

    @Operation(summary = "Get Page (Returns generic model list containing, Risk Assessments - Services - Composite Assets)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(path = "/list/page/{page}/size/{size}")
    public List<RmtDTO> getObjectById(@PathVariable("page") Long page, @PathVariable("size") Long size) {
        return this.rmtService.runRiskAssessmentPage(page, size);
    }

    @Operation(summary = "Get By Id (Returns fully detailed model containing, Risk Assessment - Services - Composite Assets - Assets - Threats - Coutermeasures)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/{id}")
    public RmtDTO getObjectById(@PathVariable("id") Long id) {
        return this.rmtService.retrieveRiskAssessmentById(id);
    }

    @Operation(summary = "Run Rmt analysis for Risk Assesment Id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/analysis/{id}")
    public RmtDTO sendToRmtById(@PathVariable("id") Long id) {
        return this.rmtService.sendToRmtById(id);
    }

    @Operation(summary = "Run Rmt analysis for Risk Assesment Object", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(value = "/analyze")
    public RmtDTO sendToRmt(@RequestBody RmtDTO rmt) {
        return this.rmtService.sendToRmt(rmt);
    }
}
