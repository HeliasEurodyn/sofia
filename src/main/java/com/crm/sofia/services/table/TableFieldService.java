package com.crm.sofia.services.table;

import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.mapper.table.TableFieldMapper;
import com.crm.sofia.model.table.Table;
import com.crm.sofia.model.table.TableField;
import com.crm.sofia.repository.table.TableFieldRepository;
import com.crm.sofia.repository.table.TableRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TableFieldService {

    private final TableRepository tableRepository;
    private final TableFieldRepository tableFieldRepository;
    private final TableFieldMapper componentFieldMapper;


    public TableFieldService(TableRepository tableRepository,
                             TableFieldRepository tableFieldRepository,
                             TableFieldMapper componentFieldMapper) {
        this.tableRepository = tableRepository;
        this.tableFieldRepository = tableFieldRepository;
        this.componentFieldMapper = componentFieldMapper;
    }

    public TableFieldDTO saveCustomComponentField(TableFieldDTO tableFieldDTO, Long customComponentId) {
        Optional<Table> optionalComponent = this.tableRepository.findById(customComponentId);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        Table table = optionalComponent.get();

        TableField tableField;
        Long id = 0L;
        if (tableFieldDTO.getId() != null) id = tableFieldDTO.getId();
        Optional<TableField> optionalCustomComponentField = this.tableFieldRepository.findById(id);

        if (optionalCustomComponentField.isPresent()) {
            tableField = optionalCustomComponentField.get();
            componentFieldMapper.setDtoToEntity(tableFieldDTO, tableField);

        } else {
            tableField = componentFieldMapper.map(tableFieldDTO);
            tableField.setId(null);
        }
        tableField.setTable(table);
        return componentFieldMapper.map(this.tableFieldRepository.save(tableField));
    }


    public void deleteObjectsNotInListForCustomComponent(List<Long> ids, Long id) {
        tableFieldRepository.deleteObjectsNotInListForCustomComponent(ids,id);
    }
}



