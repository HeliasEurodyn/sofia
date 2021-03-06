package com.crm.sofia.services.list;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.model.expression.ExprResponce;
import com.crm.sofia.model.jasperTest.JasperModelClass;
import com.crm.sofia.model.list.ListEntity;
import com.crm.sofia.repository.list.ListRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.expression.ExpressionService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final ListMapper listMapper;
    private final ExpressionService expressionService;
    private final ListDynamicQueryService listDynamicQueryService;
    private final JWTService jwtService;

    public ListService(ListRepository listRepository,
                       ListMapper listMapper,
                       ExpressionService expressionService,
                       ListDynamicQueryService listDynamicQueryService,
                       JWTService jwtService) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
        this.expressionService = expressionService;
        this.listDynamicQueryService = listDynamicQueryService;
        this.jwtService = jwtService;
    }

    @Transactional
    public ListDTO postObject(ListDTO listDTO) {
        ListEntity listEntity = this.listMapper.map(listDTO);
        listEntity.setCreatedOn(Instant.now());
        listEntity.setModifiedOn(Instant.now());
        listEntity.setCreatedBy(jwtService.getUserId());
        listEntity.setModifiedBy(jwtService.getUserId());
        ListEntity createdListEntity = this.listRepository.save(listEntity);
        return this.listMapper.map(createdListEntity);
    }

    @Transactional
    public ListDTO putObject(ListDTO listDTO) {
        ListEntity listEntity = this.listMapper.map(listDTO);
        listEntity.setModifiedOn(Instant.now());
        listEntity.setModifiedBy(jwtService.getUserId());
        ListEntity createdListEntity = this.listRepository.save(listEntity);
        return this.listMapper.map(createdListEntity);
    }

    public List<ListDTO> getObject() {
        List<ListEntity> views = this.listRepository.findAll();
        return this.listMapper.map(views);
    }

    public ListDTO getObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        ListDTO listDTO = this.listMapper.map(optionalListEntity.get());
        List<ComponentPersistEntityDTO> sorted = listDTO.getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
        listDTO.getComponent().setComponentPersistEntityList(sorted);

        return listDTO;
    }

    public ListDTO getObjectData(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        ListEntity listEntity = optionalListEntity.get();
        ListDTO listDTO = this.listMapper.map(listEntity);

        List<ComponentPersistEntityDTO> sorted = listDTO.getComponent().getComponentPersistEntityList()
                .stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder))
                .collect(Collectors.toList());

        listDTO.getComponent().setComponentPersistEntityList(sorted);

        List<ListComponentFieldDTO> filtersList = Stream.concat(listDTO.getListComponentFilterFieldList().stream(),
                listDTO.getListComponentColumnFieldList().stream())
                .collect(Collectors.toList());

        for (ListComponentFieldDTO filterDto : filtersList) {

            if (filterDto.getDefaultValue() == null) continue;
            if (filterDto.getDefaultValue().equals("")) continue;

            ExprResponce exprResponce = expressionService.create(filterDto.getDefaultValue());
            if (!exprResponce.getError()) {
                Object fieldValue = exprResponce.getExprUnit().getResult();
                filterDto.setFieldValue(fieldValue);
            }
        }

        return listDTO;
    }

    public ListDTO getObjectByName(String name) {
        ListEntity listEntity = this.listRepository.findFirstByName(name);
        if (listEntity == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        return this.listMapper.map(listEntity);
    }

    public void deleteObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        this.listRepository.deleteById(optionalListEntity.get().getId());
    }

    public ListResultsDataDTO getObjectData(ListDTO dto) {
        ListResultsDataDTO listResultsDataDTO = new ListResultsDataDTO();

        List<Map<String, Object>> listContent = this.listDynamicQueryService.executeListAndGetData(dto);
        listResultsDataDTO.setListContent(listContent);

        if (dto.getHasPagination()) {

            // Current Page
            Long currentPage = dto.getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            listResultsDataDTO.setCurrentPage(currentPage);

            // Get Total Rows
            Long totalRows = this.listDynamicQueryService.executeListAndCountTotalRows(dto);
            listResultsDataDTO.setTotalRows(totalRows);

            // Page Size & Total Pages
            Long pageSize = dto.getPageSize();
            Long totalPages = totalRows / pageSize;
            Long pageSizeOffset = totalRows % pageSize;
            if (pageSizeOffset > 0) totalPages += 1;
            listResultsDataDTO.setPageSize(pageSize);
            listResultsDataDTO.setTotalPages(totalPages);

        }

        return listResultsDataDTO;
    }

    public ListResultsDataDTO getObjectDataByParameters(Map<String, String> parameters, Long id) {
        ListDTO listDTO = this.getObjectData(id);

        List<ListComponentFieldDTO> filtersList = Stream.concat(listDTO.getListComponentFilterFieldList().stream(),
                listDTO.getListComponentLeftGroupFieldList().stream())
                .collect(Collectors.toList());

         filtersList = Stream.concat(filtersList.stream(),
                 listDTO.getListComponentColumnFieldList().stream())
                .collect(Collectors.toList());

        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {
            if (listComponentFieldDTO.getEditable() && listComponentFieldDTO.getVisible()) {
                if (parameters.containsKey(listComponentFieldDTO.getCode())) {
                    String fieldValue = parameters.get(listComponentFieldDTO.getCode());

                    if (listComponentFieldDTO.getType().equals("datetime")) {
                        Instant fieldValueInstant = LocalDateTime.parse(fieldValue,
                                DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.UK))
                                .atZone(ZoneOffset.UTC)
                                .toInstant();
                        listComponentFieldDTO.setFieldValue(fieldValueInstant);
                    } else {
                        listComponentFieldDTO.setFieldValue(fieldValue);
                    }
                }
            }
        }

        return this.getObjectData(listDTO);
    }

    public List<GroupEntryDTO> getObjectLeftGroupingData(ListDTO dto) {
        List<GroupEntryDTO> groupContent = this.listDynamicQueryService.executeListAndGetGroupingData(dto);
        return groupContent;
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

    public List<GroupEntryDTO> getObjectLeftGroupingDataByParameters(Map<String, String> parameters, Long id) {
        ListDTO listDTO = this.getObjectData(id);

        List<ListComponentFieldDTO> filtersList = Stream.concat(listDTO.getListComponentFilterFieldList().stream(),
                listDTO.getListComponentLeftGroupFieldList().stream())
                .collect(Collectors.toList());

        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {
            if (listComponentFieldDTO.getEditable() && listComponentFieldDTO.getVisible()) {
                if (parameters.containsKey(listComponentFieldDTO.getCode())) {
                    String fieldValue = parameters.get(listComponentFieldDTO.getCode());


                    if (listComponentFieldDTO.getType().equals("datetime")) {
                        Instant fieldValueInstant = LocalDateTime.parse(fieldValue,
                                DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.UK))
                                .atZone(ZoneOffset.UTC)
                                .toInstant();
                        listComponentFieldDTO.setFieldValue(fieldValueInstant);
                    } else {
                        listComponentFieldDTO.setFieldValue(fieldValue);
                    }

                }
            }
        }

        return this.getObjectLeftGroupingData(listDTO);
    }


