package com.crm.sofia.rest_template.sofia;

import com.crm.sofia.dto.cityscape.cve_search.VendorDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@Service
public class Github {

    public Object getEmail(String token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","token " + token);
            headers.add("Accept-Language","en-us");
            headers.add("Accept","application/json");
            headers.add("Accept-Encoding","gzip, deflate");

            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<Object> responce = restTemplate.exchange(
                    URI.create("https://api.github.com/user/emails"),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Object>() {
                    }
            );
            Object respData = responce.getBody();
            return responce.getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
