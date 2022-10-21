package com.crm.sofia.controllers.html_dashboard;

import com.crm.sofia.dto.sofia.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.services.html_dashboard.HtmlDashboardDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/html-dashboard-designer")
public class HtmlDashboardDesignerController {

    private final HtmlDashboardDesignerService htmlDashboardDesignerService;

    public HtmlDashboardDesignerController(HtmlDashboardDesignerService htmlDashboardDesignerService) {
        this.htmlDashboardDesignerService = htmlDashboardDesignerService;
    }

    @GetMapping
    List<HtmlDashboardDTO> getObject() {
        return htmlDashboardDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    HtmlDashboardDTO getObject(@RequestParam("id") String id) {
        return htmlDashboardDesignerService.getObject(id);
    }

    @PostMapping
    public HtmlDashboardDTO postObject(@RequestBody HtmlDashboardDTO htmlDashboardDto) throws IOException {
        return htmlDashboardDesignerService.postObject(htmlDashboardDto);
    }

    @PutMapping
    public HtmlDashboardDTO putObject(@RequestBody HtmlDashboardDTO htmlDashboardDto) {
        return htmlDashboardDesignerService.postObject(htmlDashboardDto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        htmlDashboardDesignerService.deleteObject(id);
    }

}
