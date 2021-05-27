package com.crm.sofia.native_repository.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponentDeleterNativeRepository {

    private final EntityManager entityManager;

    public ComponentDeleterNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Modifying
    public void delete(ComponentDTO componentDTO) {
        this.generateQueriesAndDelete(componentDTO.getComponentPersistEntityList());
    }

    private void generateQueriesAndDelete(List<ComponentPersistEntityDTO> componentPersistEntityList) {
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
        Boolean multiDataLine = (componentPersistEntityDTO.getMultiDataLine() == null ? false : componentPersistEntityDTO.getMultiDataLine());
        if (multiDataLine) {
            componentPersistEntityDTO.getComponentPersistEntityDataLines()
                    .forEach(componentPersistEntityDataLineDTO -> {

                        ComponentPersistEntityDTO componentPersistEntityDataLine = new ComponentPersistEntityDTO();

                        componentPersistEntityDataLine.setComponentPersistEntityFieldList(
                                componentPersistEntityDataLineDTO.getComponentPersistEntityFieldList());
                        componentPersistEntityDataLine.setComponentPersistEntityList(
                                componentPersistEntityDataLineDTO.getComponentPersistEntityList());
                        componentPersistEntityDataLine.setMultiDataLine(false);
                        componentPersistEntityDataLine.setPersistEntity(componentPersistEntityDTO.getPersistEntity());
                        componentPersistEntityDataLine.setCode(componentPersistEntityDTO.getCode());
                        componentPersistEntityDataLine.setAllowSave(componentPersistEntityDTO.getAllowSave());
                        componentPersistEntityDataLine.setAllowRetrieve(componentPersistEntityDTO.getAllowRetrieve());
                        componentPersistEntityDataLine.setDeleteType(componentPersistEntityDTO.getDeleteType());

                        this.deleteComponentPersistEntity(componentPersistEntityDataLine);
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

    private void generateDeleteQuery(String entityName, List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Delete From Section */
        String queryString = "DELETE FROM " + entityName;

        /* Where Section */
//        List<String> whereList = componentPersistEntityFieldList.stream()
//                .filter(x -> !(x.getSaveStatement() == null ? "" : x.getSaveStatement()).equals(""))
//                .filter(x -> x.getValue() != null)
//                .map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName())
//                .collect(Collectors.toList());

        /* Where Section */
        List<String> whereList = componentPersistEntityFieldList
                .stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey())
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

        componentPersistEntityFieldList
                .stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey())
                .filter(x -> x.getValue() != null)
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                x.getValue()
                        ));

        query.executeUpdate();
    }
}
