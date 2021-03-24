package com.crm.sofia.services.form;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
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
import java.util.*;
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

        /* Μaps parameters to component. */
        component = this.mapParametersToComponentDTO(component, parameters);

        /* Filter - Keep only Table, Saveable PersistEntities */
        List<ComponentPersistEntityDTO> filteredPersistEntityList =
                component.getComponentPersistEntityList().stream()
                        .filter(x -> x.getPersistEntity().getEntitytype().equals("Table"))
                        .filter(x -> x.getAllowSave())
                        .collect(Collectors.toList());

        /* Itterate & save */
        for (ComponentPersistEntityDTO componentPersistEntity : filteredPersistEntityList) {

            Boolean multiDataLine = (componentPersistEntity.getMultiDataLine() == null ? false : componentPersistEntity.getMultiDataLine());
            if (multiDataLine == true) {
                this.saveComponentPersistEntityLine(componentPersistEntity, savedPersistEntities);
            } else {
                this.saveComponentPersistEntity(
                        componentPersistEntity.getPersistEntity().getName(),
                        componentPersistEntity.getComponentPersistEntityFieldList(),
                        savedPersistEntities);
                savedPersistEntities.add(componentPersistEntity);
            }

        }

    }

    private ComponentPersistEntityDTO saveComponentPersistEntityLine(ComponentPersistEntityDTO componentPersistEntity,
                                                                     List<ComponentPersistEntityDTO> savedPersistEntities) {


        for (ComponentPersistEntityDataLineDTO componentPersistEntityDataLine : componentPersistEntity.getComponentPersistEntityDataLines()) {

            this.saveComponentPersistEntity(
                    componentPersistEntity.getPersistEntity().getName(),
                    componentPersistEntityDataLine.getComponentPersistEntityFieldList(),
                    savedPersistEntities);

        }

        return componentPersistEntity;
    }

    private List<ComponentPersistEntityFieldDTO> saveComponentPersistEntity(
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

        /* Save Query */
        this.setPrimaryKey(componentPersistEntityFieldList, id);

        return componentPersistEntityFieldList;
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

    private List<ComponentPersistEntityFieldDTO> setPrimaryKey(List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList, Long id) {
        for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : componentPersistEntityFieldList) {
            if (componentPersistEntityFieldDTO.getPersistEntityField().getPrimaryKey() == true) {
                componentPersistEntityFieldDTO.setValue(id);
            }
        }
        return componentPersistEntityFieldList;
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

    private ComponentDTO mapParametersToComponentDTO(ComponentDTO componentDTO, Map<String, Map<String, Object>> parameters) {

        /* Iterate parameters */
        for (Map.Entry persistEntityPair : parameters.entrySet()) {
            String persistEntityCode = (String) persistEntityPair.getKey();
            Map<String, Object> persistEntityFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            /* Find component Persist entity from list */
            ComponentPersistEntityDTO componentPersistEntity = this.findComponentPersistEntity(componentDTO.getComponentPersistEntityList(),
                    persistEntityCode);
            if (componentPersistEntity == null) continue;

            if (persistEntityFieldsMap.containsKey("multiline")) {
                /* Iterate parameters and map MultilinePersistEntity */
                componentPersistEntity = this.mapMultilinePersistEntity(componentPersistEntity,
                        persistEntityFieldsMap);
                componentPersistEntity.setMultiDataLine(true);
            } else {
                /* Iterate parameters and map to componentPersistEntity Fields */
                List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                        this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntity.getComponentPersistEntityFieldList(),
                                persistEntityFieldsMap);
                componentPersistEntity.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
                componentPersistEntity.setMultiDataLine(false);
            }

        }

        return componentDTO;
    }

    private ComponentPersistEntityDTO mapMultilinePersistEntity(ComponentPersistEntityDTO componentPersistEntity, Map<String, Object> persistEntityFieldsMap) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {}.getType();

        /* Iterate lines */
        for (Map.Entry persistEntityPair : persistEntityFieldsMap.entrySet()) {
            String persistEntityid = (String) persistEntityPair.getKey();
            if (persistEntityid == "multiline") continue;

            Map<String, Object> persistEntityLineFieldsMap = (Map<String, Object>) persistEntityPair.getValue();

            /* Clone ComponentPersistEntityFieldList and add to line */
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                    gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityFieldList()), listType);

            /* Iterate parameters and map to componentPersistEntity Fields */
            componentPersistEntityFieldList =
                    this.itterateAndMapPamametersToComponentPersistEntityFields(componentPersistEntityFieldList,
                            persistEntityLineFieldsMap);

            ComponentPersistEntityDataLineDTO componentPersistEntityDataLine = new ComponentPersistEntityDataLineDTO();
            componentPersistEntityDataLine.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntity.getComponentPersistEntityDataLines().add(componentPersistEntityDataLine);
        }

        return componentPersistEntity;
    }

    private List<ComponentPersistEntityFieldDTO> itterateAndMapPamametersToComponentPersistEntityFields(List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                                                                        Map<String, Object> persistEntityFieldsMap) {
        /* Iterate parameters */
        for (Map.Entry persistEntityFieldPair : persistEntityFieldsMap.entrySet()) {
            String persistEntityFieldCode = (String) persistEntityFieldPair.getKey();
            Object persistEntityFieldValue = (Object) persistEntityFieldPair.getValue();

            /* Find component Persist entity field */
            ComponentPersistEntityFieldDTO compPersistEntityField =
                    this.findComponentPersistEntityField(componentPersistEntityFieldList,
                            persistEntityFieldCode);
            if (compPersistEntityField == null) continue;

            /* Set value to compPersistEntityField */
            compPersistEntityField.setValue(persistEntityFieldValue);
        }

        return componentPersistEntityFieldList;
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

    private ComponentPersistEntityFieldDTO findComponentPersistEntityField(List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
                                                                           String persistEntityFieldCode) {

        /* Find component Persist entity field from selected componentPersistEntity */
        Optional<ComponentPersistEntityFieldDTO> compPersistEntityFieldOptional =
                componentPersistEntityFieldList
                        .stream()
                        .filter(cpef -> cpef.getPersistEntityField().getName().equals(persistEntityFieldCode))
                        .findFirst();

        if (!compPersistEntityFieldOptional.isPresent()) return null;
        ComponentPersistEntityFieldDTO compPersistEntityField = compPersistEntityFieldOptional.get();

        return compPersistEntityField;
    }

    public void retrieveComponentData(ComponentDTO component,
                                      String selectionId) {

        List<ComponentPersistEntityDTO> retrievedPersistEntities = new ArrayList<>();

        /* Map selection id to first componentPersistEntity */
        ComponentPersistEntityDTO mainComponentPersistEntity = component.getComponentPersistEntityList().get(0);
        mainComponentPersistEntity = this.mapSelectionIdToPersistEntity(mainComponentPersistEntity, selectionId);

        /* Itterate & retrieve */
        for (ComponentPersistEntityDTO componentPersistEntity : component.getComponentPersistEntityList()) {
            List<ComponentPersistEntityFieldDTO> retrievalFieldList =
                    this.mapRetrivalFields(componentPersistEntity, retrievedPersistEntities);
            componentPersistEntity = this.retrieveComponentPersistEntity(componentPersistEntity, retrievalFieldList);
            retrievedPersistEntities.add(componentPersistEntity);
        }
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

                    if (locateStatement.equals(currentFieldCode)) {
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

    private ComponentPersistEntityDTO retrieveComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
                                                                     List<ComponentPersistEntityFieldDTO> retrievalFieldList) {

        Query query = this.generateSelectQuery(componentPersistEntity, retrievalFieldList);
        componentPersistEntity = this.executeSelectQuery(query, componentPersistEntity);

        return componentPersistEntity;
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

        if(retrievalFieldList.size() > 0){
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

        if(dataList.size() == 1){
            this.mapSingleLineQueryResponces(componentPersistEntity,dataList.get(0));
        } else if (dataList.size() > 1){
            this.mapMultiLineQueryResponces(componentPersistEntity,dataList);
        }

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO mapSingleLineQueryResponces(ComponentPersistEntityDTO componentPersistEntity, Object[] dataRow) {

            componentPersistEntity.setMultiDataLine(false);
            int i = 0;
            for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntity.getComponentPersistEntityFieldList()) {
                componentPersistEntityField.setValue(dataRow[i]);
                i++;
            }

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO mapMultiLineQueryResponces(ComponentPersistEntityDTO componentPersistEntity, List<Object[]> dataList) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {}.getType();
        componentPersistEntity.setMultiDataLine(true);

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


}
