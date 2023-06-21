package com.crm.sofia.controllers.xls_import;

import com.crm.sofia.dto.xls_import.XlsImportDTO;
import com.crm.sofia.services.xls_import.XlsImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@Validated
@RequestMapping("/xls-import")
public class XlsImportController {
    @Autowired
    private XlsImportService xlsImportService;

    public XlsImportController(XlsImportService xlsImportService) {
        this.xlsImportService = xlsImportService;
    }

    @PostMapping
    public void postObject(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("id") String id) throws IOException {
       this.xlsImportService.importXls(multipartFile, id);
    }

    @GetMapping(path = "/by-id")
    XlsImportDTO getObject(@RequestParam("id") String id) {
        return this.xlsImportService.getObject(id);
    }

}
