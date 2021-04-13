package com.crm.sofia.services.list;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
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

@Service
public class ListDynamicQueryService {

    private final EntityManager entityManager;

    public ListDynamicQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Map<String, Object>> executeListAndGetData(ListDTO listDTO) {
        List<Map<String, Object>> listContent = new ArrayList<>();

        if (listDTO.getListComponentColumnFieldList().size() == 0) {
            return listContent;
        }

        /*
         * Select clause
         */
        String queryString = this.generateSelectPart(listDTO);

        /*
         * Identify formPersistEntities
         */
        List<ComponentPersistEntityDTO> fromPersistEntities = this.identifyFromPersistEntities(listDTO);

        /*
         * From clause
         */
        queryString += this.generateFromPart(fromPersistEntities);

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

        /*
         * Execute
         */
        listContent = this.executeListQuery(listDTO, query);

        return listContent;
    }

    public List<GroupEntryDTO> executeListAndGetGroupingData(ListDTO listDTO) {
        List<GroupEntryDTO> groupContent = new ArrayList<>();
        // ListComponentDTO listComponentDTO = dto.getListComponentList().get(0);

        if (listDTO.getListComponentLeftGroupFieldList().size() == 0) {
            return groupContent;
        }

        /*
         * Select clause
         */
        String queryString = this.generateSelectLeftGroupPart(listDTO);

        /*
         * Identify formPersistEntities
         */
        List<ComponentPersistEntityDTO> fromPersistEntities = this.identifyFromPersistEntities(listDTO);

        /*
         * From clause
         */
        queryString += this.generateFromPart(fromPersistEntities);

        /*
         * Where clause
         */
        /* Check for empty required Fields */
        Optional<ListComponentFieldDTO> optionalRequiredFieldEmpty =
                listDTO.getListComponentFilterFieldList()
                        .stream()
                        .filter(x -> (x.getFieldValue() == null ? "" : x.getFieldValue()).equals("") && x.getRequired())
                        .findFirst();

        if (optionalRequiredFieldEmpty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter "
                    + optionalRequiredFieldEmpty.get().getCode() + " should not be empty!");
        }

        List<ListComponentFieldDTO> filtersList = listDTO.getListComponentFilterFieldList()
                .stream()
                .filter(x -> x.getFieldValue() != null)
                .filter(x -> !x.getFieldValue().equals(""))
                .filter(field -> field.getComponentPersistEntity() != null)
                .collect(Collectors.toList());

        if (!listDTO.getCustomFilterFieldStructure()) {
            queryString += this.generateWherePart(filtersList);
        } else {
            queryString += this.generateCustomWhereColumnsPart(filtersList, listDTO.getFilterFieldStructure());
        }

        /*
         * Group By clause
         */
        queryString += this.generateGroupByColumnsPart(listDTO);

        /*
         * Order By clause
         */
        queryString += this.generateOrderByLeftGroupPart(listDTO);

        /*
         * Generate Query
         */
        Query query = this.generateGroupQuery(listDTO, queryString);

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
         * Identify formPersistEntities
         */
        List<ComponentPersistEntityDTO> fromPersistEntities = this.identifyFromPersistEntities(listDTO);

        /*
         * From clause
         */
        queryString += this.generateFromPart(fromPersistEntities);


        /*
         * Where clause
         */
        this.generateCountWherePart(listDTO);

        /*
         * Generate Query
         */
        Query query = this.generateCountQuery(listDTO, queryString);

        /*
         * Execute Query
         */
        Long totalRows = this.executeCountQuery(listDTO, query);

