package com.crm.sofia.controllers.data_transfer;

import com.crm.sofia.dto.sofia.data_transfer.DataTransferDTO;
import com.crm.sofia.services.data_transfer.DataTransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/data-transfer")
public class DataTransferController {

    @Autowired
    private DataTransferService dataTransferService;

    @GetMapping
    List<DataTransferDTO> getObject() {
        return dataTransferService.getObject();
    }

    @GetMapping(path = "/by-id")
    DataTransferDTO getObject(@RequestParam("id") String id) {
        return dataTransferService.getObject(id);
    }

    @PostMapping
    public DataTransferDTO postObject(@RequestBody DataTransferDTO dto) throws IOException {
        return dataTransferService.postObject(dto);
    }

    @PutMapping
    public DataTransferDTO putObject(@RequestBody DataTransferDTO dto) {
        return dataTransferService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        dataTransferService.deleteObject(id);
    }

    @GetMapping(path = "/export", produces = "text/plain")
    byte[] export(@RequestParam("id") String id) throws IOException, ClassNotFoundException {
        return dataTransferService.export(id);
    }

    @PostMapping(path = "/import")
    public void postObject(
            @RequestParam("file") MultipartFile multipartFile) throws Exception {
        this.dataTransferService.importMultipartFile(multipartFile);
    }
}
