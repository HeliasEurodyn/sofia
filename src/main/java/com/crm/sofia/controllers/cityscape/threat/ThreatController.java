package com.crm.sofia.controllers.cityscape.threat;

import com.crm.sofia.model.cityscape.threat.Threat;
import com.crm.sofia.services.cityscape.threat.ThreatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping( "/threat")
@Tag(name = "Threats", description = "Endpoints collection for Threat CRUD operations.")
public class ThreatController {

    private final ThreatService threatService;

    public ThreatController(ThreatService threatService) {
        this.threatService = threatService;
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(path = "")
    public List<Threat> getObjectById() {
        return this.threatService.getObject();
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/{id}")
    public Threat getObjectById(@PathVariable("id") Long id) {
        return this.threatService.getObject(id);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public Threat postObject(@RequestBody Threat threat) {
        return this.threatService.postObject(threat);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping
    public Threat putObject(@RequestBody Threat threat) {
        return this.threatService.postObject(threat);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping
    public Boolean delete(@RequestParam("id") Long id) {
        return this.threatService.delete(id);
    }

}
