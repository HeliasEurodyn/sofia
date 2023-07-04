package com.crm.sofia.services.list;

import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.model.expression.ExprResponse;
import com.crm.sofia.model.list.ListEntity;
import com.crm.sofia.native_repository.list.ListRetrieverNativeRepository;
import com.crm.sofia.native_repository.list.ListUpdaterNativeRepository;
import com.crm.sofia.repository.list.ListRepository;
import com.crm.sofia.services.expression.ExpressionService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private  ExpressionService expressionService;
    @Autowired
    private ListRetrieverNativeRepository listRetrieverNativeRepository;
    @Autowired
    private ListUpdaterNativeRepository listUpdaterNativeRepository;

//    public ListService(ListRepository listRepository,
//                       ListMapper listMapper,
//                       ExpressionService expressionService,
//                       ListRetrieverNativeRepository listRetrieverNativeRepository,
//                       ListUpdaterNativeRepository listUpdaterNativeRepository) {
//        this.listRepository = listRepository;
//        this.listMapper = listMapper;
//        this.expressionService = expressionService;
//        this.listRetrieverNativeRepository = listRetrieverNativeRepository;
//        this.listUpdaterNativeRepository = listUpdaterNativeRepository;
//    }

    public ListDTO getObject(String id, String languageId) {

        /* Retrieve */
        ListEntity listEntity = this.listRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist"));

        ListDTO listDTO = this.listMapper.mapList(listEntity, languageId);

        return listDTO;
    }

    public ListDTO calcDefaultsOnListDTO(ListDTO listDTO) {

        listDTO.getListComponentFilterFieldList()
                .stream()
                .filter(x -> x.getDefaultValue() != null)
                .filter(x -> !x.getDefaultValue().equals(""))
                .filter(x -> !(x.getEditable() != null && x.getEditable()))
                .forEach(x -> {
                 //   ExprResponse exprResponse = expressionService.create(x.getDefaultValue());
                    ExprResponse exprResponse = expressionService.createCacheable(x.getDefaultValue(), x.getId());
                    if (!exprResponse.getError()) {
                        Object fieldValue = expressionService.getResult(exprResponse);
                        x.setFieldValue(fieldValue);
                    }
                });

        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> x.getDefaultValue() != null)
                .filter(x -> !x.getDefaultValue().equals(""))
                .filter(x -> !(x.getEditable() != null && x.getEditable()))
                .forEach(x -> {
                    ExprResponse exprResponse = expressionService.createCacheable(x.getDefaultValue(), x.getId());
//                    ExprResponse exprResponse = expressionService.create(x.getDefaultValue());
                    if (!exprResponse.getError()) {
                        Object fieldValue = expressionService.getResult(exprResponse);
                        x.setFieldValue(fieldValue);
                    }
                });

        return listDTO;
    }

//    public ListDTO getObjectWithDefaults(String id) {
//
//        ListDTO listDTO = this.getObject(id);
//        listDTO.getListComponentFilterFieldList()
//                .stream()
//                .filter(x -> x.getDefaultValue() != null)
//                .filter(x -> !x.getDefaultValue().equals(""))
//                .filter(x -> !(x.getEditable() == null ? false : x.getEditable()))
//                .forEach(x -> {
//                    ExprResponse exprResponse = expressionService.create(x.getDefaultValue());
//                    if (!exprResponse.getError()) {
//                        Object fieldValue = exprResponse.getExprUnit().getResult();
//                        x.setFieldValue(fieldValue);
//                    }
//                });
//
//        listDTO.getListComponentColumnFieldList()
//                .stream()
//                .filter(x -> x.getDefaultValue() != null)
//                .filter(x -> !x.getDefaultValue().equals(""))
//                .filter(x -> !(x.getEditable() == null ? false : x.getEditable()))
//                .forEach(x -> {
//                    ExprResponse exprResponse = expressionService.create(x.getDefaultValue());
//                    if (!exprResponse.getError()) {
//                        Object fieldValue = exprResponse.getExprUnit().getResult();
//                        x.setFieldValue(fieldValue);
//                    }
//        });
//
//        return listDTO;
//    }

