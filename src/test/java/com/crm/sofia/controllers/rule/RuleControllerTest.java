package com.crm.sofia.controllers.rule;

import com.crm.sofia.dto.rule.RuleDTO;
import com.crm.sofia.dto.rule.RuleSettingsDTO;
import com.crm.sofia.mapper.rule.RuleMapper;
import com.crm.sofia.mapper.rule.RuleMapperImpl;
import com.crm.sofia.repository.rule.RuleRepository;
import com.crm.sofia.services.rule.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

@ExtendWith(MockitoExtension.class)
public class RuleControllerTest {

    @Spy
    private final RuleMapper ruleMapper = new RuleMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RuleService ruleService;

    private MockMvc mvc;

    private RuleDTO dto;

    private RuleSettingsDTO dto1;


    private List<RuleDTO> ruleDTOList;

    private List<RuleSettingsDTO> ruleSettingsDTOList;


    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private RuleController ruleController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.ruleDTOList = new ArrayList<>();
        this.ruleSettingsDTOList = new ArrayList<>();
        dto = new RuleDTO().setDescription("dummyDescription");
        dto.setId("1");
        dto1 = new RuleSettingsDTO().setName("dummyName");
        dto1.setId("1");
        this.ruleSettingsDTOList.add(dto1);
        this.ruleDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(ruleController).build();
    }

    @Test
    void getObjectByIdTest() throws Exception {
        given(ruleService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/rule/by-id?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }

    @Test
    void getObjectSettingsByIdTest() throws Exception {
        given(ruleService.getObjectSettings(any())).willReturn(dto1);
        MockHttpServletResponse response = mvc.perform(get("/rule/settings/by-id?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyName");
    }

    @Test
    void postObjectTest() throws Exception {
        given(ruleService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/rule")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }

    @Test
    void putObjectTest() throws Exception {
        given(ruleService.putObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/rule")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(ruleService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/rule?id=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());

    }
}
