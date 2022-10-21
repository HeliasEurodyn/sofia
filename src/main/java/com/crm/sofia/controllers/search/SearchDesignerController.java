package com.crm.sofia.controllers.search;

import com.crm.sofia.dto.sofia.search.SearchDTO;
import com.crm.sofia.services.sofia.search.SearchDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/search-designer")
public class SearchDesignerController {

    private final SearchDesignerService searchDesignerService;

    public SearchDesignerController(SearchDesignerService searchDesignerService) {
        this.searchDesignerService = searchDesignerService;
    }

    @GetMapping
    List<SearchDTO> getObject() {
        return this.searchDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    SearchDTO getObject(@RequestParam("id") String id) {
        return this.searchDesignerService.getObject(id);
    }

    @PostMapping
    public SearchDTO postObject(@RequestBody SearchDTO dto) {
        return this.searchDesignerService.postObject(dto);
    }

    @PutMapping
    public SearchDTO putObject(@RequestBody SearchDTO dto) {
        return this.searchDesignerService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.searchDesignerService.deleteObject(id);
    }

}
