package com.crm.sofia.native_repository.sofia.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.model.sofia.expression.ExprResponce;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.expression.ExpressionService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ComponentSaverNativeRepository {

    private final EntityManager entityManager;
    private final JWTService jwtService;
    private final ExpressionService expressionService;
    private final PasswordEncoder passwordEncoder;

    public ComponentSaverNativeRepository(EntityManager entityManager,
                                          JWTService jwtService,
                                          ExpressionService expressionService, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.jwtService = jwtService;
        this.expressionService = expressionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String save(ComponentDTO componentDTO) {
        return this.generateQueriesAndSave(componentDTO.getComponentPersistEntityList(), new ArrayList<>());
    }

    private String generateQueriesAndSave(
            List<ComponentPersistEntityDTO> componentPersistEntityList, List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Itterate & save */
        for (ComponentPersistEntityDTO componentPersistEntity : componentPersistEntityList) {
            Boolean multiDataLine = (componentPersistEntity.getMultiDataLine() != null && componentPersistEntity.getMultiDataLine());
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
        if (componentPersistEntityList.size() == 0) {
            return "0";
        }

        String id = componentPersistEntityList.get(0).getComponentPersistEntityFieldList()
                .stream()
                .filter(x -> (x.getPersistEntityField().getPrimaryKey() != null && x.getPersistEntityField().getPrimaryKey()) == true)
                .map(x -> (x.getValue() == null ? "0" : x.getValue().toString())).findFirst()
                .orElse("0");

        return id;
    }

    private ComponentPersistEntityDTO saveMultilineComponentPersistEntity(ComponentPersistEntityDTO componentPersistEntity,
                                                                          List<ComponentPersistEntityDTO> savedPersistEntities) {

        if (!(componentPersistEntity.getAllowSave() != null && componentPersistEntity.getAllowSave())) {
            return componentPersistEntity;
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
                        componentPersistEntity.getPersistEntity().getName(),
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
                        componentPersistEntity.getPersistEntity().getName(),
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

        return componentPersistEntity;
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

        log.info(query.toString());

        /* Execute Query */
        if (query != null) {
            this.executeSave(query);
        }

        return componentPersistEntityFieldList;
    }

    private Query generateUpdateQuery(String entityName,
                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* UPDATE Section */
        String queryString = "UPDATE " + entityName;

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

        /* SET columns = values Section */
        List<String> headersList = componentPersistEntityFieldList.stream()
                .filter(x -> x.getPersistEntityField().getPrimaryKey() == false)
                .filter(x ->
                        (!x.getPersistEntityField().getType().equals("password")) ||
                                (x.getPersistEntityField().getType().equals("password") && !(x.getValue() == null?"":x.getValue()).equals("")) )
                .map(x -> x.getPersistEntityField().getName() + " = :" + x.getPersistEntityField().getName())
                .collect(Collectors.toList());
        String headersString = String.join(", ", headersList);
        queryString += " SET " + headersString;

        if (headersList.size() == 0) {
            return null;
        }

        /* WHERE Section */
        Optional<ComponentPersistEntityFieldDTO> optionalCpef =
                componentPersistEntityFieldList.stream()
                        .filter(x -> x.getPersistEntityField().getPrimaryKey() == true)
                        .filter(x -> x.getValue() != null)
                        .findFirst();

        if (!optionalCpef.isPresent()) {
            return null;
        }

        ComponentPersistEntityFieldDTO cpef = optionalCpef.get();

        queryString += " WHERE " + cpef.getPersistEntityField().getName() + " = :"
                + cpef.getPersistEntityField().getName();

        /* Parameters Replacement Section */
        Query query = entityManager.createNativeQuery(queryString);

        componentPersistEntityFieldList.stream()
                .filter(x -> !x.getPersistEntityField().getType().equals("password"))
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                x.getValue()
                        ));

        componentPersistEntityFieldList.stream()
                .filter(x -> x.getPersistEntityField().getType().equals("password")  && !(x.getValue() == null?"":x.getValue()).equals("") )
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                passwordEncoder.encode(x.getValue().toString())
                        ));

        return query;
    }

    private Query generateInsertQuery(String entityName,
                                      List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList) {

        /* Insert into Section */
        String queryString = "INSERT INTO " + entityName;

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
                .filter(x -> !x.getPersistEntityField().getType().equals("password"))
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                (x.getValue() != null ? x.getValue() : "")
                        ));

        componentPersistEntityFieldList.stream()
                .filter(y -> !y.getPersistEntityField().getAutoIncrement())
                .filter(x -> x.getValue() != null)
                .filter(x -> x.getPersistEntityField().getType().equals("password") )
                .forEach(x ->
                        query.setParameter(
                                x.getPersistEntityField().getName(),
                                passwordEncoder.encode(x.getValue().toString())
                        ));

        return query;
    }

    private Long executeSave(Query query) {
        Long id;
        try {
            System.out.println(query.unwrap(org.hibernate.Query.class).getQueryString());
            query.executeUpdate();
            id = ((BigInteger) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).longValue();

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
            String entityName,
            List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldList,
            List<ComponentPersistEntityDTO> savedPersistEntities) {

        /* Map Values from previous saved ones */
        componentPersistEntityFieldList =
                this.mapSavedValuesToComponentPersistEntity(savedPersistEntities, componentPersistEntityFieldList);

        /* Run On Save Expressions */
        this.runOnSaveExpressions(componentPersistEntityFieldList);

        /* Generate Query */
        Query query = this.generateInsertQuery(entityName, componentPersistEntityFieldList);

        log.info(query.unwrap(org.hibernate.Query.class).getQueryString());

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
                    ExprResponce exprResponce = expressionService.create(cpef.getPersistEntityField().getOnSaveValue());
                    if (!exprResponce.getError()) {
                        Object fieldValue = exprResponce.getExprUnit().getResult();
                        cpef.setValue(fieldValue);
                    }
                });
    }

}
