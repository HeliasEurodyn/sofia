package com.crm.sofia.controllers.timeline;

import com.crm.sofia.controllers.dashboard.DashboardController;
import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.dto.timeline.TimelineDTO;
import com.crm.sofia.dto.timeline.TimelineResponseDTO;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.mapper.dashboard.DashboardMapperImpl;
import com.crm.sofia.mapper.timeline.TimelineMapper;
import com.crm.sofia.mapper.timeline.TimelineMapperImpl;
import com.crm.sofia.repository.dashboard.DashboardRepository;
import com.crm.sofia.repository.timeline.TimelineRepository;
import com.crm.sofia.services.dashboard.DashboardService;
import com.crm.sofia.services.timeline.TimelineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class TimelineControllerTest {
    @Spy
    private final TimelineMapper timelineMapper = new TimelineMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private TimelineService timelineService;

    private MockMvc mvc;

    private TimelineDTO dto;


    private List<TimelineDTO> timelineDTOList;

    private TimelineResponseDTO timelineResponseDTO;


    @Mock
    private TimelineRepository timelineRepository;

    @InjectMocks
    private TimelineController timelineController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.timelineDTOList = new ArrayList<>();
        dto = new TimelineDTO().setDescription("dummyDescription");
        dto.setId("1");
        this.timelineDTOList.add(dto);
        timelineResponseDTO = new TimelineResponseDTO().setIsTheLastPage(Boolean.valueOf("1")).setResultList(List.of(Map.of("param1",dto)));


        mvc = MockMvcBuilders.standaloneSetup(timelineController).build();
    }

    @Test
    void getDataTest() throws Exception {
        given(timelineService.getData(anyString(),anyMap(),anyInt())).willReturn(timelineResponseDTO);
        MockHttpServletResponse response = mvc.perform(get("/timeline/data?id=1&param1=value1&param2=value2&currentPage=1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.isTheLastPage"), Boolean.valueOf("1"));
    }

    @Test
    void postDataTest() throws Exception {
        given(timelineService.postData(anyString(),anyMap(),anyInt())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(post("/timeline/data?id=1&param1=value1&param2=value2&currentPage=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "{\"response\": \"ok\"}");
    }

    @Test
    void getObjectTest() throws Exception {
        given(timelineService.getObjectIgnoringQuery(anyString())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/timeline/by-id?id=1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }

}
