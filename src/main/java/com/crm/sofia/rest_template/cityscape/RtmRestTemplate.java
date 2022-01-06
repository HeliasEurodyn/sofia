package com.crm.sofia.rest_template.cityscape;

import com.crm.sofia.dto.cityscape.cve_search.VendorDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductCpeDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductDTO;
import com.crm.sofia.dto.cityscape.rtm.RtmDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class RtmRestTemplate {

    @Value("${rtmUrl}")
    private String rtmUrl;

    public RtmDTO runRiskAssessment(RtmDTO rtmDTO)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Content-Type", "application/json");
            headers.add("Authorization",
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyMCIsImlhdCI6MTY0MTIyNDIwOCwiZXhwIjoxNjQyMDg4MjA4fQ.xBOJoypSEDMXqabuCR3qk_wFQIWBGcoc4cw-xuQ3xDyGEPTcxgdjTLLzbPZwcIChrGEjR_ogUVNpsyTbfxzhqQ");
            HttpEntity<RtmDTO> httpEntity = new HttpEntity<RtmDTO>(rtmDTO, headers);
            RtmDTO responce = restTemplate.postForObject(
                    URI.create(rtmUrl + "/rtm/risk-assessment/"),
                    httpEntity,
                    RtmDTO.class
            );

            return responce;
        }catch(Exception ex){
            log.error("Exception Ocurred on runRiskAssessment Rest Template", ex);
            return null;
        }
    }

}
