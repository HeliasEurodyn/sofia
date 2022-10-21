package com.crm.sofia.controllers.download;

import com.crm.sofia.dto.download.DownloadDTO;
import com.crm.sofia.services.download.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@Validated
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping
    List<DownloadDTO> getObject() {
        return downloadService.getObject();
    }

    @GetMapping(path = "/by-id")
    DownloadDTO getObject(@RequestParam("id") String id) {
        return downloadService.getObject(id);
    }

    @PostMapping
    public DownloadDTO postObject(@RequestBody DownloadDTO downloadDto) throws IOException {
        return downloadService.postObject(downloadDto);
    }

    @PutMapping
    public DownloadDTO putObject(@RequestBody DownloadDTO downloadDto) {
        return downloadService.postObject(downloadDto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        downloadService.deleteObject(id);
    }

}
