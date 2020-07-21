package com.crm.sofia.controllers.list;

import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.services.list.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/list")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping
    List<ListDTO> getObject() {
        return this.listService.getObject();
    }

    @GetMapping(path="/by-id")
    ListDTO getObject(@RequestParam("id") Long id) {
        return this.listService.getObject(id);
    }

    @GetMapping(path="/by-name")
    ListDTO getObject(@RequestParam("name") String name) {
        return this.listService.getObjectByName(name);
    }

    @PostMapping
    public ListDTO postObject(@RequestBody ListDTO dto) {
        ListDTO createdDTO = this.listService.postObject(dto);
        return createdDTO;
    }


    @PutMapping
    public ListDTO putObject(@RequestBody ListDTO dto) {
        ListDTO createdDTO = this.listService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        this.listService.deleteObject(id);
    }

}
