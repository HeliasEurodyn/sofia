package com.crm.sofia.services.table;

import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.mapper.table.TableMapper;
import com.crm.sofia.model.table.Table;
import com.crm.sofia.repository.table.TableRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TableService {
    //    private final TableFieldService customComponentFieldService;
    private final TableRepository tableRepository;
    private final TableMapper componentMapper;
    private final EntityManager entityManager;

    public TableService(TableRepository tableRepository,
                        TableMapper componentMapper,
//                        TableFieldService customComponentFieldService,
                        EntityManager entityManager) {
        this.tableRepository = tableRepository;
//        this.customComponentFieldService = customComponentFieldService;
        this.componentMapper = componentMapper;
        this.entityManager = entityManager;
    }


    public TableDTO postObject(TableDTO componentDTO) {
        Table component = this.componentMapper.map(componentDTO);

        Table table = this.tableRepository.save(component);
        return this.componentMapper.map(table);
    }


//    public TableDTO putObject(TableDTO componentDTO) {
//
//        Optional<Table> optionalComponent = this.tableRepository.findById(componentDTO.getId());
//        if (!optionalComponent.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
//        }
//        Table table = optionalComponent.get();
//
//        componentMapper.setDtoToEntity(componentDTO, table);
//        Table createdTable = this.tableRepository.save(table);
//        TableDTO createdCustomComponentDTO = this.componentMapper.map(createdTable);
//
//        return createdCustomComponentDTO;
//    }


//    public List<TableFieldDTO> putNewObjectFields(TableDTO componentDTO) {
//        List<TableFieldDTO> createdTableFieldDTOS = new ArrayList<>();
//        for (TableFieldDTO tableFieldDTO : componentDTO.getTableFieldList()) {
//            TableFieldDTO createdTableFieldDTO = this.customComponentFieldService.saveCustomComponentField(tableFieldDTO, componentDTO.getId());
//            createdTableFieldDTOS.add(createdTableFieldDTO);
//        }
//
//        List<Long> ids = createdTableFieldDTOS.stream().map(TableFieldDTO::getId).collect(Collectors.toList());
//        this.customComponentFieldService.deleteObjectsNotInListForCustomComponent(ids, componentDTO.getId());
//
//        return createdTableFieldDTOS;
//    }


    public List<TableDTO> getObject() {
        List<Table> tables = this.tableRepository.findAll();
        return this.componentMapper.map(tables);
    }

    public TableDTO getObject(Long id) {
        Optional<Table> optionalComponent = this.tableRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        return this.componentMapper.map(optionalComponent.get());
    }

    public void deleteObject(Long id) {
        Optional<Table> optionalComponent = this.tableRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        this.tableRepository.deleteById(optionalComponent.get().getId());
    }

    @Transactional
    public List<String> getTables() {
        Query query = entityManager.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='sofia';");
        List<String> tableNames = query.getResultList();
        return tableNames;
    }


    public List<String> getTableFields(String tableName) {
        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + tableName + " FROM sofia;");
        List<Object[]> fields = query.getResultList();
        List<String> fieldNames = fields.stream().map(f -> f[0].toString()).collect(Collectors.toList());

        return fieldNames;
    }

    @Transactional
    public void deteleDatabaseTable(String tableName) {
        Query query = entityManager.createNativeQuery("DROP TABLE " + tableName.replace(" ", "") + ";");
        query.executeUpdate();
    }

    public void updateDatabaseTable(TableDTO customComponentDTO) {

        List<String> existingTableFields = this.getTableFields(customComponentDTO.getName().replace(" ", ""));
        int fieldCounter = 0;
        String sql = "";
        sql += "ALTER TABLE " + customComponentDTO.getName().replace(" ", "");
        sql += " \n";
        for (TableFieldDTO tableFieldDTO : customComponentDTO.getTableFieldList()) {

            if (existingTableFields.contains(tableFieldDTO.getName().replace(" ", ""))) {
                continue;
            }

            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += " ADD COLUMN ";
            sql += tableFieldDTO.getName().replace(" ", "") + " ";
            sql += " " + tableFieldDTO.getType().replace(" ", "");
            if (tableFieldDTO.getType().toUpperCase().equals("VARCHAR")) {
                sql += " (" + tableFieldDTO.getSize().toString().replace(" ", "") + ") ";
            }

            if (tableFieldDTO.getIsUnsigned()) {
                sql += " UNSIGNED ";
            }

            if (tableFieldDTO.getHasNotNull()) {
                sql += " NOT NULL ";
            }

            if (tableFieldDTO.getHasDefault()) {
                sql += " DEFAULT " + tableFieldDTO.getDefaultValue();
            }

            if (tableFieldDTO.getAutoIncrement()) {
                sql += " AUTO_INCREMENT ";
            }

            if (tableFieldDTO.getPrimaryKey()) {
                sql += " PRIMARY KEY ";
            }

            sql += "\n";

            fieldCounter++;
        }
        sql += " ; ";

        if (fieldCounter == 0) return;

        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }


    public void createDatabaseTable(TableDTO customComponentDTO) {
        if (customComponentDTO.getTableFieldList().size() == 0) return;

        int fieldCounter = 0;
        String sql = "";
        sql += "CREATE TABLE IF NOT EXISTS " + customComponentDTO.getName().replace(" ", "");
        sql += " ( ";
        for (TableFieldDTO tableFieldDTO : customComponentDTO.getTableFieldList()) {
            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += tableFieldDTO.getName().replace(" ", "") + " ";
            sql += " " + tableFieldDTO.getType().replace(" ", "");
            if (tableFieldDTO.getType().toUpperCase().equals("VARCHAR")) {
                sql += " (" + tableFieldDTO.getSize().toString().replace(" ", "") + ") ";
            }

            if (tableFieldDTO.getIsUnsigned()) {
                sql += " UNSIGNED ";
            }

            if (tableFieldDTO.getHasNotNull()) {
                sql += " NOT NULL ";
            }

            if (tableFieldDTO.getHasDefault()) {
                sql += " DEFAULT " + tableFieldDTO.getDefaultValue();
            }

            if (tableFieldDTO.getAutoIncrement()) {
                sql += " AUTO_INCREMENT ";
            }

            if (tableFieldDTO.getPrimaryKey()) {
                sql += " PRIMARY KEY ";
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

    @Transactional
    public TableDTO save(TableDTO dto) {
        TableDTO createdDTO = this.postObject(dto);
        this.createDatabaseTable(createdDTO);
        return createdDTO;

    }

    @Transactional
    public TableDTO update(TableDTO dto) {
        TableDTO createdDTO = this.postObject(dto);
        this.updateDatabaseTable(createdDTO);
        return createdDTO;
    }

    public List<TableFieldDTO> generateTableFields(String name) {
        List<TableFieldDTO> dtos = new ArrayList<>();

        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + name + " FROM sofia;");
        List<Object[]> fields = query.getResultList();

        for (Object[] field : fields) {
            TableFieldDTO dto = new TableFieldDTO();
            dto.setName(field[0].toString());
            dto.setDescription("");
            dto.setType(field[1].toString());
            dto.setEntitytype("TableField");

            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(field[1].toString());
            while (m.find()) {
                dto.setSize(Integer.valueOf(m.group(1)));
            }

            int index = field[1].toString().indexOf("(");
            if (index > 0) {
                String type = field[1].toString().substring(0, index);
                dto.setType(type);
            } else {
                String type = field[1].toString();
                if (type.equals("timestamp")) type = "datetime";
                dto.setType(type);
            }

            String isNullField = field[2].toString();
            if (isNullField.equals("NO")) dto.setHasNotNull(true);
            else dto.setHasNotNull(false);

            String keyField = field[3].toString();
            if (keyField.equals("PRI")) dto.setPrimaryKey(true);
            else dto.setPrimaryKey(false);

            if (field[4] != null) {
                dto.setDefaultValue(field[4].toString());
                dto.setHasDefault(true);
            } else {
                dto.setHasDefault(false);
            }
            String extraField = field[5].toString();

            if (extraField.equals("auto_increment")) dto.setAutoIncrement(true);
            else dto.setAutoIncrement(false);

            dto.setIsUnsigned(false);

            dtos.add(dto);
        }

        return dtos;
    }
}
