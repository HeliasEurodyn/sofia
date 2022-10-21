package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormDynamicQueryService {

    private final EntityManager entityManager;

    public FormDynamicQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public String generateQueriesAndSave(
            List<ComponentPersistEntityDTO> componentPersistEntityList, List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Filter - Keep only Table, Saveable PersistEntities */
        List<ComponentPersistEntityDTO> filteredPersistEntityList =
                componentPersistEntityList
                        .stream()
                        .filter(x -> x.getPersistEntity().getEntitytype().equals("Table"))
                        .filter(x -> (x.getAllowSave() == null ? false : x.getAllowSave()))
                        .collect(Collectors.toList());

        if (filteredPersistEntityList.size() == 0) {
            return "0";
        }

        /* Itterate & save */
        for (ComponentPersistEntityDTO componentPersistEntity : filteredPersistEntityList) {
            Boolean multiDataLine = (componentPersistEntity.getMultiDataLine() == null ? false : componentPersistEntity.getMultiDataLine());
            if (multiDataLine == true) {
                this.saveMultilineComponentPersistEntity(componentPersistEntity, savedPersistEntities);
            } else {
                this.saveComponentPersistEntity(componentPersistEntity, savedPersistEntities);
                savedPersistEntities.add(componentPersistEntity);

                if (componentPersistEntity.getComponentPersistEntityList() != null) {
                    this.generateQueriesAndSave(componentPersistEntity.getComponentPersistEntityList(), savedPersistEntities);
                }
            }


        }

        /* Retrieve and return created id */
        String id = filteredPersistEntityList.get(0).getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey() == true)
                .map(x -> x.getValue().toString()).findFirst()
                .orElse("0");

        return id;
    }

    public void generateQueriesAndDelete(List<ComponentPersistEntityDTO> componentPersistEntityList) {
        componentPersistEntityList
                .forEach(componentPersistEntity -> {
                    this.deleteComponentPersistEntity(componentPersistEntity);
                });
    }

    private void deleteComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntityDTO) {

        if (componentPersistEntityDTO.getComponentPersistEntityList() != null) {
            componentPersistEntityDTO.getComponentPersistEntityList()
                    .forEach(componentPersistEntity -> {
                        this.deleteComponentPersistEntity(componentPersistEntity);
                    });
        }

        Boolean allowSave = componentPersistEntityDTO.getAllowSave() == null ? false : componentPersistEntityDTO.getAllowSave();
        if (!allowSave) {
            return;
        }

        /* Delete multiline */
        Boolean multiDataLine = (componentPersistEntityDTO.getMultiDataLine()==null?false:componentPersistEntityDTO.getMultiDataLine());
        if(multiDataLine){
            componentPersistEntityDTO.getComponentPersistEntityDataLines()
                    .forEach(componentPersistEntityDataLineDTO -> {
                        componentPersistEntityDTO.setComponentPersistEntityList(
                                componentPersistEntityDataLineDTO.getComponentPersistEntityList());
                        componentPersistEntityDTO.setComponentPersistEntityList(
                                componentPersistEntityDataLineDTO.getComponentPersistEntityList());

                        this.deleteComponentPersistEntity(componentPersistEntityDTO);
                    });
            return;
        }

        /* If type=delete perform deletion, if type=clearJoin just update foreign keys to null */
        String deleteType = componentPersistEntityDTO.getDeleteType() == null ? "" : componentPersistEntityDTO.getDeleteType();
        if (deleteType.equals("delete")) {

            this.generateDeleteQuery(componentPersistEntityDTO.getPersistEntity().getName(),
                    componentPersistEntityDTO.getComponentPersistEntityFieldList());

        } else if (deleteType.equals("clearJoin")) {
            return;
        }

    }

    public void retrieveComponentData(ComponentDTO component,
                                      String selectionId) {

        List<ComponentPersistEntityDTO> retrievedPersistEntities = new ArrayList<>();

        /* Map selection id to first componentPersistEntity */
        ComponentPersistEntityDTO mainComponentPersistEntity = component.getComponentPersistEntityList().get(0);
        this.mapSelectionIdToPersistEntity(mainComponentPersistEntity, selectionId);

        this.retrieveComponentPersistEntityListData(
                component.getComponentPersistEntityList(),
                retrievedPersistEntities);
    }

    private void retrieveComponentPersistEntityListData(
            List<ComponentPersistEntityDTO> componentPersistEntityList,
            List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        /* Itterate & retrieve */
        componentPersistEntityList
                .forEach(componentPersistEntity -> {
                    this.retrieveComponentPersistEntityData(componentPersistEntity, retrievedPersistEntities);
                });
    }

    private void retrieveComponentPersistEntityData(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        List<ComponentPersistEntityFieldDTO> retrievalFieldList =
                this.mapRetrivalFields(componentPersistEntity, retrievedPersistEntities);

        if (retrievalFieldList.size() > 0) {
            componentPersistEntity = this.retrieveComponentPersistEntity(componentPersistEntity, retrievalFieldList);
        }

        if ((componentPersistEntity.getMultiDataLine() == null ? false : componentPersistEntity.getMultiDataLine())) {

            this.retrieveChildrenComponentPersistEntitiesDataByLines(componentPersistEntity,
                    retrievedPersistEntities);
            retrievedPersistEntities.add(componentPersistEntity);
        } else {
            retrievedPersistEntities.add(componentPersistEntity);
            this.retrieveChildrenComponentPersistEntitiesData(componentPersistEntity, retrievedPersistEntities);
        }
    }

    private void retrieveChildrenComponentPersistEntitiesData(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        if (componentPersistEntity.getComponentPersistEntityList() == null) {
            return;
        }

        componentPersistEntity.getComponentPersistEntityList()
                .forEach(childComponentPersistEntity -> {
                    this.retrieveComponentPersistEntityData(childComponentPersistEntity, retrievedPersistEntities);
                });
    }

    private void retrieveChildrenComponentPersistEntitiesDataByLines(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        if (componentPersistEntity.getComponentPersistEntityList() == null) {
            return;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityDTO>>() {
        }.getType();
        componentPersistEntity.getComponentPersistEntityDataLines()
                .forEach(line -> {
                            ComponentPersistEntityDTO lineComponentPersistEntity = new ComponentPersistEntityDTO();
                            lineComponentPersistEntity.setCode(componentPersistEntity.getCode());
                            lineComponentPersistEntity.setComponentPersistEntityFieldList(line.getComponentPersistEntityFieldList());
                            lineComponentPersistEntity.setMultiDataLine(false);
                            List<ComponentPersistEntityDTO> currentRetrievedPersistEntities = new ArrayList<>();
                            currentRetrievedPersistEntities.addAll(retrievedPersistEntities);
                            currentRetrievedPersistEntities.add(lineComponentPersistEntity);

                            List<ComponentPersistEntityDTO> clonedChildComponentPersistEntityList =
                                    gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityList()), listType);
                            line.setComponentPersistEntityList(clonedChildComponentPersistEntityList);

                            line.getComponentPersistEntityList()
                                    .forEach(lineChildComponentPersistEntity -> {
                                        this.retrieveComponentPersistEntityData(lineChildComponentPersistEntity, currentRetrievedPersistEntities);
                                    });
                        }
                );
    }

