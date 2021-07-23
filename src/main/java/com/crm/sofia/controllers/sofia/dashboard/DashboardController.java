package com.crm.sofia.controllers.sofia.dashboard;

import com.crm.sofia.dto.sofia.dashboard.DashboardDTO;
import com.crm.sofia.services.sofia.dashboard.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping(path = "/by-id")
    DashboardDTO getObject(@RequestParam("id") Long id) {
        return this.dashboardService.getObject(id);
    }

}
