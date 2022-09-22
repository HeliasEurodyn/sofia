package com.crm.sofia.rest_template.cityscape;

import com.crm.sofia.dto.cityscape.rtm.RmtDTO;
import com.crm.sofia.dto.sofia.auth.LoginDTO;
import com.crm.sofia.dto.sofia.auth.RmtLoginResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Arrays;

@Slf4j
@Service
public class RtmRestTemplate {

    @Value("${rmtUrl}")
    private String rtmUrl;

    public RmtDTO analysis(RmtDTO rmtDTO, String jwt)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Content-Type", "application/json");
            headers.add("Authorization", "Bearer " + jwt);
            HttpEntity<RmtDTO> httpEntity = new HttpEntity<RmtDTO>(rmtDTO, headers);
            RmtDTO responce = restTemplate.postForObject(
                    URI.create(rtmUrl + "/riskAnalysis"),
                    httpEntity,
                    RmtDTO.class
            );

            return responce;
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public RmtLoginResponseDTO login()
    {
        try {
            LoginDTO login = new LoginDTO();
            login.setUsername("admin");
            login.setPassword("admin");

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Content-Type", "application/json");
            HttpEntity<LoginDTO> httpEntity = new HttpEntity<LoginDTO>(login,headers);
            RmtLoginResponseDTO responce = restTemplate.postForObject(
                    URI.create(rtmUrl + "/login"),
                    httpEntity,
                    RmtLoginResponseDTO.class
            );

            return responce;
        }catch(Exception ex){
            log.error("Rmt Login Exception occured", ex);
            return null;
        }
    }

    public RmtDTO insertThreat(RmtDTO rmtDTO, String jwt)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Content-Type", "application/json");
            headers.add("Authorization", "Bearer " + jwt);
            HttpEntity<RmtDTO> httpEntity = new HttpEntity<RmtDTO>(rmtDTO, headers);
            RmtDTO responce = restTemplate.postForObject(
                    URI.create(rtmUrl + "/riskAnalysis"),
                    httpEntity,
                    RmtDTO.class
            );

            return responce;
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
