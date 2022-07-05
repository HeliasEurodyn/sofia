package com.crm.sofia.services.sofia.table;

import com.crm.sofia.dto.sofia.table.TableDTO;
import com.crm.sofia.dto.sofia.table.TableFieldDTO;
import com.crm.sofia.mapper.sofia.table.TableMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import com.crm.sofia.repository.sofia.persistEntity.PersistEntityRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.component.ComponentDesignerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TableService {

    @Value("${sofia.db.name}")
    private String sofiaDatabase;

    private final PersistEntityRepository persistEntityRepository;
    private final TableMapper tableMapper;
    private final EntityManager entityManager;
    private final JWTService jwtService;
    private final ComponentDesignerService componentDesignerService;

    public TableService(PersistEntityRepository persistEntityRepository,
                        TableMapper tableMapper,
                        EntityManager entityManager, JWTService jwtService,
                        ComponentDesignerService componentDesignerService) {
        this.persistEntityRepository = persistEntityRepository;
        this.tableMapper = tableMapper;
        this.entityManager = entityManager;
        this.jwtService = jwtService;
        this.componentDesignerService = componentDesignerService;
    }

    public TableDTO postObject(TableDTO componentDTO) {
        PersistEntity persistEntity = this.tableMapper.map(componentDTO);

        if((persistEntity.getId() == null?"":persistEntity.getId()).equals("")){
            persistEntity.setCreatedBy(jwtService.getUserId());
            persistEntity.setCreatedOn(Instant.now());
        }
        persistEntity.setModifiedBy(jwtService.getUserId());
        persistEntity.setModifiedOn(Instant.now());

        persistEntity.getPersistEntityFieldList()
                .stream()
                .forEach(persistEntityField -> {
                    persistEntityField.setPersistEntity(persistEntity);
                });

        PersistEntity entity = this.persistEntityRepository.save(persistEntity);
        return this.tableMapper.map(entity);
    }

    public List<TableDTO> getObject() {
        List<PersistEntity> tables = this.persistEntityRepository.findByEntitytypeOrderByModifiedOn("Table");
        List<TableDTO> dtos = this.tableMapper.map(tables);
        dtos.forEach(tableDTO -> {
            this.shortTableFields(tableDTO);
        });
        return dtos;
    }

    public TableDTO getObject(String id) {
        Optional<PersistEntity> optionalComponent = this.persistEntityRepository.findById(id);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }

        TableDTO tableDTO = this.tableMapper.map(optionalComponent.get());
        this.shortTableFields(tableDTO);
        return tableDTO;
    }

    public void shortTableFields(TableDTO tableDTO) {
        tableDTO.getTableFieldList().stream()
                .filter(field -> field.getShortOrder() == null)
                .forEach(field -> field.setShortOrder(0L) );

        List<TableFieldDTO> shortedFieldList = tableDTO.getTableFieldList()
                .stream()
                .sorted(Comparator.comparingLong(TableFieldDTO::getShortOrder)).collect(Collectors.toList());
        tableDTO.setTableFieldList(shortedFieldList);
    }

    public void deleteObject(String id) {
        Optional<PersistEntity> optionalPersistEntity = this.persistEntityRepository.findById(id);
        if (!optionalPersistEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Persist entity does not exist");
        }
        this.componentDesignerService.removeComponentTablesByTableId(id);
        this.persistEntityRepository.deleteById(optionalPersistEntity.get().getId());
    }

    @Transactional
    public List<String> getTables() {
        Query query = entityManager.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='"+this.sofiaDatabase+"';");
        List<String> tableNames = query.getResultList();
        return tableNames;
    }

    public List<String> getExistingTableFields(String tableName) {
        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + tableName + " FROM "+this.sofiaDatabase+";");
        List<Object[]> fields = query.getResultList();
        List<String> fieldNames = fields.stream().map(f -> f[0].toString()).collect(Collectors.toList());

        return fieldNames;
    }

    @Transactional
    public void deteleDatabaseTable(String tableName) {
        Query query = entityManager.createNativeQuery("DROP TABLE " + tableName.replace(" ", "") + ";");
        query.executeUpdate();
    }

    public void updateDatabaseTable(TableDTO tableDTO) {
        List<String> existingTableFields = this.getExistingTableFields(tableDTO.getName().replace(" ", ""));
        int fieldCounter = 0;
        String sql = "";
        sql += "ALTER TABLE " + tableDTO.getName().replace(" ", "");
        sql += " \n";
        for (TableFieldDTO tableFieldDTO : tableDTO.getTableFieldList()) {

            if (existingTableFields.contains(tableFieldDTO.getName().replace(" ", ""))) {
                continue;
            }

            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += " ADD COLUMN ";
            sql += tableFieldDTO.getName().replace(" ", "") + " ";
            sql += " " + tableFieldDTO.getType()
                    .replace(" ", "")
                    .replace("datetime", "timestamp")
                    .replace("password", "varchar");

            if (Arrays.asList("varchar","password").contains(tableFieldDTO.getType())) {
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

    public void createDatabaseTableIfNotExist(TableDTO tableDTO) {
        if (tableDTO.getTableFieldList().size() == 0) return;

        int fieldCounter = 0;
        String sql = "";
        sql += "CREATE TABLE IF NOT EXISTS " + tableDTO.getName().replace(" ", "");
        sql += " ( ";
        for (TableFieldDTO tableFieldDTO : tableDTO.getTableFieldList()) {
            if (fieldCounter > 0) {
                sql += ",";
            }
            sql += tableFieldDTO.getName().replace(" ", "") + " ";
            sql += " " + tableFieldDTO.getType()
                    .replace(" ", "")
                    .replace("datetime", "timestamp")
                    .replace("password", "varchar");

            if (Arrays.asList("varchar","password").contains(tableFieldDTO.getType())) {
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

//    @Transactional
//    public TableDTO save(TableDTO dto) {
//        TableDTO createdDTO = this.postObject(dto);
//        this.createDatabaseTableIfNotExist(createdDTO);
//        return createdDTO;
//    }

    @Transactional
    public TableDTO save(TableDTO dto) {

        /**
         * Remove deleted Fields From Components
         */
        this.componentDesignerService.removeComponentTableFieldsByTable(
                dto.getId(),
                dto.getTableFieldList()
                        .stream()
                        .map( x -> x.getId())
                        .collect(Collectors.toList())
                );

        /**
         * Save DTO
         */
        TableDTO createdDTO = this.postObject(dto);

        /**
         * Create Database Table If Not Exists
         */
        this.createDatabaseTableIfNotExist(createdDTO);

        /**
         * If Database Table was already existing, Create new table fields that were not there
         */
        this.updateDatabaseTable(createdDTO);

        /**
         * Add new Fields From Components
         */
        this.componentDesignerService.insertComponentTableFieldsByTable(this.tableMapper.map(createdDTO));

        return createdDTO;
    }

    public List<TableFieldDTO> generateTableFields(String name) {
        List<TableFieldDTO> dtos = new ArrayList<>();

        if(!this.tableOnDatabase(name)){
            return dtos;
        }

        Query query = entityManager.createNativeQuery("SHOW COLUMNS FROM " + name + " FROM "+sofiaDatabase+";");
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
                if (type.equals("password")) type = "varchar";
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
