package com.crm.sofia.controllers.info_card;

import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.services.info_card.InfoCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Validated
@RequestMapping("/info-card")
public class InfoCardController {
    @Autowired
    private InfoCardService infoCardService;
    @Autowired
    public InfoCardController(InfoCardService infoCardService) {
        this.infoCardService = infoCardService;
    }

    @GetMapping(path = "/by-id")
    InfoCardDTO getObject(@RequestParam("id") String id,
                          @RequestParam Map<String, String> parameters) {
        return this.infoCardService.getObject(id, parameters);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/min/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getMinJavaScript(@PathVariable("id") String id) {
        return this.infoCardService.getMinJavaScript(id);
    }

    @RequestMapping(value = "/dynamic-javascript/{id}/script.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getJavaScript(@PathVariable("id") String id) {
        return this.infoCardService.getJavaScript(id);
    }

    @Transactional
    @RequestMapping(value = "/dynamic-javascripts/factory.js", method = RequestMethod.GET, produces = "text/javascript;")
    String getFormJavaScripty() {
        return this.infoCardService.getJavaScriptFactory();
    }

}
