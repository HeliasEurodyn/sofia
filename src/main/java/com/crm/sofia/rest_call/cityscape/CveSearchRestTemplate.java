package com.crm.sofia.rest_call.cityscape;

import com.crm.sofia.dto.cityscape.cve_search.VendorDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductCpeDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@Service
public class CveSearchRestTemplate {

    @Value("${cveSearchUrl}")
    private String cveSearchUrl;

    public VendorDTO getVendors(String vendor) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<VendorDTO> responce = restTemplate.exchange(
                    URI.create(cveSearchUrl + "/search-vendor/" + vendor),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<VendorDTO>() {
                    }
            );

            return responce.getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public VendorProductDTO getVendorProducts(String vendor, String product) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<VendorProductDTO> responce = restTemplate.exchange(
                    URI.create(cveSearchUrl + "/search-vendor/" + vendor + "/" + product),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<VendorProductDTO>() {
                    }
            );

            return responce.getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public VendorProductCpeDTO getVendorProductCpes(String vendor, String product) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<VendorProductCpeDTO> responce = restTemplate.exchange(
                    URI.create(cveSearchUrl + "/browse/" + vendor + "/" + product),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<VendorProductCpeDTO>() {
                    }
            );

            return responce.getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public Object getCvesByCpe(String cpe) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<Object> responce = restTemplate.exchange(
                    URI.create(cveSearchUrl + "/cvefor/" + cpe),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Object>() {
                    }
            );

            return responce.getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
