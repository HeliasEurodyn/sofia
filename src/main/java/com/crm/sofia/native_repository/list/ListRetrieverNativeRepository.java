package com.crm.sofia.native_repository.list;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.crm.sofia.services.auth.JWTService;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ListRetrieverNativeRepository {

    private final EntityManager entityManager;
    private final JWTService jwtService;

    public ListRetrieverNativeRepository(EntityManager entityManager,
                                         JWTService jwtService) {
        this.entityManager = entityManager;
        this.jwtService = jwtService;
    }

    public ListDTO executeListAndGetBaseQueryParts(ListDTO listDTO) {

        /*
         * Select clause
         */
        String queryString = this.generateSelectPart(listDTO);
        listDTO.setSelectQuery(queryString);

        /*
         * Select Left Group clause
         */
         queryString = this.generateSelectLeftGroupPart(listDTO);
         listDTO.setSelectLeftGroupQuery(queryString);

        /*
         * Identify Persist Entities
         */
        List<ComponentPersistEntityDTO> persistEntities = this.identifyFromPersistEntities(listDTO);

        /*
         * From clause
         */
        queryString = this.generateFromPart(persistEntities);
        listDTO.setFromQuery(queryString);

        return listDTO;
    }


    public List<Map<String, Object>> executeListAndGetData(ListDTO listDTO) {
        List<Map<String, Object>> listContent = new ArrayList<>();

        if (listDTO.getListComponentColumnFieldList().size() == 0) {
            return listContent;
        }

        /*
         * Select clause
         */
       // String queryString = this.generateSelectPart(listDTO);
        String queryString = listDTO.getSelectQuery();

        /*
         * Identify Persist Entities
         */
        //  List<ComponentPersistEntityDTO> persistEntities = this.identifyFromPersistEntities(listDTO);

        /*
         * From clause
         */
        queryString = queryString.concat(listDTO.getFromQuery());

        /*
         * Where clause
         */
        queryString += this.generateWherePart(listDTO);

        /*
         * Order By clause
         */
        queryString += this.generateOrderByPart(listDTO);

        /*
         * Limit clause
         */
        queryString += this.generateLimitPart(listDTO);

        /*
         * Generate Query And set filter Parameters
         */
        Query query = this.createQueryAndReplaceParameters(listDTO, queryString);

        log.debug(queryString);

        /*
         * Execute
         */
        listContent = this.executeListQuery(listDTO, query);

        return listContent;
    }

    public List<GroupEntryDTO> executeListAndGetGroupingData(ListDTO listDTO) {
        List<GroupEntryDTO> groupContent = new ArrayList<>();

        if (listDTO.getListComponentLeftGroupFieldList().size() == 0) {
            return groupContent;
        }

        /*
         * Select clause
         */
        String queryString = listDTO.getSelectLeftGroupQuery();

        /*
         * From clause
         */
         queryString += listDTO.getFromQuery();

        /*
         * Define Filters List For Grouping
         */
        List<ListComponentFieldDTO> filtersList = listDTO.getListComponentFilterFieldList()
                .stream()
                .filter(x -> x.getFieldValue() != null)
                .filter(x -> !x.getFieldValue().equals(""))
                .filter(field -> field.getComponentPersistEntity() != null)
                .collect(Collectors.toList());

        /*
         * Where clause
         */
        if (!listDTO.getCustomFilterFieldStructure()) {
            queryString += this.generateWherePart(filtersList);
        } else {
            queryString += this.generateCustomWhereColumnsPart(filtersList, listDTO.getFilterFieldStructure());
        }

        //queryString += this.generateWherePart(filtersList);

        /*
         * Group By clause
         */
        queryString += this.generateGroupByColumnsPart(listDTO);

        /*
         * Order By clause
         */
        queryString += this.generateOrderByPart(listDTO);

        /*
         * Generate Query
         */
        Query query = this.generateGroupQuery(filtersList, queryString);

        /*
         * Execute Query
         */
        groupContent = this.executeGroupQuery(listDTO, query);

        return groupContent;
    }

    public Long executeListAndCountTotalRows(ListDTO listDTO) {

        if (listDTO.getListComponentColumnFieldList().size() == 0) {
            return 0L;
        }

        /*
         * Select clause
         */
        String queryString = "SELECT COUNT(*) AS TOTALROWS ";

        /*
         * Identify Persist Entities
         */
       // List<ComponentPersistEntityDTO> persistEntities = this.identifyFromPersistEntities(listDTO);

        /*
         * From clause
         */
        queryString = queryString.concat(listDTO.getFromQuery());
       // queryString += this.generateFromPart(persistEntities);


        /*
         * Where clause
         */
        queryString += this.generateWherePart(listDTO);

        /*
         * Generate Query
         */
        Query query = this.createQueryAndReplaceParameters(listDTO, queryString);

        /*
         * Execute Query
         */
        Long totalRows = this.executeCountQuery(listDTO, query);

        return totalRows;
    }

    /*
     * Iterate to Generate SELECT Columns part
     */
    public String generateSelectPart(ListDTO listDTO) {

        List<String> selectionFields = new ArrayList<>();

        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> x.getComponentPersistEntityField() != null)
                .forEach(x -> {
                    String field =
                            x.getComponentPersistEntity().getCode() + "." +
                                    x.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + x.getCode();
                    selectionFields.add(field);
                });

        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> (x.getFormulaType() == null ? "" : x.getFormulaType()).equals("sql"))
                .forEach(x -> {
                    String field = "( " + x.getEditor() + ") as " + x.getCode();
                    selectionFields.add(field);
                });

        return " SELECT " + String.join(",", selectionFields);
    }

    /*
     * Iterate to Generate SELECT Columns part
     */
    private String generateSelectLeftGroupPart(ListDTO listDTO) {
        List<String> selectParts = new ArrayList<>();

        listDTO.getListComponentLeftGroupFieldList()
                .stream()
                .forEach(x -> {
                    String selectPart = "";
                    if ((x.getFormulaType() == null ? "" : x.getFormulaType()).equals("sql")) {
                        selectPart = "( " + x.getEditor() + ") as " + x.getCode();
                    } else {
                        selectPart =
                                x.getComponentPersistEntity().getCode() + "." +
                                        x.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + x.getCode();
                    }
                    selectParts.add(selectPart);
                });

        selectParts.add("count(*) AS total");

        return "SELECT " + String.join(",", selectParts);
    }

    /*
     * Iterate to find related to query persist entities
     */
    private List<ComponentPersistEntityDTO> identifyFromPersistEntities(ListDTO listDTO) {

        /*
         * Unite All Fields
         * */
        List<ListComponentFieldDTO> fields = new ArrayList<>();
        fields.addAll(listDTO.getListComponentColumnFieldList());
        fields.addAll(listDTO.getListComponentFilterFieldList());
        fields.addAll(listDTO.getListComponentLeftGroupFieldList());
        fields.addAll(listDTO.getListComponentTopGroupFieldList());
        fields.addAll(listDTO.getListComponentOrderByFieldList());

        /*
         * Retrieve Cpes Flatted
         * */
        List<ComponentPersistEntityDTO> flattedCpes = listDTO.getComponent().flatComponentPersistEntityTree().collect(Collectors.toList());

        // 1. CpeIds by Fields
        List<String> cpeIds =
                fields.stream()
                        .filter(x -> x.getComponentPersistEntity() != null)
                        .map(x -> x.getComponentPersistEntity().getId())
                        .distinct()
                        .collect(Collectors.toList());

        // 2. All Cpes by Sql Formula Fields
        List<String> sqlEditors =
                fields.stream()
                        .filter(x -> x.getFormulaType() != null)
                        .filter(x -> x.getFormulaType().equals("sql"))
                        .filter(x -> x.getEditor() != null)
                        .filter(x -> !x.getEditor().isEmpty())
                        .map(x -> x.getEditor())
                        .distinct()
                        .collect(Collectors.toList());

        flattedCpes
                .stream()
                .filter(cpe -> {
                    return sqlEditors.stream()
                            .filter(editor -> editor.contains(cpe.getCode() + "."))
                            .findFirst().isPresent();
                })
                .filter(cpe -> !cpeIds.contains(cpe.getId()))
                .distinct()
                .forEach(cpe -> cpeIds.add(cpe.getId()));


        /* 3. Return Selected Cpes */
        List<ComponentPersistEntityDTO> cpes = new ArrayList<>();

        flattedCpes
                .stream()
                .filter(cpe -> cpeIds.contains(cpe.getId()))
                .forEach(cpe -> {

                    List<ComponentPersistEntityDTO> cpesUpToId =
                            this.getCpeTreeToListUpToId(listDTO.getComponent().getComponentPersistEntityList(),
                                    cpe.getId());

                    List<ComponentPersistEntityDTO> newCpes =
                            cpesUpToId.stream()
                                    .filter(x -> !cpes.contains(x))
                                    .collect(Collectors.toList());

                    cpes.addAll(newCpes);
                });

        return cpes;
    }

    /*
     * Iterate to Generate FROM Tables & Relashionships part
     */
    private String generateFromPart(List<ComponentPersistEntityDTO> fromCPersistEntities) {

        List<String> joinParts = new ArrayList<>();
        fromCPersistEntities
                .stream()
              //  .sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder))
                .forEach(cPersistEntity -> {
                    String joinPart = "";

                    /* Join */
                    String joinTypePart = " LEFT OUTER JOIN ";

                    /* Table, View or AppView */
                    String joinEntityPart = "";
                    if (cPersistEntity.getPersistEntity().getEntitytype().equals("AppView")) {
                        PersistEntityDTO appViewDTO = cPersistEntity.getPersistEntity();
                        joinEntityPart = " ( " +
                                appViewDTO.getQuery() + " ) " + cPersistEntity.getCode();
                    } else {
                        joinEntityPart += " " + cPersistEntity.getPersistEntity().getName() + " " + cPersistEntity.getCode();
                    }

                    /* On */
                    List<String> joinFieldsPart = new ArrayList<>();
                    cPersistEntity.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(f -> !(f.getLocateStatement() == null ? "" : f.getLocateStatement()).equals("#SELECTIONID"))
                            .filter(f -> !(f.getLocateStatement() == null ? "" : f.getLocateStatement()).equals(""))
                            .forEach(persistEntityField -> {

                                String field = cPersistEntity.getCode() + "." +
                                        persistEntityField.getPersistEntityField().getName();

                                String fieldJoin =
                                        persistEntityField.getLocateStatement().replaceAll("#", "");

                                joinFieldsPart.add(String.join(" ", field, "=", fieldJoin));
                            });

                    if (joinFieldsPart.size() > 0) {
                        joinPart = joinTypePart +
                                joinEntityPart +
                                " ON " +
                                String.join(" AND ", joinFieldsPart);
                    } else {
                        joinPart = joinEntityPart;
                    }

                    joinParts.add(joinPart);
                });

        return " FROM " + String.join(" ", joinParts);
    }

    /*
     * Generate Where part
     */
    private String generateWherePart(ListDTO listDTO) {
        listDTO.getListComponentLeftGroupFieldList()
                .forEach(x -> {
                    x.setOperator("=");
                    x.setRequired(false);
                });

        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.addAll(listDTO.getListComponentFilterFieldList());
        filtersList.addAll(listDTO.getListComponentLeftGroupFieldList());
        filtersList.addAll(listDTO.getListComponentColumnFieldList());

        /*
         * Where clause
         */
        /* Check for empty required Fields */
        Optional<ListComponentFieldDTO> optionalRequiredFieldEmpty =
                filtersList
                        .stream()
                        .filter(x -> (x.getFieldValue() == null ? "" : x.getFieldValue()).equals("") &&
                                (x.getRequired()  == null ? false : x.getRequired()))
                        .findFirst();

        if (optionalRequiredFieldEmpty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter "
                    + optionalRequiredFieldEmpty.get().getCode() + " should not be empty!");
        }

        filtersList =
                filtersList
                        .stream()
                        .filter(x -> x.getFieldValue() != null)
                        .filter(x -> !x.getFieldValue().equals(""))
                        .collect(Collectors.toList());

        if (!listDTO.getCustomFilterFieldStructure()) {
            return this.generateWherePart(filtersList);
        } else {
            return this.generateCustomWhereColumnsPart(filtersList, listDTO.getFilterFieldStructure());
        }
    }

    private String generateCountWherePart(ListDTO listDTO) {
        listDTO.getListComponentLeftGroupFieldList()
                .forEach(x -> {
                    x.setOperator("=");
                    x.setRequired(false);
                });

        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.addAll(listDTO.getListComponentFilterFieldList());
        filtersList.addAll(listDTO.getListComponentLeftGroupFieldList());
        filtersList.addAll(listDTO.getListComponentColumnFieldList());

        /* Check for empty required Fields */
        Optional<ListComponentFieldDTO> optionalRequiredFieldEmpty =
                filtersList
                        .stream()
                        .filter(x -> (x.getFieldValue() == null ? "" : x.getFieldValue()).equals("") && x.getRequired())
                        .findFirst();

        if (optionalRequiredFieldEmpty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter "
                    + optionalRequiredFieldEmpty.get().getCode() + " should not be empty!");
        }

        filtersList = filtersList
                .stream()
                .filter(x -> x.getFieldValue() != null)
                .filter(x -> !x.getFieldValue().equals(""))
                .filter(field -> field.getComponentPersistEntity() != null)
                .collect(Collectors.toList());

        if (!listDTO.getCustomFilterFieldStructure()) {
            return this.generateWherePart(filtersList);
        } else {
            return this.generateCustomWhereColumnsPart(filtersList, listDTO.getFilterFieldStructure());
        }
    }

    /*
     * Iterate to Generate WHERE Fields part
     */
    private String generateWherePart(List<ListComponentFieldDTO> filtersList) {

        List<String> whereClauseParts = new ArrayList<>();

        /* Iterate and create Where parts */
        filtersList
                .stream()
                .filter(x -> (x.getFormulaType() == null ? "" : x.getFormulaType()).equals("") ||
                        (x.getFormulaType() == null ? "" : x.getFormulaType()).equals("column"))
                .forEach(x -> {
                    String whereClause =
                            x.getComponentPersistEntity().getCode() + "." +
                                    x.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
                                    x.getOperator() + " :filter_" + x.getCode();
                    whereClauseParts.add(whereClause);
                });

        filtersList
                .stream()
                .filter(x -> (x.getFormulaType() == null ? "" : x.getFormulaType()).equals("sql"))
                .forEach(x -> {
                    String whereClause =
                            "( " + x.getEditor() + " )" +
                                    x.getOperator() + " :filter_" + x.getCode();
                    whereClauseParts.add(whereClause);
                });

        if (whereClauseParts.size() == 0) {
            return "";
        } else {
            return " WHERE ".concat( String.join(" AND ", whereClauseParts) ) ;
        }
    }

    /*
     * Iterate to Generate custom structure WHERE Fields part
     */
    private String generateCustomWhereColumnsPart(List<ListComponentFieldDTO> filtersList, String filterFieldStructure) {

        Map<String, String> whereClauseParts = new HashMap<>();
        String dynamicFilterFieldStructure = "";

        /* Check for empty required Fields */
        Optional<ListComponentFieldDTO> optionalRequiredFieldEmpty =
                filtersList
                        .stream()
                        .filter(x -> (x.getFieldValue() == null ? "" : x.getFieldValue()).equals(""))
                        .filter(x -> x.getRequired())
                        .findFirst();

        if (optionalRequiredFieldEmpty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter "
                    + optionalRequiredFieldEmpty.get().getCode() + " should not be empty!");
        }

        /* Iterate and create Where parts for empty field values*/
        filtersList
                .stream()
                .filter(x -> (x.getFieldValue() == null ? "" : x.getFieldValue()).equals(""))
                .forEach(x -> {
                    whereClauseParts.put(x.getCode(), " 1=1 ");
                });

        /* Iterate and create Where parts for not empty field values*/
        filtersList
                .stream()
                .filter(x -> (x.getFormulaType() == null ? "" : x.getFormulaType()).equals(""))
                .filter(x -> !(x.getFieldValue() == null ? "" : x.getFieldValue()).equals(""))
                .forEach(x -> {

                    String whereClause =
                            x.getComponentPersistEntity().getCode() + "." +
                                    x.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
                                    x.getOperator() + " :filter_" + x.getCode();

                    whereClauseParts.put(x.getCode(), whereClause);
                });

        filtersList
                .stream()
                .filter(x -> (x.getFormulaType() == null ? "" : x.getFormulaType()).equals("sql"))
                .filter(x -> !(x.getFieldValue() == null ? "" : x.getFieldValue()).equals(""))
                .forEach(x -> {
                    String whereClause =
                            "( " + x.getEditor() + " )" +
                                    x.getOperator() + " :filter_" + x.getCode();
                    whereClauseParts.put(x.getCode(), whereClause);
                });


        /* Replace Where in custom field srtucture */
        for (Map.Entry<String, String> entry : whereClauseParts.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();

            if (filterFieldStructure.contains("$" + k)) {
                filterFieldStructure = filterFieldStructure.replaceAll("\\$" + k, v);
            } else {
                dynamicFilterFieldStructure += " AND " + v;
            }
        }

        return " WHERE ( " + filterFieldStructure + " ) " + dynamicFilterFieldStructure;
    }

    /*
     * Iterate to Generate Group By Columns part
     */
    private String generateGroupByColumnsPart(ListDTO listDTO) {
        List<String> fields = new ArrayList<>();

        listDTO.getListComponentLeftGroupFieldList()
                .forEach(x -> {
                    fields.add(x.getCode());
                });

        if (fields.size() == 0) {
            return "";
        } else {
            return " GROUP BY " + String.join(",", fields);
        }
    }

    /*
     * Iterate to Generate Order By Columns part
     */
    private String generateOrderByPart(ListDTO listDTO) {
        List<String> orderByParts = new ArrayList<>();
        listDTO.getListComponentOrderByFieldList().forEach(x -> {
            String orderByPart = "";

            if ((x.getFormulaType() == null ? "" : x.getFormulaType()).equals("sql")) {
                orderByPart = x.getCode();
            } else {
                orderByPart = x.getComponentPersistEntity().getCode() + "." +
                        x.getComponentPersistEntityField().getPersistEntityField().getName();
            }

            String editor = (x.getEditor() == null ? "" : x.getEditor());
            if (editor.equals("ASC") || editor.equals("DESC")) {
                orderByPart += " " + editor;
            } else {
                orderByPart += " ASC";
            }

            orderByParts.add(orderByPart);
        });

        if (orderByParts.size() == 0) {
            return "";
        } else {
            return " ORDER BY ".concat( String.join(",", orderByParts));
        }
    }


    /*
     * Iterate to Generate Limit part
     */
    private String generateLimitPart(ListDTO listDTO) {

        if ((listDTO.getHasPagination() != null && listDTO.getHasPagination())) {
            Long currentPage = listDTO.getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            Long offset = listDTO.getPageSize() * currentPage;

            return " LIMIT " + listDTO.getPageSize() + " OFFSET  " + offset;

        } else if ((listDTO.getHasMaxSize() != null && listDTO.getHasMaxSize())) {
            return " LIMIT " + listDTO.getMaxSize();
        }

        return "";
    }

    /*
     * Execute Sql Query
     * Set results in a HashMap Array
     * return HashMap Array results
     */
    private Long executeCountQuery(ListDTO listDTO, Query query) {
        List<Object> dataList = query.getResultList();
        for (Object dataRow : dataList) {
            BigInteger totalRows = (BigInteger) dataRow;
            return totalRows.longValue();
        }

        return 0L;
    }

    private Query createQueryAndReplaceParameters(ListDTO listDTO, String queryString) {

        Query query = entityManager.createNativeQuery(queryString);
        listDTO.getListComponentLeftGroupFieldList()
                .forEach(x -> {
                    x.setOperator("=");
                    x.setRequired(false);
                });

        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.addAll(listDTO.getListComponentFilterFieldList());
        filtersList.addAll(listDTO.getListComponentLeftGroupFieldList());
        filtersList.addAll(listDTO.getListComponentColumnFieldList());

        filtersList =
                filtersList.stream()
                        .filter(x -> x.getFieldValue() != null)
                        .filter(x -> !x.getFieldValue().equals(""))
                        .collect(Collectors.toList());

        /* Iterate and create Where parts */
        filtersList.forEach(x -> {

            String filterPart = "";
            if (x.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(x.getFieldValue().toString());
                filterPart = DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneOffset.UTC).format(valueInstant);
            } else if (x.getType().equals("datetime_det")) {
                Instant valueInstant = Instant.parse(x.getFieldValue().toString());
                filterPart = DateTimeFormatter.ofPattern("yyyyMMddhhmm00").withZone(ZoneOffset.UTC).format(valueInstant);
            }
            else if (x.getType().equals("varchar") || x.getType().equals("text") || x.getType().equals("field") || x.getType().equals("simple-field")) {
                if (x.getOperator().equals("like")) {

                    filterPart = x.getFieldValue().toString().replaceAll("\\*+", "%");
                    filterPart = "%" + filterPart + "%";
                } else {
                    filterPart = x.getFieldValue().toString();
                }
            } else {
                filterPart = x.getFieldValue().toString();
            }

            if (x.getOperator().equals("in")) {
                query.setParameter("filter_" + x.getCode(), Arrays.asList(filterPart.split(",")));
            } else {
                query.setParameter("filter_" + x.getCode(), filterPart);
            }

        });

        if( queryString.contains(":userid")){
            query.setParameter("userid",this.jwtService.getUserId());
        }

        return query;
    }

    /*
     * Execute Sql Query
     * Set results in a HashMap Array
     * return HashMap Array results
     */
    private List<Map<String, Object>> executeListQuery(ListDTO listDTO, Query query) {

        List<Map<String, Object>> listContent = new ArrayList<>();

        List<ListComponentFieldDTO> fieldColumns = listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> x.getComponentPersistEntityField() != null)
                .collect(Collectors.toList());

        List<ListComponentFieldDTO> sqlFieldColumns = listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> (x.getFormulaType() == null ? "" : x.getFormulaType()).equals("sql"))
                .collect(Collectors.toList());

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

    private Query generateGroupQuery(List<ListComponentFieldDTO> filtersList, String queryString) {
        Query query = entityManager.createNativeQuery(queryString);

        /* Iterate and create Where parts */
        filtersList.forEach(x -> {
            String filterPart = "";
            if (x.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(x.getFieldValue().toString());
                filterPart = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant);
            } else if (x.getType().equals("varchar") || x.getType().equals("text") || x.getType().equals("field") || x.getType().equals("simple-field")) {
                if (x.getOperator().equals("like")) {
                    filterPart = x.getFieldValue().toString().replaceAll("\\*+", "%");
                    filterPart = "%" + filterPart + "%";
                } else {
                    filterPart = x.getFieldValue().toString();
                }
            } else {
                filterPart = x.getFieldValue().toString();
            }

            if (x.getOperator().equals("in")) {
                query.setParameter("filter_" + x.getCode(), Arrays.asList(filterPart.split(","))); //new Integer[]{152,163});
            } else {
                query.setParameter("filter_" + x.getCode(), filterPart);
            }
        });

        if( queryString.contains(":userid")){
            query.setParameter("userid",this.jwtService.getUserId());
        }

        return query;
    }

    /*
     * Execute algorithm
     * Set results in a HashMap Array
     * return HashMap Array results
     */
    private List<GroupEntryDTO> executeGroupQuery(ListDTO listDTO, Query query) {

        List<Object[]> dataList = query.getResultList();

        int countIndex = listDTO.getListComponentLeftGroupFieldList().size();
        List<GroupEntryDTO> groupEntries = new ArrayList<>();
        int i = 0;

        for (Object[] dataRow : dataList) {

            List<GroupEntryDTO> currentGroupEntries = groupEntries;
            GroupEntryDTO parrentEntry = null;

            for (ListComponentFieldDTO listComponentFieldDTO : listDTO.getListComponentLeftGroupFieldList()) {

                String currentValue = (dataRow[i] == null ? "" : dataRow[i].toString());

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

                    if (parrentEntry != null) {
                        GroupEntryDTO abstructParrent = new GroupEntryDTO();
                        abstructParrent.setCode(parrentEntry.getCode());
                        abstructParrent.setValue(parrentEntry.getValue());
                        abstructParrent.setParrent(parrentEntry.getParrent());
                        entry.setParrent(abstructParrent);
                    }

                    currentGroupEntries.add(entry);
                }

                currentGroupEntries = entry.getChildren();
                parrentEntry = entry;
                i++;
            }
            i = 0;
        }

        return groupEntries;
    }

