package com.crm.sofia.controllers.rest_documentation;

import com.crm.sofia.dto.rest_documentation.RestDocumentationDTO;
import com.crm.sofia.services.rest_documentation.RestDocumentationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/rest-documentation")
public class RestDocumentationController {

    @Autowired
    RestDocumentationService restDocumentationService;

    @GetMapping
    List<RestDocumentationDTO> getObject() {return restDocumentationService.getObject();}


    @GetMapping(path = "/by-id")
    RestDocumentationDTO getObject(@RequestParam("id") String id){return restDocumentationService.getObject(id);}



    @PostMapping
    public RestDocumentationDTO postObject(@RequestBody RestDocumentationDTO restDocumentationDTO) throws IOException {
        return restDocumentationService.postObject(restDocumentationDTO);
    }


    @PutMapping
    public RestDocumentationDTO putObject(@RequestBody RestDocumentationDTO restDocumentationDTO){
        return restDocumentationService.postObject(restDocumentationDTO);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {restDocumentationService.deleteObject(id);}
}
