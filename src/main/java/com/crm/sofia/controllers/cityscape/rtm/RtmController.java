package com.crm.sofia.controllers.cityscape.rtm;

import com.crm.sofia.dto.cityscape.rtm.RtmDTO;
import com.crm.sofia.services.cityscape.rtm.RtmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/rtm")
public class RtmController {

    private final RtmService rtmService;

    public RtmController(RtmService rtmService) {
        this.rtmService = rtmService;
    }

    @PostMapping
    public RtmDTO postObject(@RequestBody RtmDTO dto) {
        return this.rtmService.postObject(dto);
    }

    @PostMapping(path = "/risk-assessment")
    public RtmDTO postObject2(@RequestBody RtmDTO dto) {
        return this.rtmService.postObject2(dto);
    }

//    @PostMapping(path = "/run-risk-assessment")
//    public void runRiskAssessment(@RequestParam("id") Long id) {
//        this.rtmService.runRiskAssessment(id);
//    }

    @GetMapping
    public RtmDTO getObject(@RequestBody RtmDTO dto) {
        return this.rtmService.getObject(dto);
    }
}