//    private List<ComponentPersistEntityDTO> getComponentPersistEntitiesTreeToList(List<ComponentPersistEntityDTO> initialComponentPersistEntityList) {
//        List<ComponentPersistEntityDTO> allComponentPersistEntityList = new ArrayList<>();
//        allComponentPersistEntityList.addAll(initialComponentPersistEntityList);
//
//        initialComponentPersistEntityList
//                .stream()
//                .filter(x -> x.getComponentPersistEntityList() != null)
//                .filter(x -> x.getComponentPersistEntityList().size() > 0)
//                .forEach(x -> {
//
//                    List<ComponentPersistEntityDTO> componentPersistEntityList =
//                            this.getComponentPersistEntitiesTreeToList(x.getComponentPersistEntityList());
//                    if (componentPersistEntityList.size() > 0) {
//                        allComponentPersistEntityList.addAll(componentPersistEntityList);
//                    }
//
//                });
//
//        return allComponentPersistEntityList;
//    }

    private List<ComponentPersistEntityDTO> getCpeTreeToListUpToId(List<ComponentPersistEntityDTO> cpeList, String id) {

        ComponentPersistEntityDTO selectedCpe =
                cpeList
                        .stream()
                        .filter(x -> x.getId() == id)
                        .findFirst()
                        .orElse(null);

        if (selectedCpe == null) {

            List<ComponentPersistEntityDTO> filteredCpeList =
                    cpeList
                            .stream()
                            .filter(x -> x.getComponentPersistEntityList() != null)
                            .filter(x -> x.getComponentPersistEntityList().size() > 0)
                            .collect(Collectors.toList());

            for (ComponentPersistEntityDTO cpe : filteredCpeList) {
                List<ComponentPersistEntityDTO> selectedChildCpes = this.getCpeTreeToListUpToId(cpe.getComponentPersistEntityList(), id);
                if (selectedChildCpes.size() > 0) {
                    List<ComponentPersistEntityDTO> selectedCpes = new ArrayList<>();
                    selectedCpes.add(cpe);
                    selectedCpes.addAll(selectedChildCpes);
                    return selectedCpes;
                }
            }

        } else {
            return Collections.singletonList(selectedCpe);
        }

        return Collections.emptyList();
    }

    public Object test() {

        Query query = entityManager.createNativeQuery(
                " SELECT "+
                " data_catalog_business_object.id as cf_id, "+
                " data_catalog_category.code as cf_code_2, "+
                " data_catalog_category.name as cf_name_2, "+
                " data_catalog_service.code as cf_code_1, "+
                " data_catalog_service.short_description as cf_short_description, "+
                " data_catalog_service.name as cf_name_1, "+
                " data_catalog_business_object.code as cf_code, "+
                " data_catalog_business_object.name as cf_name, "+
                " data_catalog_business_object.profile_selector as cf_profile_selector  "+
                " FROM  data_catalog_business_object data_catalog_business_object   "+
                " LEFT OUTER JOIN  data_catalog_service data_catalog_service ON data_catalog_service.id = data_catalog_business_object.data_catalog_service_id   "+
                " LEFT OUTER JOIN  data_catalog_category data_catalog_category ON data_catalog_category.id = data_catalog_service.data_catalog_category_id  "+
                " ORDER BY data_catalog_business_object.id ASC ");

        List<Object[]> dataList = query.getResultList();
        List<List<Object>> responses = new ArrayList<>();
        for (Object queryResultLine : dataList) {
            Object[] queryResultLineArray = (Object[]) queryResultLine;
            List<Object> line = new ArrayList<>();
            for (Object queryResult : queryResultLineArray) {
                line.add(queryResult);
            }
            responses.add(line);
        }

        return responses;
    }
}
