package com.crm.sofia.controllers.report;

import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.services.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    public ReportDTO postObject(@RequestBody ReportDTO dto) {
        ReportDTO createdDTO = this.reportService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public ReportDTO putObject(@RequestBody ReportDTO dto) {
        ReportDTO createdDTO = this.reportService.postObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.reportService.deleteObject(id);
    }


    @GetMapping(path = "/jasper-test-pdf")
    void doJasperTest() throws FileNotFoundException, JRException {
        this.reportService.doJasperPdfTest();
    }

    @GetMapping(path = "/jasper-test-pdf-n")
    void doJasperTestNew(HttpServletResponse response) throws IOException, JRException, SQLException {
        this.reportService.doJasperPdfTestN(response);
    }

    @GetMapping(path = "/jasper-test-excel")
    void doJasperTestExcel() throws FileNotFoundException, JRException {
        this.reportService.doJasperExcelTestExcel();
    }


    @PostMapping(value = "/ttestt")
    public void uploadFilesByTag(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("name") String name
    ) throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        System.out.println("Temp file path: " + tmpdir);
        String a = "df";
        String b = a;

//        byte[] data = multipartFile.getInputStream().readAllBytes();
   //     File file = new File(getClass().getResource("text.txt").getFile());
   //     multipartFile.transferTo(file);
    }

}
