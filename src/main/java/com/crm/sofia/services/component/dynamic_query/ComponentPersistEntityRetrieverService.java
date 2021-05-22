package com.crm.sofia.services.component.dynamic_query;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponentPersistEntityRetrieverService {

    private final EntityManager entityManager;

    public ComponentPersistEntityRetrieverService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ComponentPersistEntityDTO retrieveComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
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
