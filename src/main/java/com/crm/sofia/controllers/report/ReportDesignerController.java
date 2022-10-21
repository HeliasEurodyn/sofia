package com.crm.sofia.controllers.report;

import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.services.report.ReportDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/report-designer")
public class ReportDesignerController {

    private final ReportDesignerService reportDesignerService;

    public ReportDesignerController(ReportDesignerService reportDesignerService) {
        this.reportDesignerService = reportDesignerService;
    }

    @GetMapping
    List<ReportDTO> getObject() {
        return this.reportDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    ReportDTO getObject(@RequestParam("id") String id) {
        return this.reportDesignerService.getObject(id);
    }

    @PostMapping
    public ReportDTO postObject(@RequestBody ReportDTO dto) throws IOException {
        ReportDTO createdDTO = this.reportDesignerService.postObject(dto);
        return createdDTO;
    }

    @PostMapping(value = "/report-file")
    public ReportDTO postObject(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("report") String reportBase64Str) throws IOException {
        ReportDTO createdDTO = this.reportDesignerService.postObject(multipartFile, reportBase64Str);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.reportDesignerService.deleteObject(id);
    }

    @GetMapping(value = "/report-file")
    public void getFileReport(HttpServletResponse response, @RequestParam("id") String id ) throws IOException {
        this.reportDesignerService.getFileReport(response, id);
    }

}
