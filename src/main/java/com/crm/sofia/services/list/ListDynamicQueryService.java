package com.crm.sofia.services.list;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListComponentDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.model.expression.expressionUnits.ExprComma;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ListDynamicQueryService {

    private final EntityManager entityManager;

    public ListDynamicQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*
     * Iterate to Generate SELECT Columns part
     */
    private String generateSelectColumnsPart(ListComponentDTO listComponentDTO) {

        String queryString = "SELECT ";
        Boolean firstItteration = true;


        List<ListComponentFieldDTO> fieldColumns = listComponentDTO.getListComponentColumnFieldList()
                .stream().filter(
                        fieldColumn -> fieldColumn.getComponentPersistEntityField() != null).collect(Collectors.toList());


        for (ListComponentFieldDTO listComponentFieldDTO : fieldColumns) {
            String field =
                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + listComponentFieldDTO.getCode();
            if (firstItteration) {
                queryString += field;
            } else {
                queryString += "," + field;
            }
            firstItteration = false;
        }

        Pattern pattern = Pattern.compile("^SqlField\\('.+'\\)$");
        List<ListComponentFieldDTO> sqlFieldColumns = listComponentDTO.getListComponentColumnFieldList()
                .stream().filter(
                        fieldColumn -> pattern.matcher(fieldColumn.getEditor()).matches()).collect(Collectors.toList());

        for (ListComponentFieldDTO listComponentFieldDTO : sqlFieldColumns) {
            String field = "( " +
                    listComponentFieldDTO.getEditor().substring(10, listComponentFieldDTO.getEditor().length() - 2) +
                    ") as " + listComponentFieldDTO.getCode();
            if (firstItteration) {
                queryString += field;
            } else {
                queryString += "," + field;
            }
            firstItteration = false;
        }

        return queryString;

    }

    /*
     * Iterate to Generate SELECT Columns part
     */
    private String generateSelectLeftGroupPart(ListComponentDTO listComponentDTO) {

        String queryString = "SELECT ";
        Boolean firstItteration = true;
        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {
            String field =
                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + listComponentFieldDTO.getCode();
            if (firstItteration) {
                queryString += field;
            } else {
                queryString += "," + field;

            }

            firstItteration = false;
        }

        queryString += ", count(*) AS total";

        return queryString;
    }

    /*
     * Iterate to Generate FROM Tables & Relashionships part
     */
    private String generateFromColumnsPart(ListComponentDTO listComponentDTO) {

        String queryString = " FROM";

        List<ComponentPersistEntityDTO> sortedComponentPersistEntityList = listComponentDTO.getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());

        for (ComponentPersistEntityDTO componentPersistEntityDTO : sortedComponentPersistEntityList) {

            if (componentPersistEntityDTO.getSelector() != null) {
                String selector = componentPersistEntityDTO.getSelector();
                String join = selector.replaceAll("\\[|\\].+", "");
                queryString += " " + join;
            }

            if (componentPersistEntityDTO.getPersistEntity().getClass() == AppViewDTO.class) {
                AppViewDTO appViewDTO = (AppViewDTO) componentPersistEntityDTO.getPersistEntity();
                queryString += " ( " +
                        appViewDTO.getQuery() + " ) " + componentPersistEntityDTO.getCode();
            } else {
                queryString += " " +
                        componentPersistEntityDTO.getPersistEntity().getName() + " " + componentPersistEntityDTO.getCode();
            }

            if (componentPersistEntityDTO.getSelector() != null) {
                String selector = componentPersistEntityDTO.getSelector();
                String onStatement = selector.replaceAll("\\[.+\\]", "");
                queryString += " ON " + onStatement;

            }

        }

        return queryString;
    }

    /*
     * Iterate to Generate WHERE Fields part
     */
    private String generateWhereColumnsPart(List<ListComponentFieldDTO> filtersList) {

        String queryString = "";
        Boolean firstItteration = true;
        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {

            if (listComponentFieldDTO.getFieldValue() == null) listComponentFieldDTO.setFieldValue("");
            if (listComponentFieldDTO.getFieldValue().equals("")) {
                if (listComponentFieldDTO.getRequired()) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter " + listComponentFieldDTO.getCode() + " should not be empty!");
                }
                continue;
            }

            String field =
                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
                            listComponentFieldDTO.getOperator() + " ";

            if (listComponentFieldDTO.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(listComponentFieldDTO.getFieldValue().toString());
                field += " '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant) + "' ";
            } else if (listComponentFieldDTO.getType().equals("varchar") || listComponentFieldDTO.getType().equals("text")) {
                if (listComponentFieldDTO.getOperator().equals("like")) {
                    field += " '" + listComponentFieldDTO.getFieldValue().toString().replaceAll("\\*+", "%") + "' ";
                } else {
                    field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
                }
            } else {
                field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
            }


            if (firstItteration) {
                queryString += " WHERE " + field;
            } else {
                queryString += " AND " + field;
            }


            firstItteration = false;
        }

        return queryString;
    }

    /*
     * Iterate to Generate custom structure WHERE Fields part
     */
    private String generateCustomWhereColumnsPart(List<ListComponentFieldDTO> filtersList, String filterFieldStructure) {

        String queryString = " WHERE " + filterFieldStructure;

        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {

            if (listComponentFieldDTO.getFieldValue() == null) listComponentFieldDTO.setFieldValue("");
            if (listComponentFieldDTO.getFieldValue().equals("")) {
                if (listComponentFieldDTO.getRequired()) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter " + listComponentFieldDTO.getCode() + " should not be empty!");
                }

                queryString.replaceAll("$" + listComponentFieldDTO.getCode(), " 1=1 ");
                continue;
            }

            String field =
                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
                            listComponentFieldDTO.getOperator() + " ";

            if (listComponentFieldDTO.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(listComponentFieldDTO.getFieldValue().toString());
                field += " '" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant) + "' ";
            } else if (listComponentFieldDTO.getType().equals("varchar") || listComponentFieldDTO.getType().equals("text")) {
                if (listComponentFieldDTO.getOperator().equals("like")) {
                    field += " '" + listComponentFieldDTO.getFieldValue().toString().replaceAll("\\*+", "%") + "' ";
                } else {
                    field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
                }
            } else {
                field += " '" + listComponentFieldDTO.getFieldValue().toString() + "' ";
            }

            queryString.replaceAll("$" + listComponentFieldDTO.getCode(), field);

        }

        return queryString;
    }

    /*
     * Iterate to Generate Group By Columns part
     */
    private String generateGroupByColumnsPart(ListComponentDTO listComponentDTO) {
        String queryString = "GROUP BY ";
        Boolean firstItteration = true;
        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {

            if (firstItteration) {
                queryString += listComponentFieldDTO.getCode();
            } else {
                queryString += "," + listComponentFieldDTO.getCode();
            }

            firstItteration = false;
        }

        return queryString;
    }

    /*
     * Iterate to Generate Order By Columns part
     */
    private String generateOrderByColumnsPart(ListComponentDTO listComponentDTO) {
        String queryString = "";
        Boolean firstItteration = true;
        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentOrderByFieldList()) {

            if (firstItteration) {
                queryString += " ORDER BY ";
            }

            String field =
                    listComponentFieldDTO.getComponentPersistEntity().getCode() + "." +
                            listComponentFieldDTO.getComponentPersistEntityField().getPersistEntityField().getName();

            if (listComponentFieldDTO.getEditor().equals("ASC") || listComponentFieldDTO.getEditor().equals("DESC")) {
                field += " " + listComponentFieldDTO.getEditor();
            } else {
                field += " ASC ";
            }

            if (firstItteration) {
                queryString += field;
            } else {
                queryString += "," + field;
            }

            firstItteration = false;
        }

        return queryString;
    }

    /*
     * Iterate to Generate Order By Columns part
     */
    private String generateOrderByLeftGroupPart(ListComponentDTO listComponentDTO) {
        String queryString = " ORDER BY ";
        Boolean firstItteration = true;
        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {

            if (firstItteration) {
                queryString += listComponentFieldDTO.getCode() + " ASC ";
            } else {
                queryString += "," + listComponentFieldDTO.getCode() + " ASC ";
            }

            firstItteration = false;
        }
        return queryString;
    }


    /*
     * Iterate to Generate Limit part
     */
    private String generateLimitPart(ListComponentDTO listComponentDTO) {

        String queryString = "";
        if (listComponentDTO.getHasPagination()) {
            Long currentPage = listComponentDTO.getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            Long offset = listComponentDTO.getPageSize() * currentPage;
            queryString += "LIMIT " + listComponentDTO.getPageSize() + " OFFSET  " + offset;
        } else if (listComponentDTO.getHasMaxSize()) {
            queryString += "LIMIT " + listComponentDTO.getMaxSize();
        }
        return queryString;
    }


    /*
     * Execute Sql Query
     * Set results in a HashMap Array
     * return HashMap Array results
     */
    private Long executeCountQuery(ListComponentDTO listComponentDTO, String queryString) {

        Query query = entityManager.createNativeQuery(queryString);
        List<Object> dataList = query.getResultList();
        for (Object dataRow : dataList) {
            BigInteger totalRows = (BigInteger) dataRow;
            return totalRows.longValue();
        }

        return 0L;
    }

    /*
     * Execute Sql Query
     * Set results in a HashMap Array
     * return HashMap Array results
     */
    private List<Map<String, Object>> executeListQuery(ListComponentDTO listComponentDTO, String queryString) {

        List<Map<String, Object>> listContent = new ArrayList<>();

        List<ListComponentFieldDTO> fieldColumns = listComponentDTO.getListComponentColumnFieldList()
                .stream().filter(
                        fieldColumn -> fieldColumn.getComponentPersistEntityField() != null).collect(Collectors.toList());

        Pattern pattern = Pattern.compile("^SqlField\\('.+'\\)$");
        List<ListComponentFieldDTO> sqlFieldColumns = listComponentDTO.getListComponentColumnFieldList()
                .stream().filter(
                        fieldColumn -> pattern.matcher(fieldColumn.getEditor()).matches()).collect(Collectors.toList());


        Query query = entityManager.createNativeQuery(queryString);
        List<Object[]> dataList = query.getResultList();
        for (Object[] dataRow : dataList) {

            Map<String, Object> dataMapRow = new HashMap<>();
            int i = 0;

            for (ListComponentFieldDTO listComponentFieldDTO : fieldColumns) {
                dataMapRow.put(listComponentFieldDTO.getCode(), dataRow[i]);
                i++;
            }

            for (ListComponentFieldDTO listComponentFieldDTO : sqlFieldColumns) {
                dataMapRow.put(listComponentFieldDTO.getCode(), dataRow[i]);
                i++;
            }

            listContent.add(dataMapRow);

        }

        return listContent;
    }

    /*
     * Execute algorithm
     * Set results in a HashMap Array
     * return HashMap Array results
     */
    private List<GroupEntryDTO> executeGroupQuery(ListComponentDTO listComponentDTO, String queryString) {

        Query query = entityManager.createNativeQuery(queryString);
        List<Object[]> dataList = query.getResultList();

        int countIndex = listComponentDTO.getListComponentLeftGroupFieldList().size();
        List<GroupEntryDTO> groupEntries = new ArrayList<>();
        int i = 0;

        for (Object[] dataRow : dataList) {

            List<GroupEntryDTO> currentGroupEntries = groupEntries;

            for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {

                String currentValue = dataRow[i].toString();
                GroupEntryDTO entry = currentGroupEntries.stream()
                        .filter(storedEntry -> currentValue.equals(storedEntry.getValue()))
                        .findAny()
                        .orElse(null);

                if (entry == null) {
                    entry = new GroupEntryDTO();
                    entry.setCode(listComponentFieldDTO.getCode());
                    entry.setValue(dataRow[i]);
                    entry.setCount(Integer.parseInt(dataRow[countIndex].toString()));
                    entry.setChildren(new ArrayList<>());
                    currentGroupEntries.add(entry);
                }

                currentGroupEntries = entry.getChildren();
                i++;
            }
            i = 0;
        }

        return groupEntries;
    }


    public List<Map<String, Object>> executeListAndGetData(ListDTO dto) {
        List<Map<String, Object>> listContent = new ArrayList<>();
        ListComponentDTO listComponentDTO = dto.getListComponentList().get(0);

        if (listComponentDTO.getListComponentColumnFieldList().size() == 0) {
            return listContent;
        }

        /*
         * Select clause
         */
        String queryString = this.generateSelectColumnsPart(listComponentDTO);

        /*
         * From clause
         */
        queryString += this.generateFromColumnsPart(listComponentDTO);


        /*
         * Where clause
         */
        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {
            listComponentFieldDTO.setOperator("=");
            listComponentFieldDTO.setRequired(false);
        }

        List<ListComponentFieldDTO> filtersList = Stream.concat(listComponentDTO.getListComponentFilterFieldList().stream(),
                listComponentDTO.getListComponentLeftGroupFieldList().stream())
                .collect(Collectors.toList());
        filtersList = filtersList.stream()
                .filter(field -> field.getComponentPersistEntity() != null).collect(Collectors.toList());

        if (!listComponentDTO.getCustomFilterFieldStructure())
            queryString += this.generateWhereColumnsPart(filtersList);
        if (listComponentDTO.getCustomFilterFieldStructure())
            queryString += this.generateCustomWhereColumnsPart(filtersList, listComponentDTO.getFilterFieldStructure());

        /*
         * Order By clause
         */
        queryString += this.generateOrderByColumnsPart(listComponentDTO);

        /*
         * Limit clause
         */
        queryString += this.generateLimitPart(listComponentDTO);

        /*
         * Execute
         */
        listContent = this.executeListQuery(listComponentDTO, queryString);

        return listContent;
    }

    public List<GroupEntryDTO> executeListAndGetGroupingData(ListDTO dto) {
        List<GroupEntryDTO> groupContent = new ArrayList<>();
        ListComponentDTO listComponentDTO = dto.getListComponentList().get(0);

        if (listComponentDTO.getListComponentLeftGroupFieldList().size() == 0) {
            return groupContent;
        }

        /*
         * Select clause
         */
        String queryString = this.generateSelectLeftGroupPart(listComponentDTO);

        /*
         * From clause
         */
        queryString += this.generateFromColumnsPart(listComponentDTO);

        /*
         * Where clause
         */
        List<ListComponentFieldDTO> filtersList = listComponentDTO.getListComponentFilterFieldList().stream()
                .filter(field -> field.getComponentPersistEntity() != null).collect(Collectors.toList());

        if (!listComponentDTO.getCustomFilterFieldStructure())
            queryString += this.generateWhereColumnsPart(filtersList);
        if (listComponentDTO.getCustomFilterFieldStructure())
            queryString += this.generateCustomWhereColumnsPart(filtersList, listComponentDTO.getFilterFieldStructure());

        /*
         * Group By clause
         */
        queryString += this.generateGroupByColumnsPart(listComponentDTO);

        /*
         * Order By clause
         */
        queryString += this.generateOrderByLeftGroupPart(listComponentDTO);

        /*
         * Execute
         */
        groupContent = this.executeGroupQuery(listComponentDTO, queryString);

        return groupContent;
    }

    public Long executeListAndCountTotalRows(ListDTO dto) {

        ListComponentDTO listComponentDTO = dto.getListComponentList().get(0);

        if (listComponentDTO.getListComponentColumnFieldList().size() == 0) {
            return 0L;
        }

        /*
         * Select clause
         */
        String queryString = "SELECT COUNT(*) AS TOTALROWS ";

        /*
         * From clause
         */
        queryString += this.generateFromColumnsPart(listComponentDTO);


        /*
         * Where clause
         */
        for (ListComponentFieldDTO listComponentFieldDTO : listComponentDTO.getListComponentLeftGroupFieldList()) {
            listComponentFieldDTO.setOperator("=");
            listComponentFieldDTO.setRequired(false);
        }

        List<ListComponentFieldDTO> filtersList = Stream.concat(listComponentDTO.getListComponentFilterFieldList().stream(),
                listComponentDTO.getListComponentLeftGroupFieldList().stream())
                .collect(Collectors.toList());
        filtersList = filtersList.stream()
                .filter(field -> field.getComponentPersistEntity() != null).collect(Collectors.toList());

        if (!listComponentDTO.getCustomFilterFieldStructure())
            queryString += this.generateWhereColumnsPart(filtersList);
        if (listComponentDTO.getCustomFilterFieldStructure())
            queryString += this.generateCustomWhereColumnsPart(filtersList, listComponentDTO.getFilterFieldStructure());



        /*
         * Execute
         */
        Long totalRows = this.executeCountQuery(listComponentDTO, queryString);

        return totalRows;
    }

}
