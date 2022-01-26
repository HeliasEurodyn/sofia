package com.crm.sofia.controllers.cityscape.rmt;

import com.crm.sofia.dto.cityscape.rtm.RmtDTO;
import com.crm.sofia.services.cityscape.rtm.RmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(path = "/list")
    public List<RmtDTO> getObjectById() {
        return this.rmtService.runRiskAssessmentList();
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/{id}")
    public RmtDTO getObjectById(@PathVariable("id") Long id) {
        return this.rmtService.retrieveRiskAssessmentById(id);
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/analysis/{id}")
    public RmtDTO sendToRmt(@PathVariable("id") Long id) {
        return this.rmtService.sendToRmt(id);
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/json-file/{id}")
    public ResponseEntity<byte[]> downloadJsonObjectById(@PathVariable("id") Long id) {
        byte[] rmtJsonBytes = this.rmtService.downloadJsonObjectBytesById(id);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=rmt.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(rmtJsonBytes.length)
                .body(rmtJsonBytes);
    }

}
