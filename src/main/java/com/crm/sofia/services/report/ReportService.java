package com.crm.sofia.services.report;

import com.crm.sofia.mapper.sofia.report.ReportMapper;
import com.crm.sofia.model.sofia.report.Report;
import com.crm.sofia.model.sofia.report.ReportParameter;
import com.crm.sofia.repository.sofia.report.ReportRepository;
import com.crm.sofia.services.auth.JWTService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
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

    public String getReportType(String id) {
        return this.reportRepository.findType(id);
    }

    @Transactional
    public void print(HttpServletResponse response, String id, Map<String, Object> parameters) throws Throwable {

        Optional<Report> optionalReport = reportRepository.findById(id);
        if (!optionalReport.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report does not exist");
        }
        Report report = optionalReport.get();
        this.writeReportToFileSystemIfNotExists(report);

        Map<String, Object> combinedParameters = combineParameters(id, parameters);

        String folderPathStr = System.getProperty("java.io.tmpdir");
        folderPathStr += report.getReportUuid() + "/";

        String reportFilePathStr = folderPathStr + report.getReportFilename();

        File reportFilePath = new File(reportFilePathStr);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFilePath.getAbsolutePath());

        for (Report subreport : report.getSubreports()) {
            String subreportFilePathStr = folderPathStr + subreport.getReportFilename();
            File subreportFilePath = new File(subreportFilePathStr);
            JasperReport jasperSubReport = JasperCompileManager.compileReport(subreportFilePath.getAbsolutePath());
            JRSaver.saveObject(jasperSubReport, folderPathStr + subreport.getReportFilename().replace(".jrxml", ".jasper"));
            combinedParameters.put(subreport.getReportFilename().replace(".jrxml", ""),
                    folderPathStr + subreport.getReportFilename().replace(".jrxml", ".jasper"));
        }

        Connection connection = dataSource.getConnection();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, combinedParameters, connection);
        JRXlsExporter a;
        String reportType = this.reportRepository.findType(id);
        switch (reportType) {
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

    private Map<String, Object> combineParameters(String id, Map<String, Object> parameters) throws IOException {
        List<ReportParameter> storedParameters = this.reportRepository.getReportParametersById(id);
        storedParameters.forEach(storedParameter -> {
            if (parameters.containsKey(storedParameter.getCode())) {
                storedParameter.setValue(parameters.get(storedParameter.getCode()).toString());
            }
        });

        Map<String, Object> combinedParameters = new HashMap<>();
        combinedParameters.put("userId", this.jwtService.getUserId().toString());
        storedParameters.forEach(storedParameter -> {
            combinedParameters.put(storedParameter.getCode(), storedParameter.getValue());
        });

        return combinedParameters;
    }


    private void writeReportToFileSystemIfNotExists(Report report) throws IOException {

        // Create folder if does not exist
        String folderPathStr = System.getProperty("java.io.tmpdir");
        folderPathStr += report.getReportUuid() + "/";
        File folderPath = new File(folderPathStr);
        if (!folderPath.exists()) {
            folderPath.mkdir();
        }

        // Create report jrxml file if does not exist
        String reportFilePathStr = folderPathStr + report.getReportFilename();
        Path reportFilePath = Paths.get(reportFilePathStr);
        if (!Files.exists(reportFilePath)) {
            byte[] fileData = report.getReportFileData();
            Files.write(reportFilePath, fileData);
        }

        // Create subreports jrxml files if does not exist
        for (Report subreport : report.getSubreports()) {
            String subreportFilePathStr = folderPathStr + subreport.getReportFilename();
            Path subreportFilePath = Paths.get(subreportFilePathStr);
            if (!Files.exists(subreportFilePath)) {
                byte[] fileData = subreport.getReportFileData();
                Files.write(subreportFilePath, fileData);
            }
        }
    }

}
