package com.crm.sofia.controllers.table;

import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.services.table.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@Validated
@RequestMapping("/table")
public class TableController {

    private final TableService componentService;

    public TableController(TableService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    List<TableDTO> getObject() {
        return this.componentService.getObject();
    }


    @PostMapping
    public TableDTO postObject(@RequestBody TableDTO dto) {
        TableDTO customComponentDTO = this.componentService.postObject(dto);
        this.componentService.createDatabaseTable(customComponentDTO);
        return customComponentDTO;
    }

    @PutMapping
    public TableDTO putObject(@RequestBody TableDTO componentDTO) {
        TableDTO customComponentDTO = this.componentService.putObject(componentDTO);
        List<TableFieldDTO> fields = this.componentService.putNewObjectFields(componentDTO);

        customComponentDTO.setTableFieldList(fields);
        this.componentService.updateDatabaseTable(customComponentDTO);

        return customComponentDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        TableDTO customComponentDTO=  this.componentService.getObject(id);
        this.componentService.deleteObject(id);
        this.componentService.deteleDatabaseTable(customComponentDTO.getName());
    }

    @GetMapping(path="/by-id")
    TableDTO getObject(@RequestParam("id") Long id) {
        return this.componentService.getObject(id);
    }


    @GetMapping(path = "/table-exists")
    public Boolean tableExists(@RequestParam("name") String tableName) {
        return componentService.tableOnDatabase(tableName);
    }

    @GetMapping(path = "/tables")
    public List<String> tables() {

        return componentService.getTables();
    }

    @GetMapping(path = "/fields")
    public List<String> fields() {

        return componentService.getTableFields("testtable");
    }


}
