package com.crm.sofia.services.list;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.*;
import com.crm.sofia.mapper.appview.AppViewMapper;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.model.appview.AppView;
import com.crm.sofia.model.expression.ExprResponce;
import com.crm.sofia.model.jasperTest.JasperModelClass;
import com.crm.sofia.model.list.ListEntity;
import com.crm.sofia.model.persistEntity.PersistEntity;
import com.crm.sofia.repository.list.ListRepository;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final ListMapper listMapper;
    //    private final EntityManager entityManager;
    private final ExpressionService expressionService;
    private final ListDynamicQueryService listDynamicQueryService;
    private final AppViewMapper appViewMapper;

    public ListService(ListRepository listRepository,
                       ListMapper listMapper,
//                       EntityManager entityManager,
                       ExpressionService expressionService,
                       ListDynamicQueryService listDynamicQueryService,
                       AppViewMapper appViewMapper) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
//        this.entityManager = entityManager;
        this.expressionService = expressionService;
        this.listDynamicQueryService = listDynamicQueryService;
        this.appViewMapper = appViewMapper;
    }

    @Transactional
    public ListDTO postObject(ListDTO listDTO) {
        ListEntity view = this.listMapper.map(listDTO);

        ListEntity createdListEntity = this.listRepository.save(view);
        return this.listMapper.map(createdListEntity);
    }

    @Transactional
    public ListDTO putObject(ListDTO listDTO) {
        ListEntity view = this.listMapper.map(listDTO);

        ListEntity createdListEntity = this.listRepository.save(view);
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
        for (ListComponentDTO dto : listDTO.getListComponentList()) {
            List<ComponentPersistEntityDTO> sorted = dto.getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
            dto.getComponent().setComponentPersistEntityList(sorted);
        }

        return listDTO;
    }

    public ListDTO getObjectData(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        ListEntity listEntity = optionalListEntity.get();
     //   PersistEntity persistEntity = (PersistEntity) listEntity.getListComponentList().get(0).getComponent().getComponentPersistEntityList().get(0).getPersistEntity();
        ListDTO listDTO = this.listMapper.map(listEntity);
        for (ListComponentDTO dto : listDTO.getListComponentList()) {
            List<ComponentPersistEntityDTO> sorted = dto.getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
            dto.getComponent().setComponentPersistEntityList(sorted);
        }

        for (ListComponentDTO dto : listDTO.getListComponentList()) {
            for (ListComponentFieldDTO filterDto : dto.getListComponentFilterFieldList()) {

                if (filterDto.getDefaultValue() == null) continue;
                if (filterDto.getDefaultValue().equals("")) continue;

                ExprResponce exprResponce = expressionService.create(filterDto.getDefaultValue());
                if (!exprResponce.getError()) {
                    Object fieldValue = exprResponce.getExprUnit().getResult();
                    filterDto.setFieldValue(fieldValue);
                }
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

        if (dto.getListComponentList().get(0).getHasPagination()) {

            // Current Page
            Long currentPage = dto.getListComponentList().get(0).getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            listResultsDataDTO.setCurrentPage(currentPage);

            // Get Total Rows
            Long totalRows = this.listDynamicQueryService.executeListAndCountTotalRows(dto);
            listResultsDataDTO.setTotalRows(totalRows);

            // Page Size & Total Pages
            Long pageSize = dto.getListComponentList().get(0).getPageSize();
            Long totalPages = totalRows / pageSize;
            Long pageSizeOffset = totalRows % pageSize;
            if (pageSizeOffset > 0) totalPages += 1;
            listResultsDataDTO.setPageSize(pageSize);
            listResultsDataDTO.setTotalPages(totalPages);

        }

        return listResultsDataDTO;
    }

    public List<GroupEntryDTO> getObjectLeftGroupingData(ListDTO dto) {
        List<GroupEntryDTO> groupContent = this.listDynamicQueryService.executeListAndGetGroupingData(dto);
        return groupContent;
    }

//    public List<GroupEntryDTO> generateGroupContent(ListDTO dto) {
//
//
//        if (dto.getListComponentList().get(0).getListComponentLeftGroupFieldList().size() == 0) {
//            return null;
//        }
//
//        /*
//         * Iterate to Generate SELECT Columns part
//         */
//        String queryString = "SELECT ";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentList().get(0).getListComponentLeftGroupFieldList()) {
//            String field =
//                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
//                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + listComponentFieldDTO.getCode();
//            if (firstItteration) {
//                queryString += field;
//            } else {
//                queryString += "," + field;
//
//            }
//
//            firstItteration = false;
//        }
//
////        queryString += countString;
//        queryString += ", count(*) AS total";
//
//        /*
//         * Iterate to Generate FROM Tables & Relashionships part
//         */
//        queryString += " FROM";
//        List<ComponentPersistEntityDTO> sortedComponentPersistEntityList = dto.getListComponentList().get(0).getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
//
//        for (ComponentPersistEntityDTO componentPersistEntityDTO : sortedComponentPersistEntityList) {
//
//            if (componentPersistEntityDTO.getSelector() != null) {
//                String selector = componentPersistEntityDTO.getSelector();
//                String join = selector.replaceAll("\\[|\\].+", "");
//                queryString += " " + join;
//            }
//
//            queryString += " " +
//                    componentPersistEntityDTO.getPersistEntity().getName() + " " + componentPersistEntityDTO.getCode();
//
//            if (componentPersistEntityDTO.getSelector() != null) {
//                String selector = componentPersistEntityDTO.getSelector();
//                String onStatement = selector.replaceAll("\\[.+\\]", "");
//                queryString += " ON " + onStatement;
//
//            }
//
//        }
//
//        /*
//         * Iterate to Generate WHERE Fields part
//         */
//        String filterString = "";
//        firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentList().get(0).getListComponentFilterFieldList()) {
//
//            if (listComponentFieldDTO.getFieldValue() == null) listComponentFieldDTO.setFieldValue("");
//            if (listComponentFieldDTO.getFieldValue().equals("")) {
//                if (listComponentFieldDTO.getRequired()) {
//                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter " + listComponentFieldDTO.getCode() + " should not be empty!");
//                }
//                if (dto.getListComponentList().get(0).getCustomFilterFieldStructure()) {
//                    filterString.replaceAll("$" + listComponentFieldDTO.getCode(), " 1=1 ");
//                }
//                continue;
//            }
//
//            String field =
//                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
//                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
//                            listComponentFieldDTO.getOperator() + " ";
//
//            if (listComponentFieldDTO.getType().equals("datetime")) {
//                Instant valueInstant = Instant.parse(listComponentFieldDTO.getFieldValue().toString());
//                field += " '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant) + "' ";
//            } else if (listComponentFieldDTO.getType().equals("varchar") || listComponentFieldDTO.getType().equals("text")) {
//                if (listComponentFieldDTO.getOperator().equals("like")) {
//                    field += " '" + listComponentFieldDTO.getFieldValue().toString().replaceAll("\\*+", "%") + "' ";
//                } else {
//                    field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
//                }
//            } else {
//                field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
//            }
//
//
//            if (dto.getListComponentList().get(0).getCustomFilterFieldStructure()) {
//
//                if (firstItteration)
//                    filterString = " WHERE " + dto.getListComponentList().get(0).getFilterFieldStructure();
//                filterString.replaceAll("$" + listComponentFieldDTO.getCode(), field);
//
//            } else {
//                if (firstItteration) {
//                    filterString += " WHERE " + field;
//                } else {
//                    filterString += " AND " + field;
//                }
//            }
//
//            firstItteration = false;
//        }
//        queryString += filterString;
//
//        /*
//         * Iterate to Generate Group By Columns part
//         */
//        queryString += "GROUP BY ";
//        firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentList().get(0).getListComponentLeftGroupFieldList()) {
//
//            if (firstItteration) {
//                queryString += listComponentFieldDTO.getCode();
//            } else {
//                queryString += "," + listComponentFieldDTO.getCode();
//            }
//
//            firstItteration = false;
//        }
//
//
//        /*
//         * Iterate to Generate Order By part
//         */
//        queryString += " ORDER BY ";
//        firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentList().get(0).getListComponentLeftGroupFieldList()) {
//
//            if (firstItteration) {
//                queryString += listComponentFieldDTO.getCode() + " ASC ";
//            } else {
//                queryString += "," + listComponentFieldDTO.getCode() + " ASC ";
//            }
//
//            firstItteration = false;
//        }
//
//
//        /*
//         * Execute algorithm
//         * Set results in a HashMap Array
//         * return HashMap Array results
//         */
//        Query query = entityManager.createNativeQuery(queryString);
//        List<Object[]> dataList = query.getResultList();
//
//        int countIndex = dto.getListComponentList().get(0).getListComponentLeftGroupFieldList().size();
//        List<GroupEntryDTO> groupEntries = new ArrayList<>();
//        int i = 0;
//
//        for (Object[] dataRow : dataList) {
//
//            List<GroupEntryDTO> currentGroupEntries = groupEntries;
//
//            for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentList().get(0).getListComponentLeftGroupFieldList()) {
//
//                String currentValue = dataRow[i].toString();
//                GroupEntryDTO entry = currentGroupEntries.stream()
//                        .filter(storedEntry -> currentValue.equals(storedEntry.getValue()))
//                        .findAny()
//                        .orElse(null);
//
//                if (entry == null) {
//                    entry = new GroupEntryDTO();
//                    entry.setCode(listComponentFieldDTO.getCode());
//                    entry.setValue(dataRow[i]);
//                    entry.setCount(Integer.parseInt(dataRow[countIndex].toString()));
//                    entry.setChildren(new ArrayList<>());
//                    currentGroupEntries.add(entry);
//                }
//
//                currentGroupEntries = entry.getChildren();
//                i++;
//            }
//            i = 0;
//        }
//
//
//        return groupEntries;
//    }

//    /*
//     * Iterate to Generate SELECT Columns part
//     */
//    private String generateSelectColumnsPart(ListComponentDTO listComponentDTO) {
//
//        String queryString = "SELECT ";
//        Boolean firstItteration = true;
//
//
//        List<ListComponentFieldDTO> fieldColumns = listComponentDTO.getListComponentColumnFieldList()
//                .stream().filter(
//                        fieldColumn -> fieldColumn.getComponentPersistEntityField() != null).collect(Collectors.toList());
//
//
//        for (ListComponentFieldDTO listComponentFieldDTO : fieldColumns) {
//            String field =
//                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
//                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + listComponentFieldDTO.getCode();
//            if (firstItteration) {
//                queryString += field;
//            } else {
//                queryString += "," + field;
//            }
//            firstItteration = false;
//        }
//
//        Pattern pattern = Pattern.compile("^SqlField\\('.+'\\)$");
//        List<ListComponentFieldDTO> sqlFieldColumns = listComponentDTO.getListComponentColumnFieldList()
//                .stream().filter(
//                        fieldColumn -> pattern.matcher(fieldColumn.getEditor()).matches()).collect(Collectors.toList());
//
//        for (ListComponentFieldDTO listComponentFieldDTO : sqlFieldColumns) {
//            String field = "( " +
//                    listComponentFieldDTO.getEditor().substring(10, listComponentFieldDTO.getEditor().length() - 2) +
//                    ") as " + listComponentFieldDTO.getCode();
//            if (firstItteration) {
//                queryString += field;
//            } else {
//                queryString += "," + field;
//            }
//            firstItteration = false;
//        }
//
//        return queryString;
//
//    }
//
//    /*
//     * Iterate to Generate FROM Tables & Relashionships part
//     */
//    private String generateFromColumnsPart(ListComponentDTO listComponentDTO) {
//
//        String queryString = " FROM";
//
//        List<ComponentPersistEntityDTO> sortedComponentPersistEntityList = listComponentDTO.getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
//
//        for (ComponentPersistEntityDTO componentPersistEntityDTO : sortedComponentPersistEntityList) {
//
//            if (componentPersistEntityDTO.getSelector() != null) {
//                String selector = componentPersistEntityDTO.getSelector();
//                String join = selector.replaceAll("\\[|\\].+", "");
//                queryString += " " + join;
//            }
//
//            queryString += " " +
//                    componentPersistEntityDTO.getPersistEntity().getName() + " " + componentPersistEntityDTO.getCode();
//
//            if (componentPersistEntityDTO.getSelector() != null) {
//                String selector = componentPersistEntityDTO.getSelector();
//                String onStatement = selector.replaceAll("\\[.+\\]", "");
//                queryString += " ON " + onStatement;
//
//            }
//
//        }
//
//        return queryString;
//    }
//
//    /*
//     * Iterate to Generate WHERE Fields part
//     */
//    private String generateWhereColumnsPart(ListComponentDTO listComponentDTO) {
//
//        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {
//            listComponentFieldDTO.setOperator("=");
//            listComponentFieldDTO.setRequired(false);
//        }
//
//        List<ListComponentFieldDTO> filtersList = Stream.concat(listComponentDTO.getListComponentFilterFieldList().stream(),
//                listComponentDTO.getListComponentLeftGroupFieldList().stream())
//                .collect(Collectors.toList());
//
//        String queryString = "";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {
//
//            if (listComponentFieldDTO.getFieldValue() == null) listComponentFieldDTO.setFieldValue("");
//            if (listComponentFieldDTO.getFieldValue().equals("")) {
//                if (listComponentFieldDTO.getRequired()) {
//                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter " + listComponentFieldDTO.getCode() + " should not be empty!");
//                }
//            }
//
//            String field =
//                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
//                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
//                            listComponentFieldDTO.getOperator() + " ";
//
//            if (listComponentFieldDTO.getType().equals("datetime")) {
//                Instant valueInstant = Instant.parse(listComponentFieldDTO.getFieldValue().toString());
//                field += " '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant) + "' ";
//            } else if (listComponentFieldDTO.getType().equals("varchar") || listComponentFieldDTO.getType().equals("text")) {
//                if (listComponentFieldDTO.getOperator().equals("like")) {
//                    field += " '" + listComponentFieldDTO.getFieldValue().toString().replaceAll("\\*+", "%") + "' ";
//                } else {
//                    field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
//                }
//            } else {
//                field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
//            }
//
//
//            if (firstItteration) {
//                queryString += " WHERE " + field;
//            } else {
//                queryString += " AND " + field;
//            }
//
//
//            firstItteration = false;
//        }
//
//        return queryString;
//    }
//
//    /*
//     * Iterate to Generate custom structure WHERE Fields part
//     */
//    private String generateCustomWhereColumnsPart(ListComponentDTO listComponentDTO) {
//
//        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {
//            listComponentFieldDTO.setOperator("=");
//            listComponentFieldDTO.setRequired(false);
//        }
//
//        List<ListComponentFieldDTO> filtersList = Stream.concat(listComponentDTO.getListComponentFilterFieldList().stream(),
//                listComponentDTO.getListComponentLeftGroupFieldList().stream())
//                .collect(Collectors.toList());
//
//        String queryString = " WHERE " + listComponentDTO.getFilterFieldStructure();
//
//        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {
//
//            if (listComponentFieldDTO.getFieldValue() == null) listComponentFieldDTO.setFieldValue("");
//            if (listComponentFieldDTO.getFieldValue().equals("")) {
//                if (listComponentFieldDTO.getRequired()) {
//                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter " + listComponentFieldDTO.getCode() + " should not be empty!");
//                }
//                if (listComponentDTO.getCustomFilterFieldStructure()) {
//                    queryString.replaceAll("$" + listComponentFieldDTO.getCode(), " 1=1 ");
//                }
//                continue;
//            }
//
//            String field =
//                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
//                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
//                            listComponentFieldDTO.getOperator() + " ";
//
//            if (listComponentFieldDTO.getType().equals("datetime")) {
//                Instant valueInstant = Instant.parse(listComponentFieldDTO.getFieldValue().toString());
//                field += " '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant) + "' ";
//            } else if (listComponentFieldDTO.getType().equals("varchar") || listComponentFieldDTO.getType().equals("text")) {
//                if (listComponentFieldDTO.getOperator().equals("like")) {
//                    field += " '" + listComponentFieldDTO.getFieldValue().toString().replaceAll("\\*+", "%") + "' ";
//                } else {
//                    field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
//                }
//            } else {
//                field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
//            }
//
//            queryString.replaceAll("$" + listComponentFieldDTO.getCode(), field);
//
//        }
//
//        return queryString;
//    }
//
//    /*
//     * Iterate to Generate Order By Columns part
//     */
//    private String generateOrderByColumnsPart(ListComponentDTO listComponentDTO) {
//        String queryString = "";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentOrderByFieldList()) {
//
//            if (firstItteration) {
//                queryString += " ORDER BY ";
//            }
//
//            String field =
//                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
//                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName();
//
//            if (listComponentFieldDTO.getEditor().equals("ASC") || listComponentFieldDTO.getEditor().equals("DESC")) {
//                field += " " + listComponentFieldDTO.getEditor();
//            } else {
//                field += " ASC ";
//            }
//
//            if (firstItteration) {
//                queryString += field;
//            } else {
//                queryString += "," + field;
//            }
//
//            firstItteration = false;
//        }
//
//        return queryString;
//    }
//
//    /*
//     * Iterate to Generate Limit part
//     */
//    private String generateLimitPart(ListComponentDTO listComponentDTO) {
//
//        String queryString = "";
//        if (listComponentDTO.getHasPagination()) {
//            Long offset = listComponentDTO.getPageSize() * listComponentDTO.getCurrentPage();
//            queryString += "LIMIT " + listComponentDTO.getPageSize() + " OFFSET  " + offset;
//        } else if (listComponentDTO.getHasMaxSize()) {
//            queryString += "LIMIT " + listComponentDTO.getMaxSize();
//        }
//        return queryString;
//    }
//
//    /*
//     * Execute Sql Query
//     * Set results in a HashMap Array
//     * return HashMap Array results
//     */
//    private List<Map<String, Object>> executeSqlQuery(ListComponentDTO listComponentDTO, String queryString) {
//
//        List<Map<String, Object>> listContent = new ArrayList<>();
//
//        List<ListComponentFieldDTO> fieldColumns = listComponentDTO.getListComponentColumnFieldList()
//                .stream().filter(
//                        fieldColumn -> fieldColumn.getComponentPersistEntityField() != null).collect(Collectors.toList());
//
//        Pattern pattern = Pattern.compile("^SqlField\\('.+'\\)$");
//        List<ListComponentFieldDTO> sqlFieldColumns = listComponentDTO.getListComponentColumnFieldList()
//                .stream().filter(
//                        fieldColumn -> pattern.matcher(fieldColumn.getEditor()).matches()).collect(Collectors.toList());
//
//
//        Query query = entityManager.createNativeQuery(queryString);
//        List<Object[]> dataList = query.getResultList();
//        for (Object[] dataRow : dataList) {
//
//            Map<String, Object> dataMapRow = new HashMap<>();
//            int i = 0;
//
//            for (ListComponentFieldDTO listComponentFieldDTO : fieldColumns) {
//                dataMapRow.put(listComponentFieldDTO.getCode(), dataRow[i]);
//                i++;
//            }
//
//            for (ListComponentFieldDTO listComponentFieldDTO : sqlFieldColumns) {
//                dataMapRow.put(listComponentFieldDTO.getCode(), dataRow[i]);
//                i++;
//            }
//
//            listContent.add(dataMapRow);
//
//        }
//
//        return listContent;
//    }


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
