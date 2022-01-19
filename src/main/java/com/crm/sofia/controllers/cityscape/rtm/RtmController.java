package com.crm.sofia.controllers.cityscape.rtm;

import com.crm.sofia.dto.cityscape.rtm.RtmDTO;
import com.crm.sofia.services.cityscape.rtm.RtmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/rtm")
public class RtmController {

    private final RtmService rtmService;

    public RtmController(RtmService rtmService) {
        this.rtmService = rtmService;
    }

//    @GetMapping(path = "/test")
//    public RtmDTO getObject(@RequestBody RtmDTO dto) {
//        return this.rtmService.getObject(dto);
//    }

    @GetMapping(path = "/list")
    public List<RtmDTO> getObjectById() {
        return this.rtmService.runRiskAssessmentList();
    }

    @GetMapping(value = "/{id}")
    public RtmDTO getObjectById(@PathVariable("id") Long id) {
        return this.rtmService.runRiskAssessment(id);
    }


}
