package com.crm.sofia.controllers.dashboard;

import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.mapper.dashboard.DashboardMapperImpl;
import com.crm.sofia.repository.dashboard.DashboardRepository;
import com.crm.sofia.services.dashboard.DashboardService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {

    @Spy
    private final DashboardMapper dashboardMapper = new DashboardMapperImpl();
    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private DashboardService dashboardService;

    private MockMvc mvc;

    private DashboardDTO dto;

    private List<DashboardDTO> dashboardDTOList;


    @Mock
    private DashboardRepository dashboardRepository;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.dashboardDTOList = new ArrayList<>();
        dto = new DashboardDTO().setDescription("dummyDescription");
        dto.setId("1");
        this.dashboardDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(dashboardController).build();
    }


    @Test
    void getObjectByIdTest() throws Exception {
        given(dashboardService.getObject(anyString())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/dashboard/by-id?id=1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }


}






