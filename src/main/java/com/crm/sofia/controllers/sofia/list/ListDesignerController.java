package com.crm.sofia.controllers.sofia.list;

import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.services.sofia.list.ListDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/clear-cache", method = RequestMethod.GET)
    boolean clearCache() {
        return this.listDesignerService.clearCache();
    }

}
