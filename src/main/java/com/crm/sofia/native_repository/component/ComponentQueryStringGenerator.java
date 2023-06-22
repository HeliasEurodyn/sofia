package com.crm.sofia.native_repository.component;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComponentQueryStringGenerator {

    @Cacheable(value = "component_select_query", key = "#componentPersistEntity.id")
    public String generateSelectCachable(ComponentPersistEntityDTO componentPersistEntity, List<ComponentPersistEntityFieldDTO> retrievalFieldList) {

        /* Select Values Section */
        String queryString = "SELECT ";

        List<String> headersList = componentPersistEntity.getComponentPersistEntityFieldList()
                .stream()
                .map(x -> x.getPersistEntityField().getName())
                .collect(Collectors.toList());

        String headersString = String.join(",", headersList);
        queryString += headersString;

        /* From Values Section */
        if (componentPersistEntity.getPersistEntity().getEntitytype().equals("AppView")) {
            PersistEntityDTO appViewDTO = componentPersistEntity.getPersistEntity();
            queryString += " FROM ( " + appViewDTO.getQuery() + " ) " + componentPersistEntity.getCode();
        } else {
            queryString += " FROM " + componentPersistEntity.getPersistEntity().getName();
        }

//        List<String> retrievalList =
//                componentPersistEntity.getComponentPersistEntityFieldList().stream()
//                        .filter(x -> !(x.getLocateStatement() == null ? "" :x.getLocateStatement()).equals(""))
//                        .map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName() + " ")
//                        .collect(Collectors.toList());

        List<String> retrievalList =
        retrievalFieldList.stream().map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName() + " ")
                        .collect(Collectors.toList());

        if (retrievalList.size() > 0) {
            queryString += " WHERE ";
            String retrievalString = String.join(" AND ", retrievalList);
            queryString += retrievalString;
        }

        return queryString;
    }

    @Cacheable(value = "component_insert_query", key = "#componentPersistEntity.id")
    public String generateInsertCacheable(ComponentPersistEntityDTO componentPersistEntity,
                                            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Insert into Section */
        String queryString = "INSERT INTO " + componentPersistEntity.getPersistEntity().getName();

        /* Insert into Values Section */
        List<String> headersList = componentPersistEntityFieldList.stream()
                .filter(x -> !x.getPersistEntityField().getAutoIncrement())
              //  .filter(x -> x.getValue() != null)
                .map(x -> x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String headersString = String.join(",", headersList);
        queryString += " (" + headersString + " ) VALUES ";

        /* Parameters Section */
        List<String> valuesParametersList = componentPersistEntityFieldList.stream()
                .filter(x -> !x.getPersistEntityField().getAutoIncrement())
            //    .filter(x -> x.getValue() != null)
                .map(x -> ":" + x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String valuesParametersString = String.join(",", valuesParametersList);
        queryString += " (" + valuesParametersString + " );";

        return queryString;
    }

    @Cacheable(value = "component_update_query_map", key = "#componentPersistEntity.id")
    public Map<String, String> generateUpdateCacheable(ComponentPersistEntityDTO componentPersistEntity,
                                                       List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* SET columns = values Section */
        Map<String, String> updateQueryMap = componentPersistEntityFieldList.stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey() == false)
        .collect(Collectors.toMap(
                e -> e.getPersistEntityField().getId(),
                e -> e.getPersistEntityField().getName() + " = :" + e.getPersistEntityField().getName())
        );

        /* WHERE Section */
        Optional<ComponentPersistEntityFieldDTO> optionalCpef =
                componentPersistEntityFieldList.stream()
                        .filter(x -> x.getPersistEntityField().getPrimaryKey() == true)
                        .findFirst();

        if (!optionalCpef.isPresent()) {
            return null;
        }

        ComponentPersistEntityFieldDTO cpef = optionalCpef.get();
        updateQueryMap.put("WHERE",
                String.join("", cpef.getPersistEntityField().getName(), " = :", cpef.getPersistEntityField().getName())
        );

        return updateQueryMap;
    }

}
