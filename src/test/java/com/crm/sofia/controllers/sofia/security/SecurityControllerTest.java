package com.crm.sofia.controllers.sofia.security;

import com.crm.sofia.controllers.security.SecurityController;
import com.crm.sofia.dto.sofia.security.SecurityDTO;
import com.crm.sofia.filters.JWTAuthFilter;
import com.crm.sofia.services.sofia.security.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(MockitoExtension.class)

public class SecurityControllerTest {

    private MockMvc mvc;
    private SecurityDTO dto;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private SecurityService securityService;

    private List<SecurityDTO> securityDTOList;

    @Mock
    private JWTAuthFilter filter;

    @InjectMocks
    private SecurityController securityController;

    @BeforeEach
    void setUp() {
        this.securityDTOList = new ArrayList<>();
        dto = new SecurityDTO();
        dto.setRead(Boolean.TRUE);
        dto.setCreate(Boolean.TRUE);
        dto.setType("Admin");
        dto.setUpdate(Boolean.FALSE);
        this.securityDTOList.add(dto);
        mvc = MockMvcBuilders.standaloneSetup(securityController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {

        given(securityService.getObject()).willReturn(securityDTOList);
        MockHttpServletResponse response = mvc.perform(get("/security")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].read")  ,Boolean.TRUE);
    }

    @Test
    void getSecurityByIdTest() throws Exception {
        given(securityService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/security/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.read")  ,Boolean.TRUE);
    }

    @Test
    void postObjectTest() throws Exception {
        given(securityService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/security")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.type")  ,"Admin");
    }
    @Test
    void putObjectTest() throws Exception {
        given(securityService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/security")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.read")  ,Boolean.TRUE);
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(securityService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/security?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }


}
