package com.crm.sofia.controllers.sofia.appview;

import com.crm.sofia.controllers.appview.AppViewController;
import com.crm.sofia.dto.sofia.appview.AppViewDTO;
import com.crm.sofia.dto.sofia.appview.AppViewFieldDTO;
import com.crm.sofia.filters.JWTAuthFilter;
import com.crm.sofia.services.sofia.appview.AppViewService;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class AppViewControllerTest {

    private MockMvc mvc;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private JWTAuthFilter filter;

    @Mock
    private AppViewService appViewService;

    @InjectMocks
    private AppViewController appViewController;

    private List<AppViewDTO> appViewDTOList;

    private AppViewDTO dto;

    private List<AppViewFieldDTO> appViewFieldDTOList;

    private AppViewFieldDTO appViewFieldDTO;



    @BeforeEach
    void setUp() {
        this.appViewDTOList = new ArrayList<>();
        this.appViewFieldDTOList = new ArrayList<>();
        dto = new AppViewDTO();
        dto.setName("dummy");
        dto.setDescription("app view");
        dto.setEntitytype("dummy");
        dto.setQuery("select * ");
        dto.setIndexes("12");
        dto.setCreatedBy("6l");
        dto.setCreatedOn(Instant.now());
        this.appViewDTOList.add(dto);

        appViewFieldDTO = new AppViewFieldDTO();
        appViewFieldDTO.setType("Demo");
        appViewFieldDTO.setDescription("Demo");
        appViewFieldDTO.setName("Test");
        appViewFieldDTO.setSize(5);
        appViewFieldDTO.setCreatedBy("5l");
        this.appViewFieldDTOList.add(appViewFieldDTO);

        mvc = MockMvcBuilders.standaloneSetup(appViewController)
                .build();
    }
    @Test
    void getObjectTest() throws Exception {

        given(appViewService.getObject()).willReturn(appViewDTOList);
        MockHttpServletResponse response = mvc.perform(get("/appview")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].name")  ,"dummy");
    }

    @Test
    void getDownloadByIdTest() throws Exception {
        given(appViewService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/appview/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"dummy");
    }
    @Test
    void postObjectTest() throws Exception {
        given(appViewService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/appview")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"dummy");
    }

    @Test
    void putObjectTest() throws Exception {
        given(appViewService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/appview")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"dummy");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(appViewService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/appview?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void getGenerateViewFieldsTest() throws Exception {
        given(appViewService.generateViewFields(any())).willReturn(appViewFieldDTOList);
        MockHttpServletResponse response = mvc.perform(get("/appview/generate-view-fields?query=select")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.[0].type")  ,"Demo");
    }
    @Test
    void chkTableExistsTest() throws Exception {
        given(appViewService.viewOnDatabase(any())).willReturn(Boolean.TRUE);
        MockHttpServletResponse response = mvc.perform(get("/appview/view-exists?name=demo")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString() ,"true");
    }


}
