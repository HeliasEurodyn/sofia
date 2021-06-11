package com.crm.sofia.controllers.report;

import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.services.report.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
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

    @PostMapping(value = "/fileReport")
    public ReportDTO postObject(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("report") String reportBase64Str) throws IOException {
        ReportDTO createdDTO = this.reportService.postObject(multipartFile, reportBase64Str);
        return createdDTO;
    }

    @PostMapping
    public ReportDTO postObject(@RequestBody ReportDTO dto) throws IOException {
        ReportDTO createdDTO = this.reportService.postObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.reportService.deleteObject(id);
    }

    @PostMapping(path = "/print")
    void print(HttpServletResponse response,@RequestParam("id") Long id, @RequestBody Map<String, Object> parameters) throws IOException, JRException, SQLException {
        this.reportService.print(response, id, parameters);
    }

    @GetMapping(path = "/type")
    String getReportType(@RequestParam("id") Long id) {
        String reportType = this.reportService.getReportType(id);
        return "{\"type\":\""+reportType+"\"}";
    }

//    @GetMapping(path = "/jasper-test-pdf")
//    void doJasperTest() throws FileNotFoundException, JRException {
//        this.reportService.doJasperPdfTest();
//    }
//
//    @GetMapping(path = "/jasper-test-pdf-n")
//    void doJasperTestNew(HttpServletResponse response) throws IOException, JRException, SQLException {
//        this.reportService.doJasperPdfTestN(response);
//    }
//
//    @GetMapping(path = "/jasper-test-excel")
//    void doJasperTestExcel() throws FileNotFoundException, JRException {
//        this.reportService.doJasperExcelTestExcel();
//    }

//    @PostMapping(value = "/ttestt")
//    public void uploadFilesByTag(
//            @RequestParam("file") MultipartFile multipartFile,
//            @RequestParam("report") String reportBase64Str
//    ) throws IOException {
//
//        // 1. File to bytes
//        InputStream initialStream = multipartFile.getInputStream();
//        byte[] targetArray = new byte[initialStream.available()];
//        initialStream.read(targetArray);
//
//        // 2. Get multipart filename & extension
//        String filename = multipartFile.getOriginalFilename();
//        String extension  = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
//
//        // 3. Temp folder
//        String tmpdir = System.getProperty("java.io.tmpdir");
//        tmpdir += filename;
//
//        // 4. Save file to temp path
//        Path path = Paths.get(tmpdir);
//        Files.write(path, targetArray);
//
//        // 5. Decode Base64
//        byte[] reportBytes = Base64.getDecoder().decode(reportBase64Str);
//
//        // 6. report bytes json Map to Object
//        ObjectMapper mapper = new ObjectMapper();
//        ReportDTO reportDTO = mapper.readValue(new String(reportBytes), ReportDTO.class);
//    }

}
