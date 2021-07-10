package com.crm.sofia.controllers.dashboard;

import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.services.dashboard.DashboardDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/dashboard-designer")
public class DashboardDesignerController {

    private final DashboardDesignerService dashboardDesignerService;

    public DashboardDesignerController(DashboardDesignerService dashboardDesignerService) {
        this.dashboardDesignerService = dashboardDesignerService;
    }

    @GetMapping
    List<DashboardDTO> getObject() {
        return this.dashboardDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    DashboardDTO getObject(@RequestParam("id") Long id) {
        return this.dashboardDesignerService.getObject(id);
    }

    @PostMapping
    public DashboardDTO postObject(@RequestBody DashboardDTO dto) {
        return this.dashboardDesignerService.postObject(dto);
    }

    @PutMapping
    public DashboardDTO putObject(@RequestBody DashboardDTO dto) {
        return this.dashboardDesignerService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.dashboardDesignerService.deleteObject(id);
    }

}
