package com.crm.sofia.controllers.sofia.info_card;

import com.crm.sofia.dto.sofia.info_card.InfoCardDTO;
import com.crm.sofia.dto.sofia.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.services.sofia.info_card.InfoCardDesignerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/info-card-designer")
public class InfoCardDesignerController {

    private final InfoCardDesignerService infoCardDesignerService;

    public InfoCardDesignerController(InfoCardDesignerService infoCardDesignerService) {
        this.infoCardDesignerService = infoCardDesignerService;
    }

    @GetMapping
    List<InfoCardDTO> getObject() {
        return this.infoCardDesignerService.getObject();
    }

    @GetMapping(path = "/by-id")
    InfoCardDTO getObject(@RequestParam("id") String id) {
        return this.infoCardDesignerService.getObject(id);
    }

    @PostMapping
    public InfoCardDTO postObject(@RequestBody InfoCardDTO dto) {
        return this.infoCardDesignerService.postObject(dto);
    }

    @PutMapping
    public InfoCardDTO putObject(@RequestBody InfoCardDTO dto) {
        return this.infoCardDesignerService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        this.infoCardDesignerService.deleteObject(id);
    }

    @GetMapping(path = "/data")
    InfoCardTextResponceDTO getData(@RequestParam("query") String sql,
                                    @RequestParam Map<String, String> parameters) {
        return this.infoCardDesignerService.getData(sql, parameters);
    }

}
