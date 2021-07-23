package com.crm.sofia.controllers.sofia.xls_import;

import com.crm.sofia.services.sofia.xls_import.XlsImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@Validated
@RequestMapping("/xls-import")
public class XlsImportController {
    private final XlsImportService xlsImportService;

    public XlsImportController(XlsImportService xlsImportService) {
        this.xlsImportService = xlsImportService;
    }

    @PostMapping( produces = "application/text")
    public String postObject(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("id") Long id) throws IOException {
       return this.xlsImportService.importXls(multipartFile, id);
    }

}
