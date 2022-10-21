package com.crm.sofia.controllers.business_unit;

import com.crm.sofia.dto.sofia.business_unit.BusinessUnitDTO;
import com.crm.sofia.services.sofia.business_unit.BusinessUnitDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/business-unit-designer")
public class BusinessUnitDesignerController {

    @Autowired
    private BusinessUnitDesignerService businessUnitDesignerService;

    @GetMapping
    List<BusinessUnitDTO> getObject() {
        return businessUnitDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    BusinessUnitDTO getObject(@RequestParam("id") String id) {
        return businessUnitDesignerService.getObject(id);
    }

    @PostMapping
    public BusinessUnitDTO postObject(@RequestBody BusinessUnitDTO businessUnitDTO) throws IOException {
        return businessUnitDesignerService.postObject(businessUnitDTO);
    }

    @PutMapping
    public BusinessUnitDTO putObject(@RequestBody BusinessUnitDTO businessUnitDTO) {
        return businessUnitDesignerService.postObject(businessUnitDTO);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        businessUnitDesignerService.deleteObject(id);
    }

}
