package com.crm.sofia.controllers.html_dashboard;

import com.crm.sofia.dto.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.services.html_dashboard.HtmlDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/html-dashboard")
public class HtmlDashboardController {

    @Autowired
    private HtmlDashboardService htmlDashboardService;
    @Autowired
    public HtmlDashboardController(HtmlDashboardService htmlDashboardService) {
        this.htmlDashboardService = htmlDashboardService;
    }

    @GetMapping(path = "/by-id")
    HtmlDashboardDTO getObject(@RequestParam("id") String id) {
        return htmlDashboardService.getObject(id);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/min/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getMinJavaScript(@PathVariable("id") String id) {
        return this.htmlDashboardService.getMinJavaScript(id);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getJavaScript(@PathVariable("id") String id) {
        return this.htmlDashboardService.getJavaScript(id);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-javascripts/factory.js", method = RequestMethod.GET, produces = "text/javascript;")
    public String getFormJavaScripty() {
        return this.htmlDashboardService.getJavaScriptFactory();
    }

}
