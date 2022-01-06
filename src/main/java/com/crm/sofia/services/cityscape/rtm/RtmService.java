package com.crm.sofia.services.cityscape.rtm;

import com.crm.sofia.dto.cityscape.rtm.RtmDTO;
import com.crm.sofia.rest_template.cityscape.RtmRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class RtmService {

    private final RtmRestTemplate rtmRestTemplate;

    public RtmService(RtmRestTemplate rtmRestTemplate) {
        this.rtmRestTemplate = rtmRestTemplate;
    }

    public RtmDTO postObject(RtmDTO dto) {
        return rtmRestTemplate.runRiskAssessment(dto);
    }

    public RtmDTO postObject2(RtmDTO dto) {
        return dto;
    }

    public RtmDTO getObject(RtmDTO dto) {
        return dto;
    }
}
