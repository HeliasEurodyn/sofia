package com.crm.sofia.controllers.appview;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.appview.AppViewFieldDTO;
import com.crm.sofia.services.appview.AppViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@Validated
@RequestMapping("/appview")
public class AppViewController {

    private final AppViewService appViewService;

    public AppViewController(AppViewService appViewService) {
        this.appViewService = appViewService;
    }

    @GetMapping
    List<AppViewDTO> getObject() {
        return this.appViewService.getObject();
    }


    @GetMapping(path = "/generate-view-fields")
    List<AppViewFieldDTO> generateViewFields(@RequestParam("query") String query) {
        return this.appViewService.generateViewFields(query);
    }


    @PostMapping
    public AppViewDTO postObject(@RequestBody AppViewDTO dto) {
        AppViewDTO customComponentDTO = this.appViewService.postObject(dto);
        this.appViewService.createDatabaseView(customComponentDTO);
        return customComponentDTO;
    }

    @PutMapping
    public AppViewDTO putObject(@RequestBody AppViewDTO viewDTO) {
        AppViewDTO customComponentDTO = this.appViewService.postObject(viewDTO);
        return customComponentDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        AppViewDTO customComponentDTO = this.appViewService.getObject(id);
        this.appViewService.deleteObject(id);
        this.appViewService.deteleDatabaseView(customComponentDTO.getName());
    }

    @GetMapping(path = "/by-id")
    AppViewDTO getObject(@RequestParam("id") Long id) {
        return this.appViewService.getObject(id);
    }


    @GetMapping(path = "/view-exists")
    public Boolean tableExists(@RequestParam("name") String tableName) {
        return appViewService.viewOnDatabase(tableName);
    }

}
