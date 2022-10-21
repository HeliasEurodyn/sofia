package com.crm.sofia.controllers.sofia.timeline;

import com.crm.sofia.controllers.timeline.TimelineDesignerController;
import com.crm.sofia.dto.timeline.TimelineDTO;
import com.crm.sofia.services.timeline.TimelineDesignerService;
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
public class TimelineDesignerControllerTest {

    private MockMvc mvc;

    private TimelineDTO dto;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private TimelineDesignerService timelineDesignerService;

    private List<TimelineDTO> timelineDTOList;

    @InjectMocks
    private TimelineDesignerController timelineDesignerController;

    @BeforeEach
    void setUp() {
        this.timelineDTOList = new ArrayList<>();
        dto = new TimelineDTO().setTitle("dummyTitleDTO").setQuery("dummyQueryDTO");
        this.timelineDTOList.add(dto);

        mvc = MockMvcBuilders.standaloneSetup(timelineDesignerController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {

        given(timelineDesignerService.getObject()).willReturn(timelineDTOList);
        MockHttpServletResponse response = mvc.perform(get("/timeline-designer")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].title")  ,"dummyTitleDTO");
    }

    @Test
    void getByIdTest() throws Exception {
        given(timelineDesignerService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/timeline-designer/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void postObjectTest() throws Exception {
        given(timelineDesignerService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/timeline-designer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }
    @Test
    void putObjectTest() throws Exception {
        given(timelineDesignerService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/timeline-designer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(timelineDesignerService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/timeline-designer?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }




}
