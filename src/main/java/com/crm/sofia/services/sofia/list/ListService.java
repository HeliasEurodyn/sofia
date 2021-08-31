package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.list.base.GroupEntryDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.dto.sofia.list.base.ListResultsDataDTO;
import com.crm.sofia.dto.sofia.list.user.ListComponentFieldUiDTO;
import com.crm.sofia.dto.sofia.list.user.ListUiDTO;
import com.crm.sofia.mapper.sofia.list.designer.ListMapper;
import com.crm.sofia.mapper.sofia.list.user.ListUiMapper;
import com.crm.sofia.model.sofia.expression.ExprResponce;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.native_repository.sofia.list.ListNativeRepository;
import com.crm.sofia.repository.sofia.list.ListRepository;
import com.crm.sofia.services.sofia.expression.ExpressionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    private final ListUiMapper listUiMapper;
    private final ExpressionService expressionService;
    private final ListNativeRepository listNativeRepository;
    private final ListCacheingService listCacheingService;

    public ListService(ListRepository listRepository,
                       ListMapper listMapper,
                       ListUiMapper listUiMapper,
                       ExpressionService expressionService,
                       ListNativeRepository listNativeRepository,
                       ListCacheingService listCacheingService) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
        this.listUiMapper = listUiMapper;
        this.expressionService = expressionService;
        this.listNativeRepository = listNativeRepository;
        this.listCacheingService = listCacheingService;
    }

    public ListDTO getObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        ListDTO listDTO = this.listMapper.map(optionalListEntity.get());
        listDTO.getComponent().getComponentPersistEntityList().sort(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder));
        listDTO.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));

        return listDTO;
    }

    public List<ListDTO> getObject() {
        List<ListEntity> views = this.listRepository.findAll();
        return this.listMapper.map(views);
    }

    public ListDTO getListObject(Long id) {

        ListDTO listDTO = this.getObject(id);

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

    public ListUiDTO getUiListObject(Long id) {

        /* Try Retrieve Cached */
        if (this.listCacheingService.hasUiObject(id)) {
            return this.listCacheingService.getUiObject(id);
        }

        /* Retrieve */
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }

        /* Map */
        ListUiDTO listUiDTO = this.listUiMapper.map(optionalListEntity.get());

        /* Short */
        listUiDTO.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));

        List<ListComponentFieldUiDTO> filtersList = Stream.concat(listUiDTO.getListComponentFilterFieldList().stream(),
                listUiDTO.getListComponentColumnFieldList().stream())
                .collect(Collectors.toList());

        /* Calc Default Values */
        for (ListComponentFieldUiDTO filterDto : filtersList) {

            if (filterDto.getDefaultValue() == null) continue;
            if (filterDto.getDefaultValue().equals("")) continue;

            ExprResponce exprResponce = expressionService.create(filterDto.getDefaultValue());
            if (!exprResponce.getError()) {
                Object fieldValue = exprResponce.getExprUnit().getResult();
                filterDto.setFieldValue(fieldValue);
            }
        }

        /* Cache */
        this.listCacheingService.putUiObject(id, listUiDTO);

        /* Return */
        return listUiDTO;
    }

    public ListResultsDataDTO getListObject(ListDTO listDTO) {
        ListResultsDataDTO listResultsDataDTO = new ListResultsDataDTO();

        List<Map<String, Object>> listContent = this.listNativeRepository.executeListAndGetData(listDTO);
        listResultsDataDTO.setListContent(listContent);

        if ((listDTO.getHasPagination() == null ? false : listDTO.getHasPagination())) {

            // Current Page
            Long currentPage = listDTO.getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            listResultsDataDTO.setCurrentPage(currentPage);

            // Get Total Rows
            Long totalRows = this.listNativeRepository.executeListAndCountTotalRows(listDTO);
            listResultsDataDTO.setTotalRows(totalRows);

            // Page Size & Total Pages
            Long pageSize = listDTO.getPageSize();
            Long totalPages = totalRows / pageSize;
            Long pageSizeOffset = totalRows % pageSize;
            if (pageSizeOffset > 0) totalPages += 1;
            listResultsDataDTO.setPageSize(pageSize);
            listResultsDataDTO.setTotalPages(totalPages);

        }

        return listResultsDataDTO;
    }

    public ListResultsDataDTO getObjectDataByParameters(Map<String, String> parameters, String jsonUrl) {

        List<Long> ids = this.listRepository.getIdByJsonUrl(jsonUrl);

        if (ids.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Url does not exits");
        }

        ListDTO listDTO = this.getListObject(ids.get(0));
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        ListResultsDataDTO listResultsDataDTO = this.getListObject(listDTO);
        return listResultsDataDTO;
    }

    public ListResultsDataDTO getObjectDataByParameters(Map<String, String> parameters, Long id) {
        ListDTO listDTO = this.getListObject(id);
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        ListResultsDataDTO listResultsDataDTO = this.getListObject(listDTO);
        return listResultsDataDTO;
    }

    private ListDTO mapParametersToListDto(ListDTO listDTO, Map<String, String> parameters) {

        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.addAll(listDTO.getListComponentFilterFieldList());
        filtersList.addAll(listDTO.getListComponentLeftGroupFieldList());
        filtersList.addAll(listDTO.getListComponentColumnFieldList());

        filtersList
                .stream()
                .filter(x -> (x.getEditable() == null ? false : x.getEditable()))
                .filter(x -> (x.getVisible() == null ? false : x.getVisible()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> !x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    x.setFieldValue(fieldValue);
                });

        filtersList
                .stream()
                .filter(x -> (x.getEditable() == null ? false : x.getEditable()))
                .filter(x -> (x.getVisible() == null ? false : x.getVisible()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    Instant fieldValueInstant = LocalDateTime.parse(fieldValue,
                            DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.UK))
                            .atZone(ZoneOffset.UTC)
                            .toInstant();
                    x.setFieldValue(fieldValueInstant);
                });

        return listDTO;
    }

    public List<GroupEntryDTO> getObjectLeftGroupingData(ListDTO dto) {
        List<GroupEntryDTO> groupContent = this.listNativeRepository.executeListAndGetGroupingData(dto);
        return groupContent;
    }

//    public void doJasperPdfTestN(HttpServletResponse response) throws JRException, SQLException, IOException {
//
////        List<JasperModelClass> entities = new ArrayList<>();
////        JasperModelClass entity = new JasperModelClass(1, "Helias", "Designation 1", 19000, "hello");
////        entities.add(entity);
////        entity = new JasperModelClass(2, "Nikos", "Designation 2", 19000, "Nick");
////        entities.add(entity);
////        entity = new JasperModelClass(3, "Kostas", "Designation 3", 29000, "Kostas");
////        entities.add(entity);
//
//        File file = ResourceUtils.getFile("classpath:hrep.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
////        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(entities);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("id", "1");
////        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
//
//        //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\helias\\Desktop\\test1.pdf");
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//
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

    public List<GroupEntryDTO> getObjectLeftGroupingDataByParameters(Map<String, String> parameters, Long id) {
        ListDTO listDTO = this.getListObject(id);

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

    public String getInstanceVersion(Long id) {
        return this.listRepository.getInstanceVersion(id);
    }
}
