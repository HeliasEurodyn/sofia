package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormDynamicQueryService {

    private final EntityManager entityManager;

    public FormDynamicQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void generateQueriesAndSave(ComponentDTO component, Map<String, Map<String, Object>> parameters) {

        List<ComponentPersistEntityDTO> savedPersistEntities = new ArrayList<>();

        /* Μaps parameters to component. The returning component contains also the parameters   */
        component = this.mapParametersToComponentDTO(component, parameters);

        /* Filter - Keep only Table, Saveable PersistEntities */
        List<ComponentPersistEntityDTO> filteredPersistEntityList =
                component.getComponentPersistEntityList().stream()
                        .filter(x -> x.getPersistEntity().getEntitytype().equals("Table"))
                        .filter(x -> x.getAllowSave())
                        .collect(Collectors.toList());

        /* Itterate & save */
        for (ComponentPersistEntityDTO componentPersistEntity : filteredPersistEntityList) {



            Query query = this.generateInsertQuery(componentPersistEntity);
            Long id = this.executeSave(query);

            savedPersistEntities.add(componentPersistEntity);
        }

    }

    private Query generateInsertQuery(ComponentPersistEntityDTO componentPersistEntity) {

        /* Insert into Section */
        String queryString = "INSERT INTO " + componentPersistEntity.getPersistEntity().getName();

        /* Insert into Values Section */
        List<String> headersList = componentPersistEntity.getComponentPersistEntityFieldList().stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .map(x -> x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String headersString = String.join(",", headersList);
        queryString += " (" + headersString + " ) VALUES ";

        /* Parameters Section */
        List<String> valuesParametersList = componentPersistEntity.getComponentPersistEntityFieldList().stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .map(x -> ":" + x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String valuesParametersString = String.join(",", valuesParametersList);
        queryString += " (" + valuesParametersString + " );";

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntity.getComponentPersistEntityFieldList().stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                (x.getValue() != null ? x.getValue().toString() : "")
                        ));

        return query;
    }


    Long executeSave(Query query) {
        Long id;
        try {
            query.executeUpdate();
            id = ((BigInteger) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).longValue();

        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        return id;
    }

    private ComponentDTO mapParametersToComponentDTO(ComponentDTO componentDTO, Map<String, Map<String, Object>> parameters) {

        for (Map.Entry persistEntityPair : parameters.entrySet()) {
            String persistEntityCode = (String) persistEntityPair.getKey();
            Map<String, Object> persistEntityFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            Optional<ComponentPersistEntityDTO> compPersistEntityOptional =
                    componentDTO.getComponentPersistEntityList()
                            .stream()
                            .filter(cpe -> cpe.getCode().equals(persistEntityCode)).findFirst();

            if (!compPersistEntityOptional.isPresent()) continue;
            ComponentPersistEntityDTO compPersistEntity = compPersistEntityOptional.get();

            for (Map.Entry persistEntityFieldPair : persistEntityFieldsMap.entrySet()) {
                String persistEntityFieldCode = (String) persistEntityFieldPair.getKey();
                Object persistEntityFieldValue = (Object) persistEntityFieldPair.getValue();

                Optional<ComponentPersistEntityFieldDTO> compPersistEntityFieldOptional =
                        compPersistEntity.getComponentPersistEntityFieldList()
                                .stream()
                                .filter(cpef -> cpef.getPersistEntityField().getName().equals(persistEntityFieldCode))
                                .findFirst();

                if (!compPersistEntityFieldOptional.isPresent()) continue;
                ComponentPersistEntityFieldDTO compPersistEntityField = compPersistEntityFieldOptional.get();

                compPersistEntityField.setValue(persistEntityFieldValue);

            }

        }

        return componentDTO;
    }


//
//    /*
//     * Iterate to Generate SELECT Columns part
//     */
//    private String generateSelectColumnsPart(ListDTO listDTO) {
//
//        String queryString = "SELECT ";
//        Boolean firstItteration = true;
//
//
//        List<ListComponentFieldDTO> fieldColumns = listDTO.getListComponentColumnFieldList()
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
//        List<ListComponentFieldDTO> sqlFieldColumns = listDTO.getListComponentColumnFieldList()
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
//     * Iterate to Generate SELECT Columns part
//     */
//    private String generateSelectLeftGroupPart(ListDTO listDTO) {
//
//        String queryString = "SELECT ";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : listDTO.getListComponentLeftGroupFieldList()) {
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
//        queryString += ", count(*) AS total";
//
//        return queryString;
//    }
//
//    /*
//     * Iterate to Generate FROM Tables & Relashionships part
//     */
//    private String generateFromColumnsPart(ListDTO listDTO) {
//
//        String queryString = " FROM";
//
//        List<ComponentPersistEntityDTO> sortedComponentPersistEntityList = listDTO.getComponent().getComponentPersistEntityList().stream().sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder)).collect(Collectors.toList());
//
//        for (ComponentPersistEntityDTO componentPersistEntityDTO : sortedComponentPersistEntityList) {
//
//            if (componentPersistEntityDTO.getSelector() != null) {
//                String selector = componentPersistEntityDTO.getSelector();
//                String join = selector.replaceAll("\\[|\\].+", "");
//                queryString += " " + join;
//            }
//
//            if (componentPersistEntityDTO.getPersistEntity().getEntitytype().equals("AppView")) {
//                PersistEntityDTO appViewDTO = componentPersistEntityDTO.getPersistEntity();
//                queryString += " ( " +
//                        appViewDTO.getQuery() + " ) " + componentPersistEntityDTO.getCode();
//            } else {
//                queryString += " " +
//                        componentPersistEntityDTO.getPersistEntity().getName() + " " + componentPersistEntityDTO.getCode();
//            }
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
//    private String generateWhereColumnsPart(List<ListComponentFieldDTO> filtersList) {
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
//    private String generateCustomWhereColumnsPart(List<ListComponentFieldDTO> filtersList, String filterFieldStructure) {
//
//        String queryString = " WHERE " + filterFieldStructure;
//
//        for (ListComponentFieldDTO listComponentFieldDTO : filtersList) {
//
//            if (listComponentFieldDTO.getFieldValue() == null) listComponentFieldDTO.setFieldValue("");
//            if (listComponentFieldDTO.getFieldValue().equals("")) {
//                if (listComponentFieldDTO.getRequired()) {
//                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Required filter " + listComponentFieldDTO.getCode() + " should not be empty!");
//                }
//
//                queryString.replaceAll("$" + listComponentFieldDTO.getCode(), " 1=1 ");
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
//     * Iterate to Generate Group By Columns part
//     */
//    private String generateGroupByColumnsPart(ListDTO listDTO) {
//        String queryString = "GROUP BY ";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : listDTO.getListComponentLeftGroupFieldList()) {
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
//        return queryString;
//    }
//
//    /*
//     * Iterate to Generate Order By Columns part
//     */
//    private String generateOrderByColumnsPart(ListDTO listDTO) {
//        String queryString = "";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : listDTO.getListComponentOrderByFieldList()) {
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
//     * Iterate to Generate Order By Columns part
//     */
//    private String generateOrderByLeftGroupPart(ListDTO listDTO) {
//        String queryString = " ORDER BY ";
//        Boolean firstItteration = true;
//        for (ListComponentFieldDTO listComponentFieldDTO : listDTO.getListComponentLeftGroupFieldList()) {
//
//            if (firstItteration) {
//                queryString += listComponentFieldDTO.getCode() + " ASC ";
//            } else {
//                queryString += "," + listComponentFieldDTO.getCode() + " ASC ";
//            }
//
//            firstItteration = false;
//        }
//        return queryString;
//    }
//
//
//    /*
//     * Iterate to Generate Limit part
//     */
//    private String generateLimitPart(ListDTO listDTO) {
//
//        String queryString = "";
//        if (listDTO.getHasPagination()) {
//            Long currentPage = listDTO.getCurrentPage();
//            if (currentPage == null) currentPage = 0L;
//            Long offset = listDTO.getPageSize() * currentPage;
//            queryString += "LIMIT " + listDTO.getPageSize() + " OFFSET  " + offset;
//        } else if (listDTO.getHasMaxSize()) {
//            queryString += "LIMIT " + listDTO.getMaxSize();
//        }
//        return queryString;
//    }
//
//
//
//    /*
//     * Execute Sql Query
//     * Set results in a HashMap Array
//     * return HashMap Array results
//     */
//    private List<Map<String, Object>> executeListQuery(ListDTO listDTO, String queryString) {
//
//        List<Map<String, Object>> listContent = new ArrayList<>();
//
//        List<ListComponentFieldDTO> fieldColumns = listDTO.getListComponentColumnFieldList()
//                .stream().filter(
//                        fieldColumn -> fieldColumn.getComponentPersistEntityField() != null).collect(Collectors.toList());
//
//        Pattern pattern = Pattern.compile("^SqlField\\('.+'\\)$");
//        List<ListComponentFieldDTO> sqlFieldColumns = listDTO.getListComponentColumnFieldList()
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
//
//    /*
//     * Execute algorithm
//     * Set results in a HashMap Array
//     * return HashMap Array results
//     */
//    private List<GroupEntryDTO> executeGroupQuery(ListDTO listDTO, String queryString) {
//
//        Query query = entityManager.createNativeQuery(queryString);
//        List<Object[]> dataList = query.getResultList();
//
//        int countIndex = listDTO.getListComponentLeftGroupFieldList().size();
//        List<GroupEntryDTO> groupEntries = new ArrayList<>();
//        int i = 0;
//
//        for (Object[] dataRow : dataList) {
//
//            List<GroupEntryDTO> currentGroupEntries = groupEntries;
//            GroupEntryDTO parrentEntry = null;
//
//            for (ListComponentFieldDTO listComponentFieldDTO : listDTO.getListComponentLeftGroupFieldList()) {
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
//
//                    if (parrentEntry != null) {
//                        GroupEntryDTO abstructParrent = new GroupEntryDTO();
//                        abstructParrent.setCode(parrentEntry.getCode());
//                        abstructParrent.setValue(parrentEntry.getValue());
//                        abstructParrent.setParrent(parrentEntry.getParrent());
//                        entry.setParrent(abstructParrent);
//                    }
//
//                    currentGroupEntries.add(entry);
//                }
//
//                currentGroupEntries = entry.getChildren();
//                parrentEntry = entry;
//                i++;
//            }
//            i = 0;
//        }
//
//        return groupEntries;
//    }
//
//    public List<Map<String, Object>> executeListAndGetData(ListDTO dto) {
//        List<Map<String, Object>> listContent = new ArrayList<>();
//
//        if (dto.getListComponentColumnFieldList().size() == 0) {
//            return listContent;
//        }
//
//        /*
//         * Select clause
//         */
//        String queryString = this.generateSelectColumnsPart(dto);
//
//        /*
//         * From clause
//         */
//        queryString += this.generateFromColumnsPart(dto);
//
//        /*
//         * Where clause
//         */
//        for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentLeftGroupFieldList()) {
//            listComponentFieldDTO.setOperator("=");
//            listComponentFieldDTO.setRequired(false);
//        }
//
//        List<ListComponentFieldDTO> filtersList = Stream.concat(dto.getListComponentFilterFieldList().stream(),
//                dto.getListComponentLeftGroupFieldList().stream())
//                .collect(Collectors.toList());
//
//        filtersList = Stream.concat(filtersList.stream(),
//                dto.getListComponentColumnFieldList().stream())
//                .collect(Collectors.toList());
//
//        filtersList = filtersList.stream()
//                .filter(field -> field.getComponentPersistEntity() != null).collect(Collectors.toList());
//
//        if (!dto.getCustomFilterFieldStructure())
//            queryString += this.generateWhereColumnsPart(filtersList);
//        if (dto.getCustomFilterFieldStructure())
//            queryString += this.generateCustomWhereColumnsPart(filtersList, dto.getFilterFieldStructure());
//
//        /*
//         * Order By clause
//         */
//        queryString += this.generateOrderByColumnsPart(dto);
//
//        /*
//         * Limit clause
//         */
//        queryString += this.generateLimitPart(dto);
//
//        /*
//         * Execute
//         */
//        listContent = this.executeListQuery(dto, queryString);
//
//        return listContent;
//    }
//
//    public List<GroupEntryDTO> executeListAndGetGroupingData(ListDTO dto) {
//        List<GroupEntryDTO> groupContent = new ArrayList<>();
//        // ListComponentDTO listComponentDTO = dto.getListComponentList().get(0);
//
//        if (dto.getListComponentLeftGroupFieldList().size() == 0) {
//            return groupContent;
//        }
//
//        /*
//         * Select clause
//         */
//        String queryString = this.generateSelectLeftGroupPart(dto);
//
//        /*
//         * From clause
//         */
//        queryString += this.generateFromColumnsPart(dto);
//
//        /*
//         * Where clause
//         */
//        List<ListComponentFieldDTO> filtersList = dto.getListComponentFilterFieldList().stream()
//                .filter(field -> field.getComponentPersistEntity() != null).collect(Collectors.toList());
//
//        if (!dto.getCustomFilterFieldStructure())
//            queryString += this.generateWhereColumnsPart(filtersList);
//        if (dto.getCustomFilterFieldStructure())
//            queryString += this.generateCustomWhereColumnsPart(filtersList, dto.getFilterFieldStructure());
//
//        /*
//         * Group By clause
//         */
//        queryString += this.generateGroupByColumnsPart(dto);
//
//        /*
//         * Order By clause
//         */
//        queryString += this.generateOrderByLeftGroupPart(dto);
//
//        /*
//         * Execute
//         */
//        groupContent = this.executeGroupQuery(dto, queryString);
//
//        return groupContent;
//    }
//
//    public Long executeListAndCountTotalRows(ListDTO dto) {
//
//        //    ListComponentDTO listComponentDTO = dto.getListComponentList().get(0);
//
//        if (dto.getListComponentColumnFieldList().size() == 0) {
//            return 0L;
//        }
//
//        /*
//         * Select clause
//         */
//        String queryString = "SELECT COUNT(*) AS TOTALROWS ";
//
//        /*
//         * From clause
//         */
//        queryString += this.generateFromColumnsPart(dto);
//
//
//        /*
//         * Where clause
//         */
//        for (ListComponentFieldDTO listComponentFieldDTO : dto.getListComponentLeftGroupFieldList()) {
//            listComponentFieldDTO.setOperator("=");
//            listComponentFieldDTO.setRequired(false);
//        }
//
//        List<ListComponentFieldDTO> filtersList = Stream.concat(dto.getListComponentFilterFieldList().stream(),
//                dto.getListComponentLeftGroupFieldList().stream())
//                .collect(Collectors.toList());
//        filtersList = filtersList.stream()
//                .filter(field -> field.getComponentPersistEntity() != null).collect(Collectors.toList());
//
//        if (!dto.getCustomFilterFieldStructure())
//            queryString += this.generateWhereColumnsPart(filtersList);
//        if (dto.getCustomFilterFieldStructure())
//            queryString += this.generateCustomWhereColumnsPart(filtersList, dto.getFilterFieldStructure());
//
//
//
//        /*
//         * Execute
//         */
//        Long totalRows = this.executeCountQuery(dto, queryString);
//
//        return totalRows;
//    }

}