//    @Cacheable(value = "list_ui_cache", key="{ #id, #languageId }")
//    public ListDTO getUiListObject(String id, String languageId) {
//
//        System.out.println("Get UiList object from Database");
//
//        /* Retrieve */
//        ListEntity listEntity = this.listRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist"));
//
//        /* Map */
//        ListDTO listUiDTO = this.listMapper.mapList(listEntity, languageId);
//
//        /* Short */
//        listUiDTO.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
//        listUiDTO.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
//        listUiDTO.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
//        listUiDTO.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
//        listUiDTO.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
//        listUiDTO.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
//        listUiDTO.getListComponentActionFieldList().forEach(af -> {
//            if( af.getListComponentActionFieldList() != null) {
//                af.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentSubFieldDTO::getShortOrder));
//            }
//        });
//
//        List<ListComponentFieldDTO> filtersList = Stream.concat(listUiDTO.getListComponentFilterFieldList().stream(),
//                listUiDTO.getListComponentColumnFieldList().stream())
//                .collect(Collectors.toList());
//
//        /* Calc Default Values */
//        for (ListComponentFieldDTO filterDto : filtersList) {
//
//            if (filterDto.getDefaultValue() == null) continue;
//            if (filterDto.getDefaultValue().equals("")) continue;
//
//            ExprResponse exprResponse = expressionService.create(filterDto.getDefaultValue());
//            if (!exprResponse.getError()) {
//                Object fieldValue = exprResponse.getExprUnit().getResult();
//                if(filterDto.getType().equals("list")){
//                    // List<List<Object>> response = (List<List<Object>>) fieldValue;
//                    filterDto.setDefaultValue(fieldValue.toString());
//                }else {
//                    filterDto.setFieldValue(fieldValue);
//                    filterDto.setDefaultValue(fieldValue.toString());
//                }
//            }
//        }
//
//        /* Return */
//        return listUiDTO;
//    }

    public ListResultsDataDTO getListResultsData(ListDTO listDTO) {
        ListResultsDataDTO listResultsDataDTO = new ListResultsDataDTO();

        List<Map<String, Object>> listContent = this.listRetrieverNativeRepository.executeListAndGetData(listDTO);
        listResultsDataDTO.setListContent(listContent);

        if ((listDTO.getHasPagination() != null && listDTO.getHasPagination())) {

            // Current Page
            Long currentPage = listDTO.getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            listResultsDataDTO.setCurrentPage(currentPage);

            // Get Total Rows
            Long totalRows = this.listRetrieverNativeRepository.executeListAndCountTotalRows(listDTO);
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

    public ListResultsDataDTO getPivotObjectDataByParameters(Map<String, String> parameters,
                                                             ListDTO listDTO) {
        listDTO.setCurrentPage(0L);
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        this.mapPivotColumns(listDTO);
        /* Recalculate Select Query for Pivot */
        String pivotSelectQery = this.listRetrieverNativeRepository.generateSelectPart(listDTO);
        listDTO.setSelectQuery(pivotSelectQery);

        ListResultsDataDTO listResultsDataDTO = this.getListResultsData(listDTO);
        return listResultsDataDTO;
    }


    public ListDTO retrieveListWithBaseQueryByUrl(String jsonUrl) {
        List<String> ids = this.listRepository.getIdByJsonUrl(jsonUrl);

        if (ids.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Url does not exits");
        }

        return this.retrieveListWithBaseQuery(ids.get(0), "0");
    }


    public ListDTO retrieveListAndCalcDefaultExpression(String id, String languageId) {
        ListDTO listDTO = this.retrieveListWithBaseQuery(id, languageId);

        List<ListComponentFieldDTO> filtersList = Stream.concat(listDTO.getListComponentFilterFieldList().stream(),
                        listDTO.getListComponentColumnFieldList().stream())
                .collect(Collectors.toList());

        /* Calc Default Values */
        for (ListComponentFieldDTO filterDto : filtersList) {

            if (filterDto.getDefaultValue() == null) continue;
            if (filterDto.getDefaultValue().equals("")) continue;

            ExprResponse exprResponse = expressionService.createCacheable(filterDto.getDefaultValue(), filterDto.getId() );
            //ExprResponse exprResponse = expressionService.create(filterDto.getDefaultValue());
            if (!exprResponse.getError()) {
                Object fieldValue = expressionService.getResult(exprResponse);
                if (filterDto.getType().equals("list")) {
                    filterDto.setDefaultValue(fieldValue.toString());
                } else {
                    filterDto.setFieldValue(fieldValue);
                    filterDto.setDefaultValue(fieldValue.toString());
                }
            }
        }


        return listDTO;
    }

    @Cacheable(value = "list_cache", key = "{ #id, #languageId }")
    public ListDTO retrieveListWithBaseQuery(String id, String languageId) {
        ListDTO listDTO = this.getObject(id, languageId);
        listDTO = this.listRetrieverNativeRepository.executeListAndGetBaseQueryParts(listDTO);
        return listDTO;
    }

    public ListResultsDataDTO getObjectDataByParameters(Map<String, String> parameters,
                                                        Long page,
                                                        ListDTO listDTO) {
        listDTO = this.calcDefaultsOnListDTO(listDTO);
        listDTO.setCurrentPage(page);
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        this.mapUserDefinedShordOrder(listDTO, parameters);
        ListResultsDataDTO listResultsDataDTO = this.getListResultsData(listDTO);
        return listResultsDataDTO;
    }

    private void mapPivotColumns(ListDTO listDTO) {
        listDTO.getListComponentColumnFieldList().addAll(listDTO.getListComponentLeftGroupFieldList());
        listDTO.getListComponentColumnFieldList().addAll(listDTO.getListComponentTopGroupFieldList());

        listDTO.setListComponentLeftGroupFieldList(new ArrayList<>());
        listDTO.setListComponentTopGroupFieldList(new ArrayList<>());
    }

    private void mapUserDefinedShordOrder(ListDTO listDTO, Map<String, String> parameters) {

        if (!parameters.containsKey("sel-sort-code") || !parameters.containsKey("sel-sort-order")) {
            return;
        }

        String selectedSortOrder = parameters.get("sel-sort-order");
        String selectedSortCode = parameters.get("sel-sort-code");


        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> x.getCode().equals(selectedSortCode))
                .forEach(x -> {
                    ListComponentFieldDTO field = new ListComponentFieldDTO();
                    field.setCode(x.getCode());
                    field.setFormulaType(x.getFormulaType());
                    if (selectedSortOrder.equals("desc")) {
                        field.setEditor("DESC");
                    } else {
                        field.setEditor("ASC");
                    }
                    field.setType(x.getType());
                    field.setComponentPersistEntity(x.getComponentPersistEntity());
                    field.setComponentPersistEntityField(x.getComponentPersistEntityField());
                    listDTO.getListComponentOrderByFieldList().add(0, field);
                });
    }

    private ListDTO mapParametersToListDto(ListDTO listDTO, Map<String, String> parameters) {

        /*
         * Map Parameters to Columns
         * */
        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> (x.getHeaderFilter() != null && x.getHeaderFilter()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> !x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    x.setFieldValue(fieldValue);
                });

        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> (x.getHeaderFilter() != null && x.getHeaderFilter()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    Instant fieldValueInstant = Instant.parse(fieldValue);
                    x.setFieldValue(fieldValueInstant);
                });

        /*
         * Map Parameters to Filters & Left Grouping
         * */
        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.addAll(listDTO.getListComponentFilterFieldList());
        filtersList.addAll(listDTO.getListComponentLeftGroupFieldList());

        filtersList
                .stream()
                .filter(x -> (x.getEditable() != null && x.getEditable()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> !x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    x.setFieldValue(fieldValue);
                });

        filtersList
                .stream()
                .filter(x -> (x.getEditable() != null && x.getEditable()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    Instant fieldValueInstant = Instant.parse(fieldValue);
                    x.setFieldValue(fieldValueInstant);
                });

        return listDTO;
    }

    public List<GroupEntryDTO> getObjectLeftGroupingDataByParameters(Map<String, String> parameters, ListDTO listDTO) {
        listDTO = this.calcDefaultsOnListDTO(listDTO);
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        return this.listRetrieverNativeRepository.executeListAndGetGroupingData(listDTO);
    }

    public String getInstanceVersion(String id) {
        return this.listRepository.getInstanceVersion(id);
    }

    public String getMinJavaScript(String id) {
        String script = this.listRepository.getListMinScript(id);
        return script;
    }

    public String getJavaScript(String id) {
        String script = this.listRepository.getListScript(id);
        return script;
    }

    public String getCssScript(String id) {
        List<String> decodedScripts = new ArrayList<>();
        List<String> scripts = this.listRepository.getCssScriptsById(id);

        scripts.forEach(script -> {
            byte[] decodedBytes = Base64.getDecoder().decode(script);
            String decodedScript = new String(decodedBytes);
            decodedScripts.add(decodedScript);
        });
        return String.join("\n\n", decodedScripts);
    }

    public String getJavaScriptFactory() {
        List<String> ids = this.listRepository.getListIds();
        List<String> scriptLines = new ArrayList<>();
        scriptLines.add("function newListDynamicScript(id) {");
        ids.forEach(id -> {
            String ifClause =
                    String.join("",
                            "if (id == '", id,
                            "' ) return new ListDynamicScript", id.replace("-", "_"), "();");
            scriptLines.add(ifClause);
        });
        scriptLines.add("}");

        return String.join("\n", scriptLines);
    }

    public void updateField(String id, String field, Object fieldValue, Object rel, String languageId) {
        ListDTO listDTO = this.retrieveListWithBaseQuery(id, languageId);
        this.listUpdaterNativeRepository.updateField(listDTO, field, fieldValue, rel);
    }

    public void convertJsonColumns(List<Map<String, Object>> listContent, ListDTO listDTO) {


        List<String> jsonColumnCodes = listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(fieldList -> fieldList.getType().equals("json"))
                .map(fieldList -> fieldList.getCode())
                .collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        listContent.forEach(map -> {
            map.forEach((key, jsonValue) -> {
                if(jsonColumnCodes.contains(key)){
                    try {
                        JsonNode jsonNode = objectMapper.readTree((String)jsonValue);
                        Object jsonParsedObject = objectMapper.convertValue(jsonNode, Object.class);
                        map.put(key, jsonParsedObject);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });

    }

    public Object test() {
        return this.listRetrieverNativeRepository.test();
    }
}
