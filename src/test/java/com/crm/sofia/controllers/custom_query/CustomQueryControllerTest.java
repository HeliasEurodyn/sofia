package com.crm.sofia.controllers.custom_query;


import com.crm.sofia.dto.custom_query.CustomQueryDTO;
import com.crm.sofia.mapper.custom_query.CustomQueryMapper;
import com.crm.sofia.mapper.custom_query.CustomQueryMapperImpl;
import com.crm.sofia.repository.custom_query.CustomQueryRepository;
import com.crm.sofia.services.custom_query.CustomQueryService;
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
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class CustomQueryControllerTest {

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Spy
    private final CustomQueryMapper customQueryMapper = new CustomQueryMapperImpl();

    @Mock
    private CustomQueryService customQueryService;

    private MockMvc mvc;

    private CustomQueryDTO dto;

    private List<CustomQueryDTO> customQueryDTOList;


    @Mock
    private CustomQueryRepository customQueryRepository;

    @InjectMocks
    private CustomQueryController customQueryController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.customQueryDTOList = new ArrayList<>();
        dto = new CustomQueryDTO().setName("dummyDTO").setQuery("dummyQuery");
        dto.setId("1");
        this.customQueryDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(customQueryController).build();
    }

    @Test
    void getDataByIdTest() throws Exception {
        given(customQueryService.getData(anyString(), anyMap())).willReturn(Optional.of(dto));
        MockHttpServletResponse response = mvc.perform(get("/custom-query/data?id=1&param1=value1&param2=value2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyDTO");
    }

    @Test
    void getDataObjectByIdTest() throws Exception {
        given(customQueryService.getDataObjects(anyString(), anyMap())).willReturn(Optional.of(dto));
        MockHttpServletResponse response = mvc.perform(get("/custom-query/data-objects?id=1&param1=value1&param2=value2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyDTO");
    }

    @Test
    void postObjectTest() throws Exception {
        given(customQueryService.postData(anyString(), anyMap())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(post("/custom-query/data?id=1&param1=value1&param2=value2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "{\"response\": \"ok\"}");
    }

    @Test
    void postDataObjectTest() throws Exception {
        given(customQueryService.postDataObjects(anyString(), anyMap())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(post("/custom-query/data-objects?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("value1","bbb" )))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "{\"response\": \"ok\"}");
    }


}