//    public ByteArrayInputStream doPoiTestExcel() throws IOException {
//        List<TestCustomer> testCustomers = new ArrayList<>();
//        TestCustomer testCustomer = new TestCustomer(1L,"Ilias","25hs martiou", 35);
//        testCustomers.add(testCustomer);
//        testCustomer = new TestCustomer(2L,"Kostas","Stavroupoleos 49", 45);
//        testCustomers.add(testCustomer);
//
//        ByteArrayInputStream in =   ExcelGenerator.customersToExcel(testCustomers);
//        return in;
//
//    }


//    public Resource exportXls() throws IOException {
//        StringBuilder filename = new StringBuilder("Foo Export").append(" - ")
//                .append("Test 1.xlsx");
//        return export(filename);
//    }
//
//    public ByteArrayResource export(String filename, HttpServletResponse response) throws IOException {
//        ServletOutputStream sfos = response.getOutputStream();
//
//
//
//        byte[] bytes = new byte[1024];
//        try (Workbook workbook = generateExcel()) {
//            FileOutputStream fos = new FileOutputStream(sfos);
////            FileOutputStream fos = write(workbook, filename);
//            sfos.write(workbook);
//            fos.write(bytes);
//            fos.flush();
//            fos.close();
//        }
//
//        return new ByteArrayResource(bytes);
//    }

//    private Workbook generateExcel() {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet();
//
//        //create columns and rows
//
//        return workbook;
//    }
//
//    private FileOutputStream write(final Workbook workbook, final String filename) throws IOException {
//        FileOutputStream fos = new FileOutputStream(filename);
//        workbook.write(fos);
//        fos.close();
//        return fos;
//    }


}
