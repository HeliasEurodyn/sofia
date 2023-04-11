package com.crm.sofia.native_repository.component;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.model.expression.ExprResponse;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.expression.ExpressionService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ComponentSaverNativeRepository {

    private final EntityManager entityManager;

    private final ExpressionService expressionService;
    private final PasswordEncoder passwordEncoder;

    private final ComponentQueryStringGenerator componentQueryStringGenerator;
    private final JWTService jwtService;

    public ComponentSaverNativeRepository(EntityManager entityManager,
                                          ExpressionService expressionService,
                                          PasswordEncoder passwordEncoder,
                                          ComponentQueryStringGenerator componentQueryStringGenerator,
                                          JWTService jwtService) {
        this.entityManager = entityManager;
        this.expressionService = expressionService;
        this.passwordEncoder = passwordEncoder;
        this.componentQueryStringGenerator = componentQueryStringGenerator;
        this.jwtService = jwtService;
    }

    @Transactional(rollbackFor = Exception.class)
    public String save(ComponentDTO componentDTO) {
        return this.generateQueriesAndSave(componentDTO.getComponentPersistEntityList(), new ArrayList<>());
    }

    private String generateQueriesAndSave(
            List<ComponentPersistEntityDTO> componentPersistEntityList,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Itterate & save */
        for (ComponentPersistEntityDTO componentPersistEntity : componentPersistEntityList) {
            Boolean multiDataLine = (componentPersistEntity.getMultiDataLine() != null && componentPersistEntity.getMultiDataLine());
            if (multiDataLine) {
                Boolean saved = this.saveMultilineComponentPersistEntity(componentPersistEntity, savedPersistEntities);
                if (!saved)
                    this.updateJoinsOnMultilineComponentPersistEntity(componentPersistEntity, savedPersistEntities);
            } else {
                this.saveComponentPersistEntity(componentPersistEntity, savedPersistEntities);
                savedPersistEntities.add(componentPersistEntity);

                if (componentPersistEntity.getComponentPersistEntityList() != null) {
                    this.generateQueriesAndSave(componentPersistEntity.getComponentPersistEntityList(), savedPersistEntities);
                }
            }
        }

        /* Retrieve and return created id */
        if (savedPersistEntities.size() == 0) {
            return "";
        }

        String id = savedPersistEntities.get(0).getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() != null && x.getPersistEntityField().getPrimaryKey()) == true)
                .map(x -> (x.getValue() == null ? "0" : x.getValue().toString())).findFirst()
                .orElse("0");

        return id;
    }

    private boolean saveMultilineComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
                                                        List<ComponentPersistEntityDTO> savedPersistEntities) {

        Boolean allowSave = (componentPersistEntity.getAllowSave() == null? false : componentPersistEntity.getAllowSave());

        if (!allowSave) {
            return false;
        }

        List<String> existingPrimaryKeys = new ArrayList<>();
        List<ComponentPersistEntityDataLineDTO> updatableLines = new ArrayList<>();
        List<ComponentPersistEntityDataLineDTO> insertableLines = new ArrayList<>();

        /* Separate lines for insert, update, delete Section */
        componentPersistEntity.getComponentPersistEntityDataLines()
                .forEach(componentPersistEntityDataLine -> {
                    Optional<ComponentPersistEntityFieldDTO> optionalComponentPersistEntityField =
                            componentPersistEntityDataLine.getComponentPersistEntityFieldList().stream()
                                    .filter(y -> y.getPersistEntityField().getPrimaryKey() == true)
                                    .filter(y -> y.getValue() != null).findFirst();

                    if (optionalComponentPersistEntityField.isPresent()) {
                        updatableLines.add(componentPersistEntityDataLine);
                        existingPrimaryKeys.add("'" + optionalComponentPersistEntityField.get().getValue().toString() + "'");
                    } else {
                        insertableLines.add(componentPersistEntityDataLine);
                    }
                });

        /*  Delete Section */
        String primaryKeyName = componentPersistEntity.getComponentPersistEntityFieldList()
                .stream()
                .filter(y -> y.getPersistEntityField().getPrimaryKey() == true).findFirst()
                .get().getPersistEntityField().getName();


        if (componentPersistEntity.getDeleteType().equals("delete")
                && componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")
                && (componentPersistEntity.getAllowSave() != null && componentPersistEntity.getAllowSave())
        ) {
            this.deleteNotExistingComponentPersistEntity(
                    existingPrimaryKeys,
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities,
                    primaryKeyName
            );
        } else if (componentPersistEntity.getDeleteType().equals("clearJoin")
                && componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")
                && (componentPersistEntity.getAllowSave() != null && componentPersistEntity.getAllowSave())) {
            this.unjoinNotExistingComponentPersistEntity(
                    existingPrimaryKeys,
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities,
                    primaryKeyName
            );
        }

        /*  Update Section */
        updatableLines.forEach(componentPersistEntityDataLine -> {

            if (componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")
                    && (componentPersistEntity.getAllowSave() != null && componentPersistEntity.getAllowSave())) {
                this.updateComponentPersistEntity(
                        componentPersistEntity,
                        componentPersistEntityDataLine.getComponentPersistEntityFieldList(),
                        savedPersistEntities);
            }

            List<ComponentPersistEntityDTO> lineSavedPersistEntities = new ArrayList<>();
            lineSavedPersistEntities.addAll(savedPersistEntities);

            ComponentPersistEntityDTO componentPersistEntityLine = new ComponentPersistEntityDTO();
            componentPersistEntityLine.setComponentPersistEntityFieldList(componentPersistEntityDataLine.getComponentPersistEntityFieldList());
            componentPersistEntityLine.setCode(componentPersistEntity.getCode());
            componentPersistEntityLine.setPersistEntity(componentPersistEntity.getPersistEntity());
            lineSavedPersistEntities.add(componentPersistEntityLine);

            if (componentPersistEntity.getComponentPersistEntityList() != null) {
                this.generateQueriesAndSave(componentPersistEntityDataLine.getComponentPersistEntityList(), lineSavedPersistEntities);
            }
        });

        /*  Insert Section */
        insertableLines.forEach(componentPersistEntityDataLine -> {

            if (componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")
                    && (componentPersistEntity.getAllowSave() != null && componentPersistEntity.getAllowSave())) {
                this.insertComponentPersistEntity(
                        componentPersistEntity,
                        componentPersistEntityDataLine.getComponentPersistEntityFieldList(),
                        savedPersistEntities);
            }

            List<ComponentPersistEntityDTO> lineSavedPersistEntities = new ArrayList<>();
            lineSavedPersistEntities.addAll(savedPersistEntities);
            // lineSavedPersistEntities.add(componentPersistEntity);
            ComponentPersistEntityDTO componentPersistEntityLine = new ComponentPersistEntityDTO();
            componentPersistEntityLine.setComponentPersistEntityFieldList(componentPersistEntityDataLine.getComponentPersistEntityFieldList());
            componentPersistEntityLine.setPersistEntity(componentPersistEntity.getPersistEntity());
            componentPersistEntityLine.setCode(componentPersistEntity.getCode());
            lineSavedPersistEntities.add(componentPersistEntityLine);

            if (componentPersistEntity.getComponentPersistEntityList() != null) {
                this.generateQueriesAndSave(componentPersistEntityDataLine.getComponentPersistEntityList(), lineSavedPersistEntities);
            }
        });

        return true;
    }

    private ComponentPersistEntityDTO updateJoinsOnMultilineComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
                                                                                   List<ComponentPersistEntityDTO> savedPersistEntities) {
        String deleteType = (componentPersistEntity.getDeleteType() == null ? "" : componentPersistEntity.getDeleteType());

        if (!deleteType.equals("clearJoin")) {
            return componentPersistEntity;
        }

        List<String> existingPrimaryKeys = new ArrayList<>();
        List<ComponentPersistEntityDataLineDTO> updatableLines = new ArrayList<>();
        //  List<ComponentPersistEntityDataLineDTO> insertableLines = new ArrayList<>();

        /* Separate lines for update, delete Section */
        componentPersistEntity.getComponentPersistEntityDataLines()
                .forEach(componentPersistEntityDataLine -> {
                    Optional<ComponentPersistEntityFieldDTO> optionalComponentPersistEntityField =
                            componentPersistEntityDataLine.getComponentPersistEntityFieldList().stream()
                                    .filter(y -> y.getPersistEntityField().getPrimaryKey() == true)
                                    .filter(y -> y.getValue() != null).findFirst();

                    if (optionalComponentPersistEntityField.isPresent()) {
                        updatableLines.add(componentPersistEntityDataLine);
                        existingPrimaryKeys.add("'" + optionalComponentPersistEntityField.get().getValue().toString() + "'");
                    }

                });

        /*  Delete Section */
        String primaryKeyName = componentPersistEntity.getComponentPersistEntityFieldList()
                .stream()
                .filter(y -> y.getPersistEntityField().getPrimaryKey() == true).findFirst()
                .get().getPersistEntityField().getName();


        if (componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")) {
            this.unjoinNotExistingComponentPersistEntity(
                    existingPrimaryKeys,
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities,
                    primaryKeyName
            );
        }

        /*  Update Section */
        updatableLines.forEach(componentPersistEntityDataLine -> {

            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList
                    = componentPersistEntityDataLine.getComponentPersistEntityFieldList().stream()
                    .filter(y -> (y.getPersistEntityField().getPrimaryKey() != null && y.getPersistEntityField().getPrimaryKey()) ||
                            !(y.getLocateStatement() == null ? "" : y.getLocateStatement()).equals(""))
                    .collect(Collectors.toList());


            if (componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")) {
                this.updateComponentPersistEntity(
                        componentPersistEntity,
                        componentPersistEntityFieldList,
                        savedPersistEntities);
            }

            List<ComponentPersistEntityDTO> lineSavedPersistEntities = new ArrayList<>();
            lineSavedPersistEntities.addAll(savedPersistEntities);

            ComponentPersistEntityDTO componentPersistEntityLine = new ComponentPersistEntityDTO();
            componentPersistEntityLine.setComponentPersistEntityFieldList(componentPersistEntityDataLine.getComponentPersistEntityFieldList());
            componentPersistEntityLine.setCode(componentPersistEntity.getCode());
            componentPersistEntityLine.setPersistEntity(componentPersistEntity.getPersistEntity());
            lineSavedPersistEntities.add(componentPersistEntityLine);

            if (componentPersistEntity.getComponentPersistEntityList() != null) {
                this.generateQueriesAndSave(componentPersistEntityDataLine.getComponentPersistEntityList(), lineSavedPersistEntities);
            }
        });

        return componentPersistEntity;
    }

    private List<ComponentPersistEntityFieldDTO> updateComponentPersistEntity(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateUpdateQuery(componentPersistEntity, componentPersistEntityFieldList);

        /* Execute Query */
        if (query != null) {
            this.executeSave(query);
        }

        return componentPersistEntityFieldList;
    }

    private Query generateUpdateQuery(ComponentPersistEntityDTO componentPersistEntity,
                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Set Default updated dates-users row log */
        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getPersistEntityField().getName().equals("modified_on"))
                .forEach(x -> x.setValue(Instant.now()));

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getPersistEntityField().getName().equals("modified_by"))
                .filter(y -> y.getPersistEntityField().getType().equals("varchar"))
                .forEach(x -> x.setValue(this.jwtService.getUserId()));

        /* Retrieve Cachable Query Parts Map */
        Map<String, String> queryMap = this.componentQueryStringGenerator.generateUpdateCacheable(componentPersistEntity, componentPersistEntityFieldList);

        String queryUpdateString = String.join(
                " ", "UPDATE", componentPersistEntity.getPersistEntity().getName());

        List<String> setFields = componentPersistEntityFieldList.stream()
                .filter(x -> !x.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .map(x -> queryMap.get(x.getPersistEntityField().getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        String querySetString = String.join(" ", "SET", String.join(", ", setFields));

        String queryWhereString = String.join(" ", "WHERE", queryMap.get("WHERE"));

        String queryString = String.join(" ", queryUpdateString, querySetString, queryWhereString);

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntityFieldList.stream()
                .filter(x -> x.getValue() != null)
                .filter(x -> !x.getPersistEntityField().getType().equals("password"))
                .filter(x -> !x.getPersistEntityField().getType().equals("datetime"))
                .filter(x -> !x.getPersistEntityField().getType().equals("datetime_det"))
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                x.getValue()
                        ));

        componentPersistEntityFieldList.stream()
                .filter(x -> x.getValue() != null)
                .filter(x -> x.getPersistEntityField().getType().equals("password"))
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                passwordEncoder.encode(x.getValue().toString())
                        ));

        componentPersistEntityFieldList.stream()
                .filter(x -> x.getValue() != null)
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> Arrays.asList("datetime", "datetime_det").contains(x.getPersistEntityField().getType()))
                .forEach(x -> {
                    Instant valueInstant = null;
                    if (!(x.getValue() == null ? "" : x.getValue().toString()).equals("")) {
                        valueInstant = OffsetDateTime.parse(x.getValue().toString()).toInstant();
                    }
                    query.setParameter(
                            x.getPersistEntityField().getName(),
                            valueInstant
                    );
                });

        return query;
    }

    private Query generateInsertQuery(ComponentPersistEntityDTO componentPersistEntity,
                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Set Default created-updated date-user log fields */
        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> Arrays.asList("created_on", "modified_on").contains(x.getPersistEntityField().getName()))
                .forEach(x -> x.setValue(Instant.now()));

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> Arrays.asList("created_by", "modified_by")
                        .contains(x.getPersistEntityField().getName()))
                .filter(y -> y.getPersistEntityField().getType().equals("varchar"))
                .forEach(x -> x.setValue(this.jwtService.getUserId()));

        String queryString = componentQueryStringGenerator.generateInsertCacheable(componentPersistEntity, componentPersistEntityFieldList);

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                //  .filter(x -> x.getValue() != null)
                .filter(x -> !x.getPersistEntityField().getType().equals("password"))
                .filter(x -> !x.getPersistEntityField().getType().equals("datetime"))
                .filter(x -> !x.getPersistEntityField().getType().equals("datetime_det"))
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                (x.getValue() == null ? "" : x.getValue())
                        ));

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                //.filter(x -> x.getValue() != null)
                .filter(x -> x.getPersistEntityField().getType().equals("password"))
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                (x.getValue() == null ? "" : passwordEncoder.encode(x.getValue().toString()))
                        ));

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
//                .filter(x -> x.getValue() != null)
                .filter(x -> Arrays.asList("datetime", "datetime_det").contains(x.getPersistEntityField().getType()))
                .forEach(x -> {
                    Instant valueInstant = null;
                    if (!(x.getValue() == null ? "" : x.getValue().toString()).equals("")) {
                        valueInstant = Instant.parse(x.getValue().toString());
                    }
                    query.setParameter(
                            x.getPersistEntityField().getName(),
                            valueInstant
                    );
                });

        return query;
    }

