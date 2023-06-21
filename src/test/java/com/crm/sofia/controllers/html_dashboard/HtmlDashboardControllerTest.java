package com.crm.sofia.controllers.html_dashboard;

import com.crm.sofia.dto.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapper;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapperImpl;
import com.crm.sofia.model.html_dashboard.HtmlDashboard;
import com.crm.sofia.repository.html_dashboard.HtmlDashboardRepository;
import com.crm.sofia.services.html_dashboard.HtmlDashboardService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class HtmlDashboardControllerTest {

    @Spy
    private final HtmlDashboardMapper htmlDashboardMapper = new HtmlDashboardMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private HtmlDashboardService htmlDashboardService;

    private MockMvc mvc;

    private HtmlDashboard htmlDashboard;

    private HtmlDashboardDTO dto;

    private List<HtmlDashboardDTO> htmlDashboardDTOList;


    @Mock
    private HtmlDashboardRepository htmlDashboardRepository;

    @InjectMocks
    private HtmlDashboardController htmlDashboardController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.htmlDashboardDTOList = new ArrayList<>();
        dto = new HtmlDashboardDTO().setName("dummyNameDTO");
        //dto.setId("1");
        this.htmlDashboardDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(htmlDashboardController).build();
    }

    @Test
    void getObjectByIdTest() throws Exception {
        given(htmlDashboardService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/html-dashboard/by-id?id=0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyNameDTO");
    }


}
