package com.crm.sofia.controllers.view;

import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.dto.view.ViewFieldDTO;
import com.crm.sofia.services.table.TableService;
import com.crm.sofia.services.view.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/view")
public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping
    List<ViewDTO> getObject() {
        return this.viewService.getObject();
    }


    @GetMapping(path="/generate-view-fields")
    List<ViewFieldDTO> generateViewFields(@RequestParam("query") String query) {
        return this.viewService.generateViewFields(query);
    }




    @PostMapping
    public ViewDTO postObject(@RequestBody ViewDTO dto) {
        ViewDTO customComponentDTO = this.viewService.postObject(dto);
        this.viewService.createDatabaseView(customComponentDTO);
        return customComponentDTO;
    }

    @PutMapping
    public ViewDTO putObject(@RequestBody ViewDTO viewDTO) {
        ViewDTO customComponentDTO = this.viewService.postObject(viewDTO);
        return customComponentDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        ViewDTO customComponentDTO=  this.viewService.getObject(id);
        this.viewService.deleteObject(id);
        this.viewService.deteleDatabaseView(customComponentDTO.getName());
    }

    @GetMapping(path="/by-id")
    ViewDTO getObject(@RequestParam("id") Long id) {
        return this.viewService.getObject(id);
    }


    @GetMapping(path = "/view-exists")
    public Boolean tableExists(@RequestParam("name") String tableName) {
        return viewService.viewOnDatabase(tableName);
    }





}