//    public String generateInsertQueryString(String entityName,
//                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {
//
//        /* Insert into Section */
//        String queryString = "INSERT INTO " + entityName;
//
//        /* Set Default created-updated date-user log fields */
//        componentPersistEntityFieldList.stream()
//                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
//                .filter(x -> Arrays.asList("created_on", "modified_on").contains(x.getPersistEntityField().getName()))
//                .forEach(x -> x.setValue(Instant.now()));
//
//        componentPersistEntityFieldList.stream()
//                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
//                .filter(x -> Arrays.asList("created_by", "modified_by")
//                        .contains(x.getPersistEntityField().getName()))
//                .filter(y -> y.getPersistEntityField().getType().equals("varchar"))
//                .forEach(x -> x.setValue(this.jwtService.getUserId()));
//
//        /* Insert into Values Section */
//        List<String> headersList = componentPersistEntityFieldList.stream()
//                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
//                .filter(x -> x.getValue() != null)
//                .map(x -> x.getPersistEntityField().getName())
//                .collect(Collectors.toList());
//        String headersString = String.join(",", headersList);
//        queryString += " (" + headersString + " ) VALUES ";
//
//        /* Parameters Section */
//        List<String> valuesParametersList = componentPersistEntityFieldList.stream()
//                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
//                .filter(x -> x.getValue() != null)
//                .map(x -> ":" + x.getPersistEntityField().getName())
//                .collect(Collectors.toList());
//        String valuesParametersString = String.join(",", valuesParametersList);
//        queryString += " (" + valuesParametersString + " );";
//
//        return queryString;
//    }

    private Object executeSave(Query query) {
        Object id;
        try {
            log.debug(query.toString());
            query.executeUpdate();
            id = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        return id;
    }

    private List<ComponentPersistEntityFieldDTO> mapSavedValuesToComponentPersistEntity(List<ComponentPersistEntityDTO> savedPersistEntities,
                                                                                        List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        for (ComponentPersistEntityDTO savedPersistEntity : savedPersistEntities) {
            for (ComponentPersistEntityFieldDTO savedComponentPersistEntityField : savedPersistEntity.getComponentPersistEntityFieldList()) {

                String currentFieldCode = "#" + savedPersistEntity.getCode() + "." + savedComponentPersistEntityField.getPersistEntityField().getName();

                for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntityFieldList) {
                    String saveStatement = (componentPersistEntityField.getSaveStatement() == null ? "" : componentPersistEntityField.getSaveStatement());
                    if (saveStatement.equals(currentFieldCode)) {
                        componentPersistEntityField.setValue(savedComponentPersistEntityField.getValue());
                    }
                }

            }
        }
        return componentPersistEntityFieldList;
    }

    private List<ComponentPersistEntityFieldDTO> insertComponentPersistEntity(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Run On Save Expressions */
        this.runOnSaveExpressions(componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateInsertQuery(componentPersistEntity, componentPersistEntityFieldList);

        log.debug(query.unwrap(org.hibernate.Query.class).getQueryString());

        /* Execute Query */
        Object id = this.executeSave(query);

        /* Set Id to component */
        componentPersistEntityFieldList
                .stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey() == true)
                .filter(x -> x.getPersistEntityField().getAutoIncrement() == true)
                .forEach(x -> x.setValue(id));

        return componentPersistEntityFieldList;
    }

    private void saveComponentPersistEntity(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        if (!componentPersistEntity.getPersistEntity().getEntitytype().equals("Table")) {
            return;
        }

        if (!(componentPersistEntity.getAllowSave() != null && componentPersistEntity.getAllowSave())) {
            return;
        }

        Boolean hasPrimaryKeyValue = this.hasPrimaryKeyValue(componentPersistEntity);

        if (hasPrimaryKeyValue) {
            this.updateComponentPersistEntity(
                    componentPersistEntity,
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities);
        } else {
            this.insertComponentPersistEntity(
                    componentPersistEntity,
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities);
        }
    }

    private void deleteNotExistingComponentPersistEntity(List<String> existingPrimaryKeys,
                                                         String entityName,
                                                         List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                         List<ComponentPersistEntityDTO> savedPersistEntities,
                                                         String primaryKeyName) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateDeteleOtherThanExistingKeysQuery(entityName, existingPrimaryKeys,
                componentPersistEntityFieldList, primaryKeyName);

        /* Execute Query */
        this.executeDelete(query);
    }

    private void unjoinNotExistingComponentPersistEntity(List<String> existingPrimaryKeys,
                                                         String entityName,
                                                         List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                         List<ComponentPersistEntityDTO> savedPersistEntities,
                                                         String primaryKeyName) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateUnjoinOtherThanExistingKeysQuery(entityName, existingPrimaryKeys,
                componentPersistEntityFieldList, primaryKeyName);

        /* Execute Query */
        this.executeDelete(query);
    }

    private Query generateUnjoinOtherThanExistingKeysQuery(String entityName,
                                                           List<String> existingPrimaryKeys,
                                                           List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                           String primaryKeyName) {

        /* UPDATE Section */
        List<String> columnParametersList = new ArrayList<>();
        String queryString = "UPDATE " + entityName + " SET ";

        List<ComponentPersistEntityFieldDTO> saveStatementFieldList = componentPersistEntityFieldList.stream()
                .filter(x -> !(x.getSaveStatement() == null ? "" : x.getSaveStatement()).equals(""))
                .collect(Collectors.toList());

        saveStatementFieldList
                .forEach(saveStatementField -> {
                    columnParametersList.add(
                            saveStatementField.getPersistEntityField().getName() + " = NULL "
                    );
                });
        queryString += String.join(",", columnParametersList);

        /* WHERE Section */
        queryString += " WHERE ";
        List<String> whereParametersList = new ArrayList<>();

        if (existingPrimaryKeys.size() > 0) {
            whereParametersList.add(
                    primaryKeyName + " NOT IN (" + String.join(",", existingPrimaryKeys) + ") "
            );
        }

        saveStatementFieldList
                .forEach(saveStatementField -> {
                    whereParametersList.add(
                            saveStatementField.getPersistEntityField().getName() + " = :"
                                    + saveStatementField.getPersistEntityField().getName() + " "
                    );
                });

        queryString += String.join(" AND ", whereParametersList);

        /* Query creation Section */
        Query query = entityManager.createNativeQuery(queryString);

        /* Parameters Replacement Section */
        saveStatementFieldList.stream()
                .filter(x -> x.getValue() != null)
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                x.getValue()
                        ));

        return query;
    }

    private Query generateDeteleOtherThanExistingKeysQuery(String entityName,
                                                           List<String> existingPrimaryKeys,
                                                           List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                           String primaryKeyName) {

        /* DELETE FROM WHERE Section */
        String queryString = "DELETE FROM " + entityName + " WHERE ";

        /* WHERE Section */
        List<String> whereParametersList = new ArrayList<>();

        if (existingPrimaryKeys.size() > 0) {
            whereParametersList.add(
                    primaryKeyName + " NOT IN (" + String.join(",", existingPrimaryKeys) + ") "
            );
        }

        List<ComponentPersistEntityFieldDTO> saveStatementFieldList = componentPersistEntityFieldList.stream()
                .filter(x -> !(x.getSaveStatement() == null ? "" : x.getSaveStatement()).equals(""))
                .collect(Collectors.toList());

        saveStatementFieldList
                .forEach(saveStatementField -> {
                    whereParametersList.add(
                            saveStatementField.getPersistEntityField().getName() + " = :"
                                    + saveStatementField.getPersistEntityField().getName() + " "
                    );
                });

        queryString += String.join(" AND ", whereParametersList);

        /* Query creation Section */
        Query query = entityManager.createNativeQuery(queryString);

        /* Parameters Replacement Section */
        saveStatementFieldList.stream()
                .filter(x -> x.getValue() != null)
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                x.getValue()
                        ));

        return query;
    }

    private void executeDelete(Query query) {
        try {
            query.executeUpdate();

        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public Boolean hasPrimaryKeyValue(ComponentPersistEntityDTO componentPersistEntity) {

        if (componentPersistEntity == null) {
            return false;
        }

        Optional<ComponentPersistEntityFieldDTO> optionalComponentPersistEntityFieldDTO =
                componentPersistEntity.getComponentPersistEntityFieldList()
                        .stream()
                        .filter(componentPersistEntityField -> componentPersistEntityField.getPersistEntityField().getPrimaryKey() == true).findFirst();

        if (!optionalComponentPersistEntityFieldDTO.isPresent()) {
            return false;
        }

        ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO = optionalComponentPersistEntityFieldDTO.get();

        return componentPersistEntityFieldDTO.getValue() != null;
    }

    private void runOnSaveExpressions(List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {
        componentPersistEntityFieldList
                .stream()
                .filter(cpef -> cpef.getPersistEntityField() != null)
                .filter(cpef -> cpef.getPersistEntityField().getOnSaveValue() != null)
                .filter(cpef -> !cpef.getPersistEntityField().getOnSaveValue().equals(""))
                .forEach(cpef -> {
                    ExprResponse exprResponse = expressionService.createCacheable(cpef.getPersistEntityField().getOnSaveValue(), cpef.getPersistEntityField().getId());
                    if (!exprResponse.getError()) {
                        Object fieldValue = expressionService.getResult(exprResponse);
                        cpef.setValue(fieldValue);
                    }
                });
    }

}
