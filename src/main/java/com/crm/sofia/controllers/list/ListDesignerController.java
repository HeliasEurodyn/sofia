package com.crm.sofia.controllers.list;

import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.services.list.ListDesignerService;
import com.crm.sofia.services.list.ListService;
import com.crm.sofia.utils.ExcelGenerator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/list-designer")
public class ListDesignerController {

    private final ListDesignerService listDesignerService;

    public ListDesignerController(ListDesignerService listDesignerService) {
        this.listDesignerService = listDesignerService;
    }

    @GetMapping
    List<ListDTO> getObject() {
        return this.listDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    ListDTO getObject(@RequestParam("id") Long id) {
        return this.listDesignerService.getObject(id);
    }

    @GetMapping(path = "/by-name")
    ListDTO getObject(@RequestParam("name") String name) {
        return this.listDesignerService.getObjectByName(name);
    }

    @PostMapping
    public ListDTO postObject(@RequestBody ListDTO dto) {
        ListDTO createdDTO = this.listDesignerService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public ListDTO putObject(@RequestBody ListDTO dto) {
        ListDTO createdDTO = this.listDesignerService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.listDesignerService.deleteObject(id);
    }

}
