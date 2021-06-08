package com.crm.sofia.services.report;

import com.crm.sofia.dto.report.ReportDTO;
import com.crm.sofia.mapper.report.ReportMapper;
import com.crm.sofia.model.jasperTest.JasperModelClass;
import com.crm.sofia.model.report.Report;
import com.crm.sofia.repository.report.ReportRepository;
import com.crm.sofia.services.auth.JWTService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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


    public ReportDTO postObject(ReportDTO dto) {
        Report component = this.reportMapper.map(dto);
        component.setCreatedOn(Instant.now());
        component.setModifiedOn(Instant.now());
        component.setCreatedBy(jwtService.getUserId());
        component.setModifiedBy(jwtService.getUserId());

        Report createdComponent = this.reportRepository.save(component);
        return this.reportMapper.map(createdComponent);
    }

    public void deleteObject(Long id) {
        Optional<Report> optionalEntity = this.reportRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        this.reportRepository.deleteById(optionalEntity.get().getId());
    }

    public void doJasperPdfTestN(HttpServletResponse response) throws JRException, SQLException, IOException {
        File file = ResourceUtils.getFile("classpath:hrep.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", "1");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    public void doJasperPdfTest() throws FileNotFoundException, JRException {

        List<JasperModelClass> entities = new ArrayList<>();
        JasperModelClass entity = new JasperModelClass(1, "Helias", "Designation 1", 19000, "hello");
        entities.add(entity);
        entity = new JasperModelClass(2, "Nikos", "Designation 2", 19000, "Nick");
        entities.add(entity);
        entity = new JasperModelClass(3, "Kostas", "Designation 3", 29000, "Kostas");
        entities.add(entity);

        File file = ResourceUtils.getFile("classpath:ListDataExport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(entities);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Helias");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\helias\\Desktop\\cv new\\test.pdf");

    }

    public void doJasperExcelTestExcel() throws FileNotFoundException, JRException {

        List<JasperModelClass> entities = new ArrayList<>();
        JasperModelClass entity = new JasperModelClass(1, "Helias", "Designation 1", 19000, "hello");
        entities.add(entity);
        entity = new JasperModelClass(2, "Nikos", "Designation 2", 19000, "Nick");
        entities.add(entity);
        entity = new JasperModelClass(3, "Kostas", "Designation 3", 29000, "Kostas");
        entities.add(entity);

        File file = ResourceUtils.getFile("classpath:ListDataExport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(entities);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Helias");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\Users\\helias\\Desktop\\cv new\\testexcel.xlsx"));

        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setSheetNames(new String[]{"Sofia output data"});
        reportConfig.setRemoveEmptySpaceBetweenRows(true);
        reportConfig.setWhitePageBackground(false);
        reportConfig.setDetectCellType(true);
        reportConfig.setOnePagePerSheet(true);
        exporter.setConfiguration(reportConfig);


        try {
            exporter.exportReport();
        } catch (JRException ex) {
//            Logger.getLogger(SimpleReportFiller.class.getName()).log(Level.SEVERE, null, ex);
        }


        //JasperExportManager.exportReportToPdfFile(jasperPrint,);
    }



}
