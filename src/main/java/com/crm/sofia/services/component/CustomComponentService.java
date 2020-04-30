package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.dto.component.CustomComponentFieldDTO;
import com.crm.sofia.mapper.component.CustomComponentMapper;
import com.crm.sofia.model.component.CustomComponent;
import com.crm.sofia.repository.component.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomComponentService {
    private final CustomComponentFieldService customComponentFieldService;
    private final ComponentRepository componentRepository;
    private final CustomComponentMapper componentMapper;
    private final EntityManager entityManager;

    public CustomComponentService(ComponentRepository componentRepository,
                                  CustomComponentMapper componentMapper,
                                  CustomComponentFieldService customComponentFieldService,
                                  EntityManager entityManager) {
        this.componentRepository = componentRepository;
        this.customComponentFieldService = customComponentFieldService;
        this.componentMapper = componentMapper;
        this.entityManager = entityManager;
    }

    @Transactional
    public CustomComponentDTO postObject(CustomComponentDTO componentDTO) {
        CustomComponent component = this.componentMapper.mapDTO(componentDTO);

        CustomComponent customComponent = this.componentRepository.save(component);
        return this.componentMapper.map(customComponent);
    }

    @Transactional
    public CustomComponentDTO putObject(CustomComponentDTO componentDTO) {

        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(componentDTO.getId());
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        CustomComponent customComponent = optionalComponent.get();

        componentMapper.setDtoToEntity(componentDTO, customComponent);
        CustomComponent createdCustomComponent = this.componentRepository.save(customComponent);
        CustomComponentDTO createdCustomComponentDTO = this.componentMapper.map(createdCustomComponent);

        return createdCustomComponentDTO;
    }

    @Transactional
    public List<CustomComponentFieldDTO> putNewObjectFields(CustomComponentDTO componentDTO) {
        List<CustomComponentFieldDTO> createdCustomComponentFieldDTOs = new ArrayList<>();
        for (CustomComponentFieldDTO customComponentFieldDTO : componentDTO.getCustomComponentFieldList()) {
            CustomComponentFieldDTO createdCustomComponentFieldDTO = this.customComponentFieldService.saveCustomComponentField(customComponentFieldDTO, componentDTO.getId());
            createdCustomComponentFieldDTOs.add(createdCustomComponentFieldDTO);
        }

        List<Long> ids = createdCustomComponentFieldDTOs.stream().map(CustomComponentFieldDTO::getId).collect(Collectors.toList());
        this.customComponentFieldService.deleteObjectsNotInListForCustomComponent(ids, componentDTO.getId());

        return createdCustomComponentFieldDTOs;
    }


    public List<CustomComponentDTO> getObject() {
        List<CustomComponent> customComponents = this.componentRepository.findAll();
        return this.componentMapper.map(customComponents);
    }

    public CustomComponentDTO getObject(Long id) {
        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        return this.componentMapper.map(optionalComponent.get());
    }

    public void deleteObject(Long id) {
        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        this.componentRepository.deleteById(optionalComponent.get().getId());
    }

    @Transactional
    public List<String> getTables() {
        Query query = entityManager.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='sofia';");
        List<String> tableNames = query.getResultList();
        return tableNames;
    }


    @Transactional
    public List<String> getTableFields(String tableName) {
        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + tableName + " FROM sofia;");
        List<Object[]> fields = query.getResultList();
        List<String> fieldNames = fields.stream().map(f -> f[0].toString()).collect(Collectors.toList());

        return fieldNames;
    }

    @Transactional
    public void deteleDatabaseTable(String tableName) {
        Query query = entityManager.createNativeQuery("DROP TABLE "+tableName.replace(" ","")+";");
        query.executeUpdate();
    }

    @Transactional
    public void updateDatabaseTable(CustomComponentDTO customComponentDTO) {

        List<String> existingTableFields = this.getTableFields(customComponentDTO.getName().replace(" ", ""));
        int fieldCounter = 0;
        String sql = "";
        sql += "ALTER TABLE " + customComponentDTO.getName().replace(" ", "");
        sql += " \n";
        for (CustomComponentFieldDTO customComponentFieldDTO : customComponentDTO.getCustomComponentFieldList()) {

            if (existingTableFields.contains(customComponentFieldDTO.getName().replace(" ", ""))) {
                continue;
            }

            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += " ADD COLUMN ";
            sql += customComponentFieldDTO.getName().replace(" ", "") + " ";
            sql += " " + customComponentFieldDTO.getType().replace(" ", "");
            if (customComponentFieldDTO.getType().toUpperCase().equals("VARCHAR")) {
                sql += " (" + customComponentFieldDTO.getSize().toString().replace(" ", "") + ") ";
            }
            sql += "\n";

            fieldCounter++;
        }
        sql += " ; ";

        if(fieldCounter == 0) return;

        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    @Transactional
    public void createDatabaseTable(CustomComponentDTO customComponentDTO) {
        if(customComponentDTO.getCustomComponentFieldList().size() == 0) return;

        int fieldCounter = 0;
        String sql = "";
        sql += "CREATE TABLE IF NOT EXISTS " + customComponentDTO.getName().replace(" ", "");
        sql += " ( ";
        for (CustomComponentFieldDTO customComponentFieldDTO : customComponentDTO.getCustomComponentFieldList()) {
            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += customComponentFieldDTO.getName().replace(" ", "") + " ";
            sql += " " + customComponentFieldDTO.getType().replace(" ", "");
            if (customComponentFieldDTO.getType().toUpperCase().equals("VARCHAR")) {
                sql += " (" + customComponentFieldDTO.getSize().toString().replace(" ", "") + ") ";
            }
            sql += "\n";

            fieldCounter++;
        }
        sql += " ); ";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }

    public Boolean tableOnDatabase(String tableName) {
        List<String> tables = this.getTables();
        if (tables.contains(tableName)) return true;
        else return false;
    }
}
