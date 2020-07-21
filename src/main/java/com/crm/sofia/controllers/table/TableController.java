package com.crm.sofia.controllers.table;

import com.crm.sofia.dto.table.TableDTO;
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

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    List<TableDTO> getObject() {
        return this.tableService.getObject();
    }


    @PostMapping
    public TableDTO postObject(@RequestBody TableDTO dto) {
        TableDTO customComponentDTO = this.tableService.save(dto);
        return customComponentDTO;

    }

    @PutMapping
    public TableDTO putObject(@RequestBody TableDTO dto) {
        TableDTO customComponentDTO = this.tableService.update(dto);
        return customComponentDTO;

    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        TableDTO dto =  this.tableService.getObject(id);
        this.tableService.deleteObject(id);
        this.tableService.deteleDatabaseTable(dto.getName());
    }

    @GetMapping(path="/by-id")
    TableDTO getObject(@RequestParam("id") Long id) {
        return this.tableService.getObject(id);
    }


    @GetMapping(path = "/table-exists")
    public Boolean tableExists(@RequestParam("name") String tableName) {
        return tableService.tableOnDatabase(tableName);
    }

    @GetMapping(path = "/tables")
    public List<String> tables() {

        return tableService.getTables();
    }

    @GetMapping(path = "/fields")
    public List<String> fields() {

        return tableService.getTableFields("testtable");
    }


}
