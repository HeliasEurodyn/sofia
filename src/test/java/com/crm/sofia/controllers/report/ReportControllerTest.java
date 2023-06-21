package com.crm.sofia.controllers.report;

import com.crm.sofia.controllers.menu.MenuController;
import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.mapper.menu.MenuMapperImpl;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.report.Report;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.repository.report.ReportRepository;
import com.crm.sofia.services.menu.MenuService;
import com.crm.sofia.services.report.ReportService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {


    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    private ReportService reportService;

    private MockMvc mvc;

    private Report report;

    private String reportType;


    private List<Report> reportList;


    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.reportList = new ArrayList<>();
        report = new Report().setName("dummyName").setType("ok");
        report.setId("1");
        this.reportList.add(report);
        reportType = "ok";

        final Map<String, String> map = new HashMap<>();
        map.put("string1", "String2");
        Map.of("param1", "value1", "param2", "value2");
        HttpServletResponse response;


        mvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }


    @Test
    void getByIdTest() throws Exception {
        given(reportService.getReportType(anyString())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(get("/report/type?id=0")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "{\"type\":\"ok\"}");
    }


}
