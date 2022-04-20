package com.crm.sofia.controllers.sofia.html_dashboard;

import com.crm.sofia.dto.sofia.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.services.sofia.html_dashboard.HtmlDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/html-dashboard")
public class HtmlDashboardController {

    private final HtmlDashboardService htmlDashboardService;

    public HtmlDashboardController(HtmlDashboardService htmlDashboardService) {
        this.htmlDashboardService = htmlDashboardService;
    }

    @GetMapping(path = "/by-id")
    HtmlDashboardDTO getObject(@RequestParam("id") String id) {
        return htmlDashboardService.getObject(id);
    }
}
