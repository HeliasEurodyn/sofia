package com.crm.sofia.controllers.sofia.timeline;

import com.crm.sofia.dto.sofia.download.DownloadDTO;
import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.filters.JWTAuthFilter;
import com.crm.sofia.services.sofia.timeline.TimelineService;
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
public class TimelineControllerTest {

    private MockMvc mvc;

    private TimelineDTO dto;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private TimelineService timelineService;

    private List<TimelineDTO> timelineDTOList;

    @Mock
    private JWTAuthFilter filter;

    @InjectMocks
    private TimelineController timelineController;

    @BeforeEach
    void setUp() {
        this.timelineDTOList = new ArrayList<>();
        dto = new TimelineDTO().setTitle("dummyTitleDTO").setQuery("dummyQueryDTO");
        this.timelineDTOList.add(dto);

        mvc = MockMvcBuilders.standaloneSetup(timelineController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {

        given(timelineService.getObject()).willReturn(timelineDTOList);
        MockHttpServletResponse response = mvc.perform(get("/timeline")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].title")  ,"dummyTitleDTO");
    }

    @Test
    void getDownloadByIdTest() throws Exception {
        given(timelineService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/timeline/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void postObjectTest() throws Exception {
        given(timelineService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/timeline")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }
    @Test
    void putObjectTest() throws Exception {
        given(timelineService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/timeline")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(timelineService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/timeline?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }




}
