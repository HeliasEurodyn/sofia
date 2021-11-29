package com.crm.sofia.controllers.sofia.report;

import com.crm.sofia.dto.sofia.report.ReportDTO;
import com.crm.sofia.services.sofia.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

    @GetMapping
    List<ReportDTO> getObject() {
        return this.reportService.getObject();
    }

    @GetMapping(path = "/by-id")
    ReportDTO getObject(@RequestParam("id") Long id) {
        return this.reportService.getObject(id);
    }

    @PostMapping
    public ReportDTO postObject(@RequestBody ReportDTO dto) throws IOException {
        ReportDTO createdDTO = this.reportService.postObject(dto);
        return createdDTO;
    }

    @PostMapping(value = "/report-file")
    public ReportDTO postObject(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("report") String reportBase64Str) throws IOException {
        ReportDTO createdDTO = this.reportService.postObject(multipartFile, reportBase64Str);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.reportService.deleteObject(id);
    }

    @PostMapping(path = "/print")
    void print(HttpServletResponse response,@RequestParam("id") Long id, @RequestBody Map<String, Object> parameters) throws Throwable {
        this.reportService.print(response, id, parameters);
    }

    @GetMapping(value = "/report-file")
    public void getFileReport(HttpServletResponse response, @RequestParam("id") Long id ) throws IOException {
        this.reportService.getFileReport(response, id);
    }

    @GetMapping(path = "/type")
    String getReportType(@RequestParam("id") Long id) {
        String reportType = this.reportService.getReportType(id);
        return "{\"type\":\""+reportType+"\"}";
    }

}
