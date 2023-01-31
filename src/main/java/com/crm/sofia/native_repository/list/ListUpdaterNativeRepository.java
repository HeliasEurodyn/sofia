package com.crm.sofia.native_repository.list;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ListUpdaterNativeRepository {

    private final EntityManager entityManager;

    public ListUpdaterNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Modifying
    public void updateField(ListDTO listDTO, String fieldCode, Object fieldValue, Object rel) {

        Optional<ListComponentFieldDTO> optionalUpdateField =
        listDTO.getListComponentColumnFieldList()
                .stream()
                .filter(x -> x.getCode().equals(fieldCode))
                .findFirst();

        if (!optionalUpdateField.isPresent()) {
            return;
        }

        ListComponentFieldDTO updateField = optionalUpdateField.get();

        List<ListComponentFieldDTO> fields = new ArrayList<>();
        fields.add(updateField);

        if ((updateField.getEditableRelFieldCode()==null?"":updateField.getEditableRelFieldCode()).equals("")) {
            return;
        }

        Optional<ListComponentFieldDTO> optionalRelField =
                listDTO.getListComponentColumnFieldList()
                        .stream()
                        .filter(x -> x.getCode().equals(updateField.getEditableRelFieldCode()))
                        .findFirst();

        if (!optionalRelField.isPresent()) {
            return;
        }

        ListComponentFieldDTO relField = optionalRelField.get();
        fields.add(relField);


        /*
         * Identify (Join Participating) Persist Entities
         */
        List<ComponentPersistEntityDTO> persistEntities = this.identifyFromPersistEntities(listDTO, fields);

        /*
         * UPDATE clause
         */
        String queryString = this.generateFromPart(persistEntities);

        /*
         * SET clause
         */
        queryString += " SET " +
                updateField.getComponentPersistEntity().getCode() + "."
                + updateField.getComponentPersistEntityField().getPersistEntityField().getName() + " = :field_value ";

        /*
         * WHERE clause
         */
        queryString += " WHERE " +
                relField.getComponentPersistEntity().getCode() + "."
                + relField.getComponentPersistEntityField().getPersistEntityField().getName() + " = :rel ";


        /*
         * Replace Parameters & Save
         */


        Query query = entityManager.createNativeQuery(queryString);

        if(!Arrays.asList( "varchar", "text").contains(
                updateField.getComponentPersistEntityField().getPersistEntityField().getType())
                && fieldValue.equals("")){
            fieldValue = null;
            query.setParameter("field_value", null);
        } else {
            query.setParameter("field_value", fieldValue);
        }
        query.setParameter("rel", rel);

        log.debug(query.unwrap(org.hibernate.Query.class).getQueryString());
        query.executeUpdate();
    }

    /*
     * Iterate to find related to query persist entities
     */
    private List<ComponentPersistEntityDTO> identifyFromPersistEntities(ListDTO listDTO, List<ListComponentFieldDTO> fields) {


        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.getComponentPersistEntitiesTreeToList(listDTO.getComponent().getComponentPersistEntityList());

        List<String> entityIds =
                fields.stream()
                        .filter(x -> x.getComponentPersistEntity() != null)
                        .map(x -> x.getComponentPersistEntity().getId())
                        .distinct()
                        .collect(Collectors.toList());

        entityIds.forEach(id -> {

            ComponentPersistEntityDTO persistEntity =
                    componentPersistEntityList
                            .stream()
                            .filter(x -> x.getId().equals(id))
                            .findFirst()
                            .orElse(null);

            this.identifyFromPersistEntitiesByJoins(persistEntity,
                    entityIds,
                    componentPersistEntityList
            );
        });

        return componentPersistEntityList
                .stream()
                .filter(x -> x != null)
                .filter(x -> entityIds.contains(x.getId()))
                .collect(Collectors.toList());
    }


    /*
     * Iterate to Generate UPDATE Tables & Relashionships part
     */
    private String generateFromPart(List<ComponentPersistEntityDTO> fromCPersistEntities) {

        List<String> joinParts = new ArrayList<>();
        fromCPersistEntities
                .stream()
                .sorted(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder))
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

        return " UPDATE " + String.join(" ", joinParts);
    }


    /*
     * Iterate to Generate FROM Tables & Relashionships part
     */
    private void identifyFromPersistEntitiesByJoins(ComponentPersistEntityDTO persistEntity,
                                                    List<String> entityIds,
                                                    List<ComponentPersistEntityDTO> componentPersistEntityList) {

        List<String> componentPersistEntityCodes = new ArrayList<>();

        List<String> locateStatemens =
                persistEntity.getComponentPersistEntityFieldList()
                        .stream()
                        .filter(x -> x.getLocateStatement() != null)
                        .filter(x -> x.getLocateStatement() != "")
                        .filter(x -> x.getLocateStatement().contains("."))
                        .filter(x -> x.getLocateStatement().startsWith("#"))
                        .map(x -> x.getLocateStatement())
                        .collect(Collectors.toList());

        locateStatemens
                .stream()
                .forEach(x -> {
                    String[] joinParts = x.split("\\.");
                    String cpeCode = joinParts[0].replace("#", "");
                    componentPersistEntityCodes.add(cpeCode);
                });

        componentPersistEntityList
                .stream()
                .filter(x -> componentPersistEntityCodes.contains(x.getCode()))
                .filter(x -> !entityIds.contains(x.getId()))
                .forEach(x -> {
                    entityIds.add(x.getId());
                    this.identifyFromPersistEntitiesByJoins(x, entityIds, componentPersistEntityList);
                });

    }

    private List<ComponentPersistEntityDTO> getComponentPersistEntitiesTreeToList(List<ComponentPersistEntityDTO> initialComponentPersistEntityList) {
        List<ComponentPersistEntityDTO> allComponentPersistEntityList = new ArrayList<>();
        allComponentPersistEntityList.addAll(initialComponentPersistEntityList);

        initialComponentPersistEntityList
                .stream()
                .filter(x -> x.getComponentPersistEntityList() != null)
                .filter(x -> x.getComponentPersistEntityList().size() > 0)
                .forEach(x -> {

                    List<ComponentPersistEntityDTO> componentPersistEntityList =
                            this.getComponentPersistEntitiesTreeToList(x.getComponentPersistEntityList());
                    if (componentPersistEntityList.size() > 0) {
                        allComponentPersistEntityList.addAll(componentPersistEntityList);
                    }

                });

        return allComponentPersistEntityList;
    }

}
