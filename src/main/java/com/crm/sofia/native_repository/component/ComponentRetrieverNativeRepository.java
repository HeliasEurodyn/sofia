package com.crm.sofia.native_repository.component;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponentRetrieverNativeRepository {

    private final EntityManager entityManager;

    private final ComponentQueryStringGenerator componentQueryStringGenerator;

    public ComponentRetrieverNativeRepository(EntityManager entityManager,
                                              ComponentQueryStringGenerator componentQueryStringGenerator) {
        this.entityManager = entityManager;
        this.componentQueryStringGenerator = componentQueryStringGenerator;
    }

    public void retrieveComponentData(ComponentDTO component, String selectionId) {

        List<ComponentPersistEntityDTO> retrievedPersistEntities = new ArrayList<>();

        /* Map selection id to first componentPersistEntity */
        ComponentPersistEntityDTO mainComponentPersistEntity = component.getComponentPersistEntityList().get(0);
        this.mapSelectionIdToPersistEntity(mainComponentPersistEntity, selectionId);

        /* Retrieve Tree */
        this.retrieveComponentPersistEntityListData(component.getComponentPersistEntityList(), retrievedPersistEntities);
    }

    /* Map selection id to first componentPersistEntity */
    private ComponentPersistEntityDTO mapSelectionIdToPersistEntity(ComponentPersistEntityDTO mainComponentPersistEntity, String selectionId) {

        Optional<ComponentPersistEntityFieldDTO> componentPersistEntityFieldOptional =
                mainComponentPersistEntity.getComponentPersistEntityFieldList()
                        .stream()
                        .filter(x -> (x.getLocateStatement() == null ? "" : x.getLocateStatement())
                                .equals("#SELECTIONID")).findFirst();

        if (!componentPersistEntityFieldOptional.isPresent()) {
            return mainComponentPersistEntity;
        }

        ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO = componentPersistEntityFieldOptional.get();
        componentPersistEntityFieldDTO.setLocateStatement(selectionId);

        return mainComponentPersistEntity;
    }

    public void retrieveComponentPersistEntityListData(List<ComponentPersistEntityDTO> componentPersistEntityList, List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        /* Itterate & retrieve */
        componentPersistEntityList.forEach(componentPersistEntity -> {
            this.retrieveComponentPersistEntityData(componentPersistEntity, retrievedPersistEntities);
        });
    }

    private void retrieveComponentPersistEntityData(ComponentPersistEntityDTO componentPersistEntity,
                                                    List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        componentPersistEntity = this.retrieveComponentPersistEntity(componentPersistEntity, retrievedPersistEntities);

        if(componentPersistEntity == null){
            return;
        }

        /* Retrieve Children */
        if ((componentPersistEntity.getMultiDataLine() != null && componentPersistEntity.getMultiDataLine())) {
            this.retrieveChildrenComponentPersistEntitiesDataByLines(componentPersistEntity, retrievedPersistEntities);
            retrievedPersistEntities.add(componentPersistEntity);
        } else {
            retrievedPersistEntities.add(componentPersistEntity);
            this.retrieveChildrenComponentPersistEntitiesData(componentPersistEntity, retrievedPersistEntities);
        }
    }

    public List<ComponentPersistEntityFieldDTO> mapRetrivalFields(ComponentPersistEntityDTO cpe, List<ComponentPersistEntityDTO> retrievedCpeList) {

        for (ComponentPersistEntityDTO retrievedCpe : retrievedCpeList) {
            for (ComponentPersistEntityFieldDTO retrievedCpef : retrievedCpe.getComponentPersistEntityFieldList()) {
                String currentFieldCode = "#" + retrievedCpe.getCode() + "." + retrievedCpef.getPersistEntityField().getName();

                for (ComponentPersistEntityFieldDTO cpef : cpe.getComponentPersistEntityFieldList()) {
                    String locateStatement = (cpef.getLocateStatement() == null ? "" : cpef.getLocateStatement());

                    if (locateStatement.equals(currentFieldCode)) {
                        String value = retrievedCpef.getValue() == null ? "" : retrievedCpef.getValue().toString();
                        cpef.setLocateStatement(value);
                    }
                }
            }
        }

        List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList =
                cpe.getComponentPersistEntityFieldList()
                .stream()
                        .filter(x -> !(x.getLocateStatement() == null ? "" : x.getLocateStatement()).equals(""))
                        .collect(Collectors.toList());

        return componentPersistEntityFieldList;
    }

    public ComponentPersistEntityDTO retrieveComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity, List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        /* Find CpeFields from componentPersistEntity with locateStatement, map locateStatements from Retrieved & return */
        List<ComponentPersistEntityFieldDTO> retrievalFieldList = this.mapRetrivalFields(componentPersistEntity, retrievedPersistEntities);

        /* Retrieve if locate statements found */
        if (retrievalFieldList.size() == 0) {
            return null;
        }

        Query query = this.generateSelectQuery(componentPersistEntity, retrievalFieldList);
        componentPersistEntity = this.executeSelectQuery(query, componentPersistEntity);

        return componentPersistEntity;
    }

    private void retrieveChildrenComponentPersistEntitiesDataByLines(ComponentPersistEntityDTO componentPersistEntity, List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        if (componentPersistEntity.getComponentPersistEntityList() == null) {
            return;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityDTO>>() {
        }.getType();
        componentPersistEntity.getComponentPersistEntityDataLines().forEach(line -> {
            ComponentPersistEntityDTO lineComponentPersistEntity = new ComponentPersistEntityDTO();
            lineComponentPersistEntity.setCode(componentPersistEntity.getCode());
            lineComponentPersistEntity.setComponentPersistEntityFieldList(line.getComponentPersistEntityFieldList());
            lineComponentPersistEntity.setMultiDataLine(false);
            List<ComponentPersistEntityDTO> currentRetrievedPersistEntities = new ArrayList<>();
            currentRetrievedPersistEntities.addAll(retrievedPersistEntities);
            currentRetrievedPersistEntities.add(lineComponentPersistEntity);

            List<ComponentPersistEntityDTO> clonedChildComponentPersistEntityList = gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityList()), listType);
            line.setComponentPersistEntityList(clonedChildComponentPersistEntityList);

            line.getComponentPersistEntityList().forEach(lineChildComponentPersistEntity -> {
                this.retrieveComponentPersistEntityData(lineChildComponentPersistEntity, currentRetrievedPersistEntities);
            });
        });
    }

    private void retrieveChildrenComponentPersistEntitiesData(ComponentPersistEntityDTO componentPersistEntity, List<ComponentPersistEntityDTO> retrievedPersistEntities) {

        if (componentPersistEntity.getComponentPersistEntityList() == null) {
            return;
        }

        componentPersistEntity.getComponentPersistEntityList().forEach(childComponentPersistEntity -> {
            this.retrieveComponentPersistEntityData(childComponentPersistEntity, retrievedPersistEntities);
        });
    }

    private Query generateSelectQuery(ComponentPersistEntityDTO componentPersistEntity, List<ComponentPersistEntityFieldDTO> retrievalFieldList) {

        String queryString = this.componentQueryStringGenerator.generateSelectCachable(componentPersistEntity);

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        retrievalFieldList.stream().forEach(x -> query.setParameter(x.getPersistEntityField().getName(), (x.getLocateStatement() != null ? x.getLocateStatement() : "")));

        return query;
    }

    private ComponentPersistEntityDTO executeSelectQuery(Query query, ComponentPersistEntityDTO componentPersistEntity) {

        List<Object[]> dataList = query.getResultList();

        if (dataList.size() == 0) {
            return componentPersistEntity;
        }

        if (!(componentPersistEntity.getMultiDataLine() != null && componentPersistEntity.getMultiDataLine())) {
            componentPersistEntity = this.mapSingleLineQueryResponses(componentPersistEntity, dataList.get(0));
        } else {
            componentPersistEntity = this.mapMultiLineQueryResponces(componentPersistEntity, dataList);
        }

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO mapSingleLineQueryResponses(ComponentPersistEntityDTO componentPersistEntity, Object[] dataRow) {
        int i = 0;
        for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntity.getComponentPersistEntityFieldList()) {
            if (!componentPersistEntityField.getPersistEntityField().getType().equals("password")) {
                componentPersistEntityField.setValue(dataRow[i]);
            }
            i++;
        }

        return componentPersistEntity;
    }

    private ComponentPersistEntityDTO mapMultiLineQueryResponces(ComponentPersistEntityDTO componentPersistEntity, List<Object[]> dataList) {

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ComponentPersistEntityFieldDTO>>() {
        }.getType();

        for (Object[] dataRow : dataList) {
            int i = 0;

            /* Clone ComponentPersistEntityFieldList and add to line */
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList = gson.fromJson(gson.toJson(componentPersistEntity.getComponentPersistEntityFieldList()), listType);
            ComponentPersistEntityDataLineDTO componentPersistEntityDataLine = new ComponentPersistEntityDataLineDTO();
            componentPersistEntityDataLine.setComponentPersistEntityFieldList(componentPersistEntityFieldList);
            componentPersistEntity.getComponentPersistEntityDataLines().add(componentPersistEntityDataLine);

            /* Add data  to new line field list */
            for (ComponentPersistEntityFieldDTO componentPersistEntityField : componentPersistEntityFieldList) {
                if (!componentPersistEntityField.getPersistEntityField().getType().equals("password")) {
                    componentPersistEntityField.setValue(dataRow[i]);
                }
                i++;
            }
        }

        return componentPersistEntity;
    }
}
