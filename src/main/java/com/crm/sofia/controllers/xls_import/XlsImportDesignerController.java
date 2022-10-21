package com.crm.sofia.controllers.xls_import;

import com.crm.sofia.dto.sofia.xls_import.XlsImportDTO;
import com.crm.sofia.services.sofia.xls_import.XlsImportDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/xls-import-designer")
public class XlsImportDesignerController {
    private final XlsImportDesignerService xlsImportDesignerService;

    public XlsImportDesignerController(XlsImportDesignerService xlsImportDesignerService) {
        this.xlsImportDesignerService = xlsImportDesignerService;
    }

    @GetMapping
    List<XlsImportDTO> getObject() {
        return this.xlsImportDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    XlsImportDTO getObject(@RequestParam("id") String id) {
        return this.xlsImportDesignerService.getObject(id);
    }

    @PostMapping
    public XlsImportDTO postObject(@RequestBody XlsImportDTO dto) {
        return this.xlsImportDesignerService.postObject(dto);
    }

    @PutMapping
    public XlsImportDTO putObject(@RequestBody XlsImportDTO dto) {
        return this.xlsImportDesignerService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.xlsImportDesignerService.deleteObject(id);
    }


}
