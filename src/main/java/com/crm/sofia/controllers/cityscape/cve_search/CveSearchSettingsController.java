package com.crm.sofia.controllers.cityscape.cve_search;

import com.crm.sofia.dto.cityscape.cve_search.CveSearchSettingsDTO;
import com.crm.sofia.services.cityscape.cve_search.CveSearchSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/cve-search-settings")
public class CveSearchSettingsController {
    private final CveSearchSettingsService cveSearchSettingsService;

    public CveSearchSettingsController(CveSearchSettingsService cveSearchSettingsService) {
        this.cveSearchSettingsService = cveSearchSettingsService;
    }

    @GetMapping(path = "/unique")
    CveSearchSettingsDTO getObject() {
        return this.cveSearchSettingsService.getObject();
    }

    @PostMapping
    public CveSearchSettingsDTO postObject(@RequestBody CveSearchSettingsDTO dto) {
        return this.cveSearchSettingsService.postObject(dto);
    }

    @GetMapping(path = "/update-vendors")
    void updateVendors() {
        this.cveSearchSettingsService.updateVendors();
    }


}
