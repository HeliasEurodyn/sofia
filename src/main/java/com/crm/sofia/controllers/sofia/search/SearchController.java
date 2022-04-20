package com.crm.sofia.controllers.sofia.search;

import com.crm.sofia.services.sofia.search.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(path = "/data")
    Object getData(@RequestParam("id") String id,@RequestParam("search") String search) {
        return searchService.getData(id, search);
    }
}