//    private void retrieveComponentPersistEntitiesDataByFields(
//            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
//            List<ComponentPersistEntityDTO> retrievedPersistEntities) {
//
//        componentPersistEntityFieldList
//                .stream()
//                .forEach(componentPersistEntityField -> {
//                    if (componentPersistEntityField.getJoinPersistEntity() != null) {
//                        this.retrieveComponentPersistEntityData(componentPersistEntityField.getJoinPersistEntity(),
//                                retrievedPersistEntities);
//                    }
//                });
//
//    }

    public ComponentPersistEntityDTO retrieveComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
                                                                    List<ComponentPersistEntityFieldDTO> retrievalFieldList) {

        Query query = this.generateSelectQuery(componentPersistEntity, retrievalFieldList);
        componentPersistEntity = this.executeSelectQuery(query, componentPersistEntity);

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO saveMultilineComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
                                                                          List<ComponentPersistEntityDTO> savedPersistEntities) {

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
        //    if (existingPrimaryKeys.size() > 0) {
        String primaryKeyName = componentPersistEntity.getComponentPersistEntityFieldList()
                .stream()
                .filter(y -> y.getPersistEntityField().getPrimaryKey() == true).findFirst()
                .get().getPersistEntityField().getName();

        if (componentPersistEntity.getDeleteType().equals("delete")) {
            this.deleteNotExistingComponentPersistEntity(
                    existingPrimaryKeys,
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities,
                    primaryKeyName
            );
        } else if (componentPersistEntity.getDeleteType().equals("clearJoin")) {
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
            this.updateComponentPersistEntity(
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntityDataLine.getComponentPersistEntityFieldList(),
                    savedPersistEntities);

            List<ComponentPersistEntityDTO> lineSavedPersistEntities = new ArrayList<>();
            lineSavedPersistEntities.addAll(savedPersistEntities);
            lineSavedPersistEntities.add(componentPersistEntity);

            if (componentPersistEntity.getComponentPersistEntityList() != null) {
                this.generateQueriesAndSave(componentPersistEntity.getComponentPersistEntityList(), lineSavedPersistEntities);
            }
        });

        /*  Insert Section */
        insertableLines.forEach(componentPersistEntityDataLine -> {
            this.insertComponentPersistEntity(
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntityDataLine.getComponentPersistEntityFieldList(),
                    savedPersistEntities);

            List<ComponentPersistEntityDTO> lineSavedPersistEntities = new ArrayList<>();
            lineSavedPersistEntities.addAll(savedPersistEntities);
            lineSavedPersistEntities.add(componentPersistEntity);

            if (componentPersistEntity.getComponentPersistEntityList() != null) {
                this.generateQueriesAndSave(componentPersistEntity.getComponentPersistEntityList(), lineSavedPersistEntities);
            }
        });

        return componentPersistEntity;
    }

    private void saveComponentPersistEntity(
            ComponentPersistEntityDTO componentPersistEntity,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        Boolean hasPrimaryKeyValue = this.hasPrimaryKeyValue(componentPersistEntity);


        if (hasPrimaryKeyValue) {
            this.updateComponentPersistEntity(
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities);
        } else {
            this.insertComponentPersistEntity(
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntity.getComponentPersistEntityFieldList(),
                    savedPersistEntities);
        }
    }

    private List<ComponentPersistEntityFieldDTO> insertComponentPersistEntity(
            String entityName,
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateInsertQuery(entityName, componentPersistEntityFieldList);

        /* Execute Query */
        Long id = this.executeSave(query);

        /* Set Id to component */
        componentPersistEntityFieldList
                .stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey() == true)
                .forEach(x -> x.setValue(id));

        //  this.setPrimaryKey(componentPersistEntityFieldList, id);

        return componentPersistEntityFieldList;
    }

    private List<ComponentPersistEntityFieldDTO> updateComponentPersistEntity(
            String entityName,
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateUpdateQuery(entityName, componentPersistEntityFieldList);

        /* Execute Query */
        if (query != null) {
            this.executeSave(query);
        }

        return componentPersistEntityFieldList;
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

    private void generateDeleteQuery(String entityName, List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Delete From Section */
        String queryString = "DELETE FROM " + entityName;

        /* Where Section */
        List<String> whereList = componentPersistEntityFieldList.stream()
                .filter(x -> !(x.getSaveStatement()==null?"":x.getSaveStatement()).equals(""))
                .filter(x -> x.getValue() != null)
                .map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName())
                .collect(Collectors.toList());

        if (whereList.size() == 0) {
            return;
        }

        String whereString = String.join(" AND ", whereList);
        queryString += " WHERE " + whereString;

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntityFieldList.stream()
                .filter(x -> x.getValue() != null)
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                x.getValue()
                        ));

        query.executeUpdate();
    }

    private Query generateInsertQuery(String entityName,
                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Insert into Section */
        String queryString = "INSERT INTO " + entityName;

        /* Insert into Values Section */
        List<String> headersList = componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .map(x -> x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String headersString = String.join(",", headersList);
        queryString += " (" + headersString + " ) VALUES ";

        /* Parameters Section */
        List<String> valuesParametersList = componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .map(x -> ":" + x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String valuesParametersString = String.join(",", valuesParametersList);
        queryString += " (" + valuesParametersString + " );";

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                (x.getValue() != null ? x.getValue().toString() : "")
                        ));

        return query;
    }

    private Query generateUpdateQuery(String entityName,
                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* UPDATE Section */
        String queryString = "UPDATE " + entityName;

        /* SET columns = values Section */
        List<String> headersList = componentPersistEntityFieldList.stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey() == false)
                .filter(x -> x.getValue() != null)
                .map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String headersString = String.join(", ", headersList);
        queryString += " SET " + headersString;

        if (headersList.size() == 0) {
            return null;
        }

        /* WHERE Section */
        Optional<ComponentPersistEntityFieldDTO> optionalComponentPersistEntityField =
                componentPersistEntityFieldList.stream()
                        .filter(x -> x.getPersistEntityField().getPrimaryKey() == true)
                        .filter(x -> x.getValue() != null)
                        .findFirst();

        if (!optionalComponentPersistEntityField.isPresent()) {
            return null;
        }

        ComponentPersistEntityFieldDTO componentPersistEntityField = optionalComponentPersistEntityField.get();

        queryString += " WHERE " + componentPersistEntityField.getPersistEntityField().getName() + " = :"
                + componentPersistEntityField.getPersistEntityField().getName();

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntityFieldList.stream()
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

    private Long executeSave(Query query) {
        Long id;
        try {
            query.executeUpdate();
            id = ((BigInteger) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).longValue();

        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }

        return id;
    }

    private void executeDelete(Query query) {
        try {
            query.executeUpdate();

        } catch (HibernateException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    private ComponentPersistEntityDTO findComponentPersistEntity(List<ComponentPersistEntityDTO> componentPersistEntityList,
                                                                 String persistEntityCode) {

        /* Find component Persist entity from list */
        Optional<ComponentPersistEntityDTO> compPersistEntityOptional =
                componentPersistEntityList
                        .stream()
                        .filter(cpe -> cpe.getCode().equals(persistEntityCode)).findFirst();

        if (!compPersistEntityOptional.isPresent()) return null;
        ComponentPersistEntityDTO compPersistEntity = compPersistEntityOptional.get();

        return compPersistEntity;
    }

    /* Map selection id to first componentPersistEntity */
    private ComponentPersistEntityDTO mapSelectionIdToPersistEntity(ComponentPersistEntityDTO mainComponentPersistEntity,
                                                                    String selectionId) {

        Optional<ComponentPersistEntityFieldDTO> componentPersistEntityFieldOptional =
                mainComponentPersistEntity.getComponentPersistEntityFieldList()
                        .stream()
                        .filter(x ->
                                (x.getLocateStatement() == null ? "" : x.getLocateStatement()).equals("#SELECTIONID")).findFirst();

        if (!componentPersistEntityFieldOptional.isPresent()) {
            return mainComponentPersistEntity;
        }

        ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO = componentPersistEntityFieldOptional.get();
        componentPersistEntityFieldDTO.setLocateStatement(selectionId);

        return mainComponentPersistEntity;
    }

    private List<ComponentPersistEntityFieldDTO> mapRetrivalFields(ComponentPersistEntityDTO componentPersistEntity,
                                                                   List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        for (ComponentPersistEntityDTO retrievedPersistEntity : retrievedPersistEntities) {
            for (ComponentPersistEntityFieldDTO retrievedComponentPersistEntityField : retrievedPersistEntity.getComponentPersistEntityFieldList()) {
                String currentFieldCode = "#" + retrievedPersistEntity.getCode() + "." + retrievedComponentPersistEntityField.getPersistEntityField().getName();

                for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntity.getComponentPersistEntityFieldList()) {
                    String locateStatement = (componentPersistEntityField.getLocateStatement() == null ? "" : componentPersistEntityField.getLocateStatement());

                    if (locateStatement.equals(currentFieldCode) &&
                            retrievedComponentPersistEntityField.getValue() != null) {
                        componentPersistEntityField.setLocateStatement(retrievedComponentPersistEntityField.getValue().toString());
                    }
                }
            }
        }

        List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                componentPersistEntity.getComponentPersistEntityFieldList()
                        .stream()
                        .filter(x ->
                                !(x.getLocateStatement() == null ? "" : x.getLocateStatement()).equals(""))
                        .collect(Collectors.toList());

        return componentPersistEntityFieldList;
    }

    private Query generateSelectQuery(ComponentPersistEntityDTO componentPersistEntity,
                                      List<ComponentPersistEntityFieldDTO> retrievalFieldList) {

        /* Select Values Section */
        String queryString = "SELECT ";

        List<String> headersList = componentPersistEntity.getComponentPersistEntityFieldList().stream()
                .map(x -> x.getPersistEntityField().getName())
                .collect(Collectors.toList());

        String headersString = String.join(",", headersList);
        queryString += headersString;

        /* From Values Section */
        queryString += " FROM " + componentPersistEntity.getPersistEntity().getName();

        /* Where Values Section */
        List<String> retrievalList = retrievalFieldList.stream()
                .map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName() + " ")
                .collect(Collectors.toList());

        if (retrievalFieldList.size() > 0) {
            queryString += " WHERE ";
            String retrievalString = String.join(" AND ", retrievalList);
            queryString += retrievalString;
        }

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        retrievalFieldList.stream()
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                (x.getLocateStatement() != null ? x.getLocateStatement().toString() : "")
                        ));

        return query;
    }

    private ComponentPersistEntityDTO executeSelectQuery(Query query, ComponentPersistEntityDTO componentPersistEntity) {

        List<Object[]> dataList = query.getResultList();

        if (!(componentPersistEntity.getMultiDataLine() == null ? false : componentPersistEntity.getMultiDataLine())) {
            componentPersistEntity = this.mapSingleLineQueryResponces(componentPersistEntity, dataList.get(0));
        } else {
            componentPersistEntity = this.mapMultiLineQueryResponces(componentPersistEntity, dataList);
        }

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO mapSingleLineQueryResponces(ComponentPersistEntityDTO componentPersistEntity, Object[] dataRow) {

//        componentPersistEntity.setMultiDataLine(false);
        int i = 0;
        for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntity.getComponentPersistEntityFieldList()) {
            componentPersistEntityField.setValue(dataRow[i]);
            i++;
        }

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO mapMultiLineQueryResponces(ComponentPersistEntityDTO componentPersistEntity, List<Object[]> dataList) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {
        }.getType();
//        componentPersistEntity.setMultiDataLine(true);

        for (Object[] dataRow : dataList) {
            int i = 0;

            /* Clone ComponentPersistEntityFieldList and add to line */
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                    gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityFieldList()), listType);
            ComponentPersistEntityDataLineDTO componentPersistEntityDataLine = new ComponentPersistEntityDataLineDTO();
            componentPersistEntityDataLine.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntity.getComponentPersistEntityDataLines().add(componentPersistEntityDataLine);

            /* Add data  to new line field list */
            for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntityFieldList) {
                componentPersistEntityField.setValue(dataRow[i]);
                i++;
            }
        }

        return componentPersistEntity;
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

        if (componentPersistEntityFieldDTO.getValue() == null) {
            return false;
        } else {
            return true;
        }
    }

}
