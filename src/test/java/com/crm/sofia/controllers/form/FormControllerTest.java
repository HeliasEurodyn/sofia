package com.crm.sofia.controllers.form;

import com.crm.sofia.controllers.dashboard.DashboardController;
import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.user.ComponentUiDTO;
import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.dto.form.user.FormUiDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.mapper.component.ComponentMapperImpl;
import com.crm.sofia.mapper.component.ComponentUiMapper;
import com.crm.sofia.mapper.component.ComponentUiMapperImpl;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.mapper.dashboard.DashboardMapperImpl;
import com.crm.sofia.mapper.form.designer.FormMapper;
import com.crm.sofia.mapper.form.designer.FormMapperImpl;
import com.crm.sofia.repository.dashboard.DashboardRepository;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.dashboard.DashboardService;
import com.crm.sofia.services.form.FormService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class FormControllerTest {

    @Spy
    private final FormMapper formMapper = new FormMapperImpl();

    @Spy
    private final ComponentMapper componentMapper = new ComponentMapperImpl();

    @Spy
    private final ComponentUiMapper componentUiMapper = new ComponentUiMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    private FormService formService;

    private MockMvc mvc;

    private FormDTO dto;


    private FormUiDTO uiDto;

    private ComponentDTO componentDTO;

    private ComponentUiDTO componentUiDTO;

    private List<ComponentUiDTO> componentUiDTOList;

    private List<FormDTO> formDTOList;

    private List<FormUiDTO> formUiDTOList;



    @Mock
    private FormRepository formRepository;

    @InjectMocks
    private FormController formController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.formDTOList = new ArrayList<>();
        formUiDTOList = new ArrayList<>();
        componentUiDTOList = new ArrayList<>();
        uiDto = new FormUiDTO().setName("dummyName").setDescription("dummyDescription");
        uiDto.setId("1");
        componentUiDTO = new ComponentUiDTO().setId("1").setComponentPersistEntityList(new ArrayList<>());
        componentUiDTOList.add(componentUiDTO);
        this.formUiDTOList.add(uiDto);
        FormDTO formDTO = new FormDTO().setComponent(componentDTO);
        formDTO.setId("1");





        mvc = MockMvcBuilders.standaloneSetup(formController).build();
    }

    @Test
    void getUiObjectTest() throws Exception {
        given(formService.getUiObject(anyString(),anyString())).willReturn(uiDto);
        MockHttpServletResponse response = mvc.perform(get("/form/ui?id=1&language-id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyName");
    }

    @Test
    void getDataTest() throws Exception {
        given(formService.retrieveUiData(any(),anyString())).willReturn(componentUiDTO);
        MockHttpServletResponse response = mvc.perform(get("/form/data?id=1&selection-id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.id"), "1");
    }

    @Test
    void getCloneDataTest() throws Exception {
        given(formService.retrieveClonedData(any(),anyString())).willReturn(componentUiDTO);
        MockHttpServletResponse response = mvc.perform(get("/form/clone-data?id=1&selection-id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.id"), "1");
    }

    @Test
    void getInstanceVersionTest() throws Exception {
        given(formService.getInstanceVersion(anyString())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(get("/form/instance-version?id=1")
                .accept("text/plain"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "ok");
    }

    @Test
    void postObjectDataTest() throws Exception {
        given(formService.save(any(),anyMap())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/form?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("value1",Map.of("value1","bbb" ))))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "ok");
    }

    @Test
    void putObjectDataTest() throws Exception {
        given(formService.save(any(),anyMap())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.put("/form?id=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("value1",Map.of("value1","bbb" ))))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "ok");
    }

    @Test
    void deleteObjectDataTest() throws Exception {
        doNothing().when(formService).delete(any(), anyString());
        MockHttpServletResponse response = mvc.perform(delete("/form?id=0&selection-id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}
