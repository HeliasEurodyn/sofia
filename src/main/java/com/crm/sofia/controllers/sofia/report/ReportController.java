package com.crm.sofia.controllers.sofia.report;

import com.crm.sofia.services.sofia.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(path = "/print")
    void print(HttpServletResponse response,@RequestParam("id") Long id, @RequestBody Map<String, Object> parameters) throws Throwable {
        this.reportService.print(response, id, parameters);
    }

    @GetMapping(path = "/type")
    String getReportType(@RequestParam("id") Long id) {
        String reportType = this.reportService.getReportType(id);
        return "{\"type\":\""+reportType+"\"}";
    }

}