        return totalRows;
    }

    /*
     * Iterate to Generate SELECT Columns part
     */
    private String generateSelectPart(ListDTO listDTO) {

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
                .filter(x -> Pattern.compile("^SqlField\\('.+'\\)$").matcher(x.getEditor()).matches())
                .forEach(x -> {
                    String field = "( " +
                            x.getEditor().substring(10, x.getEditor().length() - 2) +
                            ") as " + x.getCode();
                    selectionFields.add(field);
                });

        return " SELECT " + String.join(",", selectionFields);
    }

    /*
     * Iterate to Generate SELECT Columns part
     */
    private String generateSelectLeftGroupPart(ListDTO listDTO) {

        String queryString = "SELECT ";
        List<String> selectParts = new ArrayList<>();

        listDTO.getListComponentLeftGroupFieldList()
                .stream()
                .forEach(x -> {
                    String selectPart =
                            x.getComponentPersistEntity().getCode() + "." +
                                    x.getComponentPersistEntityField().getPersistEntityField().getName() + " as " + x.getCode();
                    selectParts.add(selectPart);
                });
        selectParts.add("count(*) AS total");

        return "SELECT " + String.join(",", selectParts);
    }

    /*
     * Iterate to Generate FROM Tables & Relashionships part
     */
    private List<ComponentPersistEntityDTO> identifyFromPersistEntities(ListDTO listDTO) {

        Map<Long, ComponentPersistEntityDTO> persistEntitiesMap = new HashMap<>();
        //  List<String> componentPersistEntityCodes = new ArrayList<>();

        List<ListComponentFieldDTO> fields = new ArrayList<>();
        fields.addAll(listDTO.getListComponentColumnFieldList());
        fields.addAll(listDTO.getListComponentActionFieldList());
        fields.addAll(listDTO.getListComponentFilterFieldList());
        fields.addAll(listDTO.getListComponentLeftGroupFieldList());
        fields.addAll(listDTO.getListComponentOrderByFieldList());
        fields.addAll(listDTO.getListComponentTopGroupFieldList());

        fields
                .stream()
                .filter(x -> x.getComponentPersistEntity() != null)
                .forEach(x -> {
                    Long id = x.getComponentPersistEntity().getId();
                    persistEntitiesMap.put(id, x.getComponentPersistEntity());
                });

        persistEntitiesMap
                .forEach((k, v) -> {
                    this.identifyFromPersistEntitiesByJoins(v, persistEntitiesMap, listDTO);
                });

        return persistEntitiesMap.values().stream().collect(Collectors.toList());
    }

    /*
     * Iterate to Generate FROM Tables & Relashionships part
     */
    private Map<Long, ComponentPersistEntityDTO> identifyFromPersistEntitiesByJoins(ComponentPersistEntityDTO persistEntity,
                                                                                    Map<Long, ComponentPersistEntityDTO> persistEntitiesMap,
                                                                                    ListDTO listDTO) {

        List<String> componentPersistEntityCodes = new ArrayList<>();

        String selector = (persistEntity.getSelector() == null ? "" : persistEntity.getSelector());
        String selectorStatement = selector.replaceAll("\\[.+\\]", "");
        selectorStatement = selectorStatement.replaceAll("and/i", "");
        selectorStatement = selectorStatement.replaceAll("or/i", "");
        selectorStatement = selectorStatement.replaceAll("\\(", "");
        selectorStatement = selectorStatement.replaceAll("\\)", "");
        selectorStatement = selectorStatement.replaceAll(" {2,}", " ");
        String[] selectorStatementParts = selectorStatement.split(" ");

        selectorStatementParts =
                Arrays.stream(selectorStatementParts)
                        .filter(x -> x.contains("."))
                        .toArray(String[]::new);

        Arrays.stream(selectorStatementParts)
        .forEach(x -> {
            String[] joinParts = x.split("\\.");
            componentPersistEntityCodes.add(joinParts[0]);
        });

        listDTO.getComponent().getComponentPersistEntityList()
                .stream()
                .filter(x -> componentPersistEntityCodes.contains(x.getCode()))
                .filter(x -> !persistEntitiesMap.containsKey(x.getId()))
                .forEach(x -> {
                    persistEntitiesMap.put(x.getId(), x);
                    this.identifyFromPersistEntitiesByJoins(x, persistEntitiesMap, listDTO);
                });

        return persistEntitiesMap;
    }

    /*
     * Iterate to Generate FROM Tables & Relashionships part
     */
    private String generateFromPart(List<ComponentPersistEntityDTO> fromPersistEntities) {

        List<String> joinParts = new ArrayList<>();
        fromPersistEntities
                .stream()
                .sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder))
                .forEach(x -> {
                    String joinPart = "";

                    /* Join */
                    String selector = (x.getSelector() == null ? "" : x.getSelector());
                    String joinTypePart = selector.replaceAll("\\[|\\].+", "");
                    joinPart += " " + joinTypePart;

                    /* Table name */
                    if (x.getPersistEntity().getEntitytype().equals("AppView")) {
                        PersistEntityDTO appViewDTO = x.getPersistEntity();
                        joinPart += " ( " +
                                appViewDTO.getQuery() + " ) " + x.getCode();
                    } else {
                        joinPart += " " + x.getPersistEntity().getName() + " " + x.getCode();
                    }

                    /* On */
                    String onStatement = selector.replaceAll("\\[.+\\]", "");
                    if (!onStatement.equals("")) {
                        joinPart += " ON " + onStatement;
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
                        .filter(x -> (x.getFieldValue() == null ? "" : x.getFieldValue()).equals("") && x.getRequired())
                        .findFirst();

        if (optionalRequiredFieldEmpty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter "
                    + optionalRequiredFieldEmpty.get().getCode() + " should not be empty!");
        }

        filtersList =
                filtersList
                        .stream()
                        .filter(field -> field.getComponentPersistEntity() != null)
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
        filtersList.stream().forEach(x -> {
            String whereClause =
                    x.getComponentPersistEntity().getCode() + "." +
                            x.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
                            x.getOperator() + " :filter_" + x.getCode();

            whereClauseParts.add(whereClause);
        });

        if (whereClauseParts.size() == 0) {
            return "";
        } else {
            return " WHERE " + String.join(" AND ", whereClauseParts);
        }
    }

    /*
     * Iterate to Generate custom structure WHERE Fields part
     */
    private String generateCustomWhereColumnsPart(List<ListComponentFieldDTO> filtersList, String filterFieldStructure) {

        Map<String, String> whereClauseParts = new HashMap<>();

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
                .filter(x -> !(x.getFieldValue() == null ? "" : x.getFieldValue()).equals(""))
                .forEach(x -> {

                    String whereClause =
                            x.getComponentPersistEntity().getCode() + "." +
                                    x.getComponentPersistEntityField().getPersistEntityField().getName() + " " +
                                    x.getOperator() + " :filter_" + x.getCode();

                    whereClauseParts.put(x.getCode(), whereClause);
                });

        /* Replace Where in custom field srtucture */
        whereClauseParts.forEach((k, v) -> {
            filterFieldStructure.replaceAll("$" + k, v);
        });

        return filterFieldStructure;
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
            return "GROUP BY " + String.join(",", fields);
        }
    }

    /*
     * Iterate to Generate Order By Columns part
     */
    private String generateOrderByPart(ListDTO listDTO) {
        List<String> orderByParts = new ArrayList<>();
        listDTO.getListComponentOrderByFieldList().forEach(x -> {
            String orderByPart = x.getComponentPersistEntity().getCode() + "." +
                    x.getComponentPersistEntityField().getPersistEntityField().getName();

            if (x.getEditor().equals("ASC") || x.getEditor().equals("DESC")) {
                orderByPart += " " + x.getEditor();
            } else {
                orderByPart += " ASC";
            }

            orderByParts.add(orderByPart);
        });

        if (orderByParts.size() == 0) {
            return "";
        } else {
            return " ORDER BY " + String.join(",", orderByParts);
        }
    }

    /*
     * Iterate to Generate Order By Columns part
     */
    private String generateOrderByLeftGroupPart(ListDTO listDTO) {
        List<String> fields = new ArrayList<>();
        listDTO.getListComponentLeftGroupFieldList()
                .stream()
                .forEach(x -> {
                    fields.add(x.getCode() + " ASC ");
                });

        if (fields.size() == 0) {
            return "";
        } else {
            return " ORDER BY " + String.join(",", fields);
        }
    }

    /*
     * Iterate to Generate Limit part
     */
    private String generateLimitPart(ListDTO listDTO) {

        if ((listDTO.getHasPagination() == null ? false : listDTO.getHasPagination())) {
            Long currentPage = listDTO.getCurrentPage();
            if (currentPage == null) currentPage = 0L;
            Long offset = listDTO.getPageSize() * currentPage;

            return "LIMIT " + listDTO.getPageSize() + " OFFSET  " + offset;

        } else if (listDTO.getHasMaxSize()) {
            return "LIMIT " + listDTO.getMaxSize();
        }

        return "";
    }

    private Query generateCountQuery(ListDTO listDTO, String queryString) {
        Map<String, String> filterParts = new HashMap<>();
        Query query = entityManager.createNativeQuery(queryString);

        listDTO.getListComponentLeftGroupFieldList()
                .forEach(x -> {
                    x.setOperator("=");
                    x.setRequired(false);
                });

        List<ListComponentFieldDTO> filtersList = new ArrayList<>();
        filtersList.add((ListComponentFieldDTO) listDTO.getListComponentFilterFieldList());
        filtersList.add((ListComponentFieldDTO) listDTO.getListComponentLeftGroupFieldList());

        filtersList = filtersList
                .stream()
                .filter(field -> field.getComponentPersistEntity() != null)
                .collect(Collectors.toList());

        /* Iterate and create Where parts */
        filtersList.forEach(x -> {

            String filterPart = "";
            if (x.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(x.getFieldValue().toString());
                filterPart = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant);
            } else if (x.getType().equals("varchar") || x.getType().equals("text")) {
                if (x.getOperator().equals("like")) {
                    filterPart = x.getFieldValue().toString().replaceAll("\\*+", "%");
                } else {
                    filterPart = x.getFieldValue().toString();
                }
            } else {
                filterPart = x.getFieldValue().toString();
            }

            filterParts.put("filter_" + x.getCode(), filterPart);
        });

        filterParts.forEach((k, v) -> {
            query.setParameter(k, v);
        });

        return query;
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

        Map<String, String> filterParts = new HashMap<>();
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
                        .filter(field -> field.getComponentPersistEntity() != null)
                        .collect(Collectors.toList());

        /* Iterate and create Where parts */
        filtersList.forEach(x -> {

            String filterPart = "";
            if (x.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(x.getFieldValue().toString());
                filterPart = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant);
            } else if (x.getType().equals("varchar") || x.getType().equals("text")) {
                if (x.getOperator().equals("like")) {
                    filterPart = x.getFieldValue().toString().replaceAll("\\*+", "%");
                } else {
                    filterPart = x.getFieldValue().toString();
                }
            } else {
                filterPart = x.getFieldValue().toString();
            }

            filterParts.put("filter_" + x.getCode(), filterPart);
        });

        filterParts.forEach((k, v) -> {
            query.setParameter(k, v);
        });

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
                .filter(x -> Pattern.compile("^SqlField\\('.+'\\)$").matcher(x.getEditor()).matches())
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

    private Query generateGroupQuery(ListDTO listDTO, String queryString) {
        Map<String, String> filterParts = new HashMap<>();
        Query query = entityManager.createNativeQuery(queryString);

        List<ListComponentFieldDTO> filtersList = listDTO.getListComponentFilterFieldList()
                .stream()
                .filter(field -> field.getComponentPersistEntity() != null)
                .collect(Collectors.toList());

        /* Iterate and create Where parts */
        filtersList.forEach(x -> {
            String filterPart = "";
            if (x.getType().equals("datetime")) {
                Instant valueInstant = Instant.parse(x.getFieldValue().toString());
                filterPart = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(valueInstant);
            } else if (x.getType().equals("varchar") || x.getType().equals("text")) {
                if (x.getOperator().equals("like")) {
                    filterPart = x.getFieldValue().toString().replaceAll("\\*+", "%");
                } else {
                    filterPart = x.getFieldValue().toString();
                }
            } else {
                filterPart = x.getFieldValue().toString();
            }

            filterParts.put("filter_" + x.getCode(), filterPart);
        });

        filterParts.forEach((k, v) -> {
            query.setParameter(k, v);
        });

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
}
