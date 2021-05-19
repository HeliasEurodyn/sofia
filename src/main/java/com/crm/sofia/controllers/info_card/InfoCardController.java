package com.crm.sofia.controllers.info_card;

import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.dto.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.services.info_card.InfoCardDesignerService;
import com.crm.sofia.services.info_card.InfoCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/info-card")
public class InfoCardController {

    private final InfoCardService infoCardService;

    public InfoCardController(InfoCardService infoCardService) {
        this.infoCardService = infoCardService;
    }

    @GetMapping(path = "/by-id")
    InfoCardDTO getObject(@RequestParam("id") Long id) {
        return this.infoCardService.getObject(id);
    }

//    @GetMapping(path = "/data")
//    InfoCardTextResponceDTO getData(@RequestParam("query") String sql) {
//        return this.infoCardService.getData(sql);
//    }

}
