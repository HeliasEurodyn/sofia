package com.crm.sofia.controllers.list;

import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.services.list.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/datalist")
public class ListDataController {

    private final ListService listService;

    public ListDataController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping(path = "/{jsonUrl}")
    public List<Map<String, Object>> getDatalist(@PathVariable("jsonUrl") String jsonUrl, @RequestParam Map<String, String> parameters) {
        ListDTO listDTO = this.listService.retrieveListWithBaseQueryByUrl(jsonUrl);
        return this.listService.getObjectDataByParameters(parameters,0L , listDTO).getListContent();
    }

    @GetMapping(path = "/{jsonUrl}/page/{page}")
    public ListResultsDataDTO getDatalistPage(
            @PathVariable("jsonUrl") String jsonUrl,
            @PathVariable("page") Long page,
            @RequestParam Map<String, String> parameters) {
        ListDTO listDTO = this.listService.retrieveListWithBaseQueryByUrl(jsonUrl);
        return this.listService.getObjectDataByParameters(parameters,page, listDTO);
    }
}
