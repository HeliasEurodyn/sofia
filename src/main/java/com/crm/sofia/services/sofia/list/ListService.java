package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.list.base.GroupEntryDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.dto.sofia.list.base.ListResultsDataDTO;
import com.crm.sofia.dto.sofia.list.user.ListComponentFieldUiDTO;
import com.crm.sofia.dto.sofia.list.user.ListComponentSubFieldUiDTO;
import com.crm.sofia.dto.sofia.list.user.ListUiDTO;
import com.crm.sofia.mapper.sofia.list.designer.ListMapper;
import com.crm.sofia.mapper.sofia.list.user.ListUiMapper;
import com.crm.sofia.model.sofia.expression.ExprResponce;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.native_repository.sofia.list.ListRetrieverNativeRepository;
import com.crm.sofia.native_repository.sofia.list.ListUpdaterNativeRepository;
import com.crm.sofia.repository.sofia.list.ListRepository;
import com.crm.sofia.services.sofia.expression.ExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
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
    private final ListRetrieverNativeRepository listRetrieverNativeRepository;
    private final ListUpdaterNativeRepository listUpdaterNativeRepository;

    @Autowired
    CacheManager cacheManager;

    public ListService(ListRepository listRepository,
                       ListMapper listMapper,
                       ListUiMapper listUiMapper,
                       ExpressionService expressionService,
                       ListRetrieverNativeRepository listRetrieverNativeRepository,
                       ListUpdaterNativeRepository listUpdaterNativeRepository) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
        this.listUiMapper = listUiMapper;
        this.expressionService = expressionService;
        this.listRetrieverNativeRepository = listRetrieverNativeRepository;
        this.listUpdaterNativeRepository = listUpdaterNativeRepository;
    }

    public ListDTO getObject(String id) {
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

    public ListDTO getObjectWithDefaults(String id) {

        ListDTO listDTO = this.getObject(id);
        listDTO.getListComponentFilterFieldList()
                .stream()
                .filter(x -> x.getDefaultValue() != null)
                .filter(x -> !x.getDefaultValue().equals(""))
               // .filter(x -> !(x.getEditable() == null ? false : x.getEditable()))
                .forEach(x -> {
                    ExprResponce exprResponce = expressionService.create(x.getDefaultValue());
                    if (!exprResponce.getError()) {
                        Object fieldValue = exprResponce.getExprUnit().getResult();
                        x.setFieldValue(fieldValue);
                    }
                });

        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> x.getDefaultValue() != null)
                .filter(x -> !x.getDefaultValue().equals(""))
                .forEach(x -> {
                    ExprResponce exprResponce = expressionService.create(x.getDefaultValue());
                    if (!exprResponce.getError()) {
                        Object fieldValue = exprResponce.getExprUnit().getResult();
                        x.setFieldValue(fieldValue);
                    }
        });

        return listDTO;
    }

    @Cacheable(value = "list_ui_cache", key="{ #id, #languageId }")
    public ListUiDTO getUiListObject(String id, String languageId) {

        System.out.println("Get UiList object from Database");

        /* Retrieve */
        ListEntity listEntity = this.listRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist"));

        /* Map */
        ListUiDTO listUiDTO = this.listUiMapper.mapList(listEntity, languageId);


        /* Short */
        listUiDTO.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldUiDTO::getShortOrder));
        listUiDTO.getListComponentActionFieldList().forEach(af -> {
            if( af.getListComponentActionFieldList() != null) {
                af.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentSubFieldUiDTO::getShortOrder));
            }
        });

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
                if(filterDto.getType().equals("list")){
                    // List<List<Object>> response = (List<List<Object>>) fieldValue;
                    filterDto.setDefaultValue(fieldValue.toString());
                }else {
                    filterDto.setFieldValue(fieldValue);
                    filterDto.setDefaultValue(fieldValue.toString());
                }
            }
        }

        /* Return */
        return listUiDTO;
    }

    public ListResultsDataDTO getListResultsData(ListDTO listDTO) {
        ListResultsDataDTO listResultsDataDTO = new ListResultsDataDTO();

        List<Map<String, Object>> listContent = this.listRetrieverNativeRepository.executeListAndGetData(listDTO);
        listResultsDataDTO.setListContent(listContent);

        if ((listDTO.getHasPagination() == null ? false : listDTO.getHasPagination())) {

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

    public ListResultsDataDTO getObjectDataByParameters(Map<String, String> parameters, String jsonUrl) {

        List<String> ids = this.listRepository.getIdByJsonUrl(jsonUrl);

        if (ids.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Url does not exits");
        }

        ListDTO listDTO = this.getObjectWithDefaults(ids.get(0));
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        ListResultsDataDTO listResultsDataDTO = this.getListResultsData(listDTO);
        return listResultsDataDTO;
    }

    public ListResultsDataDTO getPivotObjectDataByParameters(Map<String, String> parameters, String id) {
        ListDTO listDTO = this.getObjectWithDefaults(id);
        listDTO.setCurrentPage(0L);
        listDTO = this.mapParametersToListDto(listDTO, parameters);
        this.mapPivotColumns(listDTO);
        ListResultsDataDTO listResultsDataDTO = this.getListResultsData(listDTO);
        return listResultsDataDTO;
    }

    public ListResultsDataDTO getObjectDataByParameters(Map<String, String> parameters, Long page, String id) {
        ListDTO listDTO = this.getObjectWithDefaults(id);
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

        if(!parameters.containsKey("sel-sort-code") || !parameters.containsKey("sel-sort-order") ){
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
                    if(selectedSortOrder.equals("desc")) {
                        field.setEditor("DESC");
                    } else {
                        field.setEditor("ASC");
                    }
                    field.setType(x.getType());
                    field.setComponentPersistEntity(x.getComponentPersistEntity());
                    field.setComponentPersistEntityField(x.getComponentPersistEntityField());
                    listDTO.getListComponentOrderByFieldList().add(0,field);
                });
    }

    private ListDTO mapParametersToListDto(ListDTO listDTO, Map<String, String> parameters) {

        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.addAll(listDTO.getListComponentFilterFieldList());
        filtersList.addAll(listDTO.getListComponentLeftGroupFieldList());

        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> !x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    x.setFieldValue(fieldValue);
                });

        listDTO.getListComponentColumnFieldList()
                .stream()
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

        filtersList
                .stream()
                .filter(x -> (x.getEditable() == null ? false : x.getEditable()))
               // .filter(x -> (x.getVisible() == null ? false : x.getVisible()))
                .filter(x -> parameters.containsKey(x.getCode()))
                .filter(x -> !x.getType().equals("datetime"))
                .forEach(x -> {
                    String fieldValue = parameters.get(x.getCode());
                    x.setFieldValue(fieldValue);
                });

        filtersList
                .stream()
                .filter(x -> (x.getEditable() == null ? false : x.getEditable()))
              //  .filter(x -> (x.getVisible() == null ? false : x.getVisible()))
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

    public List<GroupEntryDTO> getObjectLeftGroupingDataByParameters(Map<String, String> parameters, String id) {
        ListDTO listDTO = this.getObjectWithDefaults(id);
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
        List<String> formIds = this.listRepository.getListIds();
        List<String> scriptLines = new ArrayList<>();
        scriptLines.add("function newListDynamicScript(id) {");
        formIds.forEach(id -> {
            String ifClause =
                    String.join("",
                            "if (id == '" , id,
                            "' ) return new ListDynamicScript",id.replace("-","_") , "();" );
            scriptLines.add(ifClause);
        });
        scriptLines.add("}");

        return String.join("\n", scriptLines);
    }

    public void updateField(String id, String field, Object fieldValue, Object rel) {
        ListDTO listDTO = this.getObject(id);
        this.listUpdaterNativeRepository.updateField(listDTO, field, fieldValue, rel);

        System.out.println(rel);
        System.out.println(field);
        System.out.println(fieldValue);
    }
}
