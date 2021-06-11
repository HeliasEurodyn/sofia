package com.crm.sofia.services.report;

import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.mapper.report.ReportMapper;
import com.crm.sofia.model.report.Report;
import com.crm.sofia.model.report.ReportParameter;
import com.crm.sofia.repository.report.ReportRepository;
import com.crm.sofia.services.auth.JWTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;


@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final JWTService jwtService;
    private final DataSource dataSource;

    public ReportService(ReportRepository reportRepository,
                         ReportMapper reportMapper,
                         JWTService jwtService,
                         DataSource dataSource) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.jwtService = jwtService;
        this.dataSource = dataSource;
    }


    public List<ReportDTO> getObject() {
        List<Report> entites = this.reportRepository.findAll();
        return this.reportMapper.map(entites);
    }

    public ReportDTO getObject(Long id) {
        Optional<Report> optionalEntity = this.reportRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Report entity = optionalEntity.get();
        ReportDTO dto = this.reportMapper.map(entity);
        return dto;
    }

    public String getReportType(Long id) {
        return this.reportRepository.findType(id);
    }

    public ReportDTO postObject(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("report") String reportBase64Str) throws IOException {

        // Get filename & extension from MultipartFile
        String filename = multipartFile.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String reportUuid = UUID.randomUUID().toString();

        // If type != jrxml Throw exception
        if (!extension.equals("jrxml")) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Wrong file type");
        }

        // Get Bytes of File
        byte[] reportFileData = this.getFileBytes(multipartFile);

        // Serialized ReportDTO to Object
        ReportDTO reportDTO = this.base64JsonStringToReportDTO(reportBase64Str);

        // File system updates
        this.writeReportToFileSystem(reportFileData, reportUuid);
        this.deleteReportFromFileSystem(reportDTO.getId());

        // Save
        return this.postObject(filename, extension, reportUuid, reportFileData, reportDTO);
    }


    public ReportDTO postObject(ReportDTO reportDTO) throws IOException {
        if (reportDTO.getId() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File cannot be empty");
        } else {

            Optional<Report> optionalEntity = this.reportRepository.findById(reportDTO.getId());
            if (!optionalEntity.isPresent()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
            }

            // Save
            return this.postObject(
                    optionalEntity.get().getReportFilename(),
                    optionalEntity.get().getReportExtension(),
                    optionalEntity.get().getReportUuid(),
                    optionalEntity.get().getReportFileData(),
                    reportDTO);
        }
    }

    private ReportDTO postObject(
            String filename,
            String extension,
            String reportUuid,
            byte[] reportFileData,
            ReportDTO reportDTO) throws IOException {

        Report report = this.reportMapper.map(reportDTO);
        report.setReportFilename(filename);
        report.setReportExtension(extension);
        report.setReportFileData(reportFileData);
        report.setReportUuid(reportUuid);

        if (report.getId() == null) report.setCreatedOn(Instant.now());
        if (report.getId() == null) report.setCreatedBy(jwtService.getUserId());
        report.setModifiedOn(Instant.now());
        report.setModifiedBy(jwtService.getUserId());

        Report createdReport = this.reportRepository.save(report);
        return this.reportMapper.map(createdReport);
    }

    public void deleteObject(Long id) {
        Optional<Report> optionalEntity = this.reportRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        this.reportRepository.deleteById(optionalEntity.get().getId());
    }

    @Transactional
    public void print(HttpServletResponse response, long id, Map<String, Object> parameters) throws JRException, SQLException, IOException {
        String uuid = this.reportRepository.findUuid(id);
        this.writeReportToFileSystemIfNotExists(id,uuid);
        Map<String, Object> combinedParameters = combineParameters(id,parameters);

        String tmpdir = System.getProperty("java.io.tmpdir");
        tmpdir += uuid + ".jrxml";
        File file = new File(tmpdir);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Connection connection = dataSource.getConnection();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, combinedParameters, connection);
        JRXlsExporter a ;
        String reportType = this.reportRepository.findType(id);
        switch(reportType) {
            case "pdf":
                response.addHeader("Content-disposition", "attachment; filename=file.pdf");
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                break;
            case "docx":
                response.setContentType("application/docx");
                response.setHeader("Content-disposition", "attachment; filename=file.docx");
                ServletOutputStream docxServletOutputStream = response.getOutputStream();
                JRDocxExporter jrDocxExporter = new JRDocxExporter();
                jrDocxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                jrDocxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(docxServletOutputStream));
                jrDocxExporter.exportReport();
                docxServletOutputStream.flush();
                break;
            case "xls":
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment; filename=file.xls");
                ServletOutputStream xlsServletOutputStream = response.getOutputStream();
                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsServletOutputStream));
                exporter.exportReport();
                xlsServletOutputStream.flush();
                break;
            case "xlsx":
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment; filename=file.xls");
                ServletOutputStream xlsxServletOutputStream = response.getOutputStream();
                JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
                jrXlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                jrXlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsxServletOutputStream));
                jrXlsxExporter.exportReport();
                xlsxServletOutputStream.flush();
                break;
            case "csv":
                response.setContentType("application/csv");
                response.setHeader("Content-disposition", "attachment; filename=file.csv");
                ServletOutputStream csvServletOutputStream = response.getOutputStream();
                JRCsvExporter jrCsvExporter = new JRCsvExporter();
                jrCsvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                jrCsvExporter.setExporterOutput(new SimpleWriterExporterOutput(csvServletOutputStream));
                jrCsvExporter.exportReport();
                csvServletOutputStream.flush();
                break;
            case "text":
                response.setContentType("application/csv");
                response.setHeader("Content-disposition", "attachment; filename=file.csv");
                ServletOutputStream textServletOutputStream = response.getOutputStream();
                JRTextExporter jrTextExporter = new JRTextExporter();
                jrTextExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                jrTextExporter.setExporterOutput(new SimpleWriterExporterOutput(textServletOutputStream));
                jrTextExporter.exportReport();
                textServletOutputStream.flush();
                break;
        }

        connection.close();
    }

    private Map<String, Object> combineParameters(Long id, Map<String, Object> parameters) throws IOException {
        List<ReportParameter> storedParameters = this.reportRepository.getReportParametersById(id);
        storedParameters.forEach( storedParameter -> {
            if(parameters.containsKey(storedParameter.getCode())){
                storedParameter.setValue(parameters.get(storedParameter.getCode()).toString());
            }
        });

        Map<String, Object> combinedParameters = new HashMap<>();
        combinedParameters.put("userId", this.jwtService.getUserId().toString());
        storedParameters.forEach( storedParameter -> {
            combinedParameters.put(storedParameter.getCode(),storedParameter.getValue());
        });

        return combinedParameters;
    }

    private void writeReportToFileSystemIfNotExists(Long id, String uuid) throws IOException {
        Boolean exits = checkIfReportExistsInFileSystem(uuid);
        if(!exits){
            byte[] fileData = reportRepository.findReportFileData(id);
            this.writeReportToFileSystem(fileData,uuid);
        }
    }

    private void writeReportToFileSystem(byte[] reportFileData, String uuid) throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        tmpdir += uuid + ".jrxml";
        Path path = Paths.get(tmpdir);
        if (!Files.exists(path)) {
            Files.write(path, reportFileData);
        }
    }

    private void deleteReportFromFileSystem(Long reportId) throws IOException {
        if (reportId == null) {
            return;
        }

        Optional<Report> optionalEntity = this.reportRepository.findById(reportId);
        if (!optionalEntity.isPresent()) {
            return;
        }

        String tmpdir = System.getProperty("java.io.tmpdir");
        tmpdir += optionalEntity.get().getReportUuid() + ".jrxml";

        Path path = Paths.get(tmpdir);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    private Boolean checkIfReportExistsInFileSystem(String uuid) {
        String tmpdir = System.getProperty("java.io.tmpdir");
        tmpdir += uuid + ".jrxml";
        Path path = Paths.get(tmpdir);
        if (!Files.exists(path)) {
            return false;
        }
        return true;
    }

    private ReportDTO base64JsonStringToReportDTO(String reportBase64Str) throws JsonProcessingException {
        byte[] reportBytes = Base64.getDecoder().decode(reportBase64Str);
        ObjectMapper mapper = new ObjectMapper();
        ReportDTO reportDTO = mapper.readValue(new String(reportBytes), ReportDTO.class);
        return reportDTO;
    }

    private byte[] getFileBytes(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        byte[] byteArray = new byte[inputStream.available()];
        inputStream.read(byteArray);

        return byteArray;
    }

//    public ReportDTO postObject(ReportDTO dto) {
//        Report component = this.reportMapper.map(dto);
//        component.setCreatedOn(Instant.now());
//        component.setModifiedOn(Instant.now());
//        component.setCreatedBy(jwtService.getUserId());
//        component.setModifiedBy(jwtService.getUserId());
//
//        Report createdComponent = this.reportRepository.save(component);
//        return this.reportMapper.map(createdComponent);
//    }


//    public void doJasperPdfTestN(HttpServletResponse response) throws JRException, SQLException, IOException {
//        File file = ResourceUtils.getFile("classpath:hrep.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("id", "1");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//    }
//
//    public void doJasperPdfTest() throws FileNotFoundException, JRException {
//
//        List<JasperModelClass> entities = new ArrayList<>();
//        JasperModelClass entity = new JasperModelClass(1, "Helias", "Designation 1", 19000, "hello");
//        entities.add(entity);
//        entity = new JasperModelClass(2, "Nikos", "Designation 2", 19000, "Nick");
//        entities.add(entity);
//        entity = new JasperModelClass(3, "Kostas", "Designation 3", 29000, "Kostas");
//        entities.add(entity);
//
//        File file = ResourceUtils.getFile("classpath:ListDataExport.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(entities);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Helias");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\helias\\Desktop\\cv new\\test.pdf");
//
//    }
//
//    public void doJasperExcelTestExcel() throws FileNotFoundException, JRException {
//
//        List<JasperModelClass> entities = new ArrayList<>();
//        JasperModelClass entity = new JasperModelClass(1, "Helias", "Designation 1", 19000, "hello");
//        entities.add(entity);
//        entity = new JasperModelClass(2, "Nikos", "Designation 2", 19000, "Nick");
//        entities.add(entity);
//        entity = new JasperModelClass(3, "Kostas", "Designation 3", 29000, "Kostas");
//        entities.add(entity);
//
//        File file = ResourceUtils.getFile("classpath:ListDataExport.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(entities);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Helias");
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//
//        JRXlsxExporter exporter = new JRXlsxExporter();
//
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\Users\\helias\\Desktop\\cv new\\testexcel.xlsx"));
//
//        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
//        reportConfig.setSheetNames(new String[]{"Sofia output data"});
//        reportConfig.setRemoveEmptySpaceBetweenRows(true);
//        reportConfig.setWhitePageBackground(false);
//        reportConfig.setDetectCellType(true);
//        reportConfig.setOnePagePerSheet(true);
//        exporter.setConfiguration(reportConfig);
//
//
//        try {
//            exporter.exportReport();
//        } catch (JRException ex) {
////            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//        //JasperExportManager.exportReportToPdfFile(jasperPrint,);
//    }


}
