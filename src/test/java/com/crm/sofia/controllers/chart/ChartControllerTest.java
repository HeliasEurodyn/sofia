package com.crm.sofia.controllers.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.chart.ChartFieldDTO;
import com.crm.sofia.services.chart.ChartService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class ChartControllerTest {

    private MockMvc mvc;

    private ChartDTO dto;

    private ChartFieldDTO chartFieldDTO;

    @Mock
    private ChartService chartService;

    private List<ChartDTO> chartDTOList;

    private List<ChartFieldDTO> chartFieldDTOList;

    @InjectMocks
    private ChartController ChartController;

    @BeforeEach
    void setup() {

        this.chartDTOList = new ArrayList<>();
        dto = new ChartDTO().setTitle("dummyTitleDTO").setQuery("dummyHtmlDTO");
        this.chartDTOList.add(dto);
        this.chartFieldDTOList = new ArrayList<>();
        chartFieldDTO = new ChartFieldDTO().setDescription("dummyDescriptionDTO");
        chartFieldDTO.setId("1");
        this.chartFieldDTOList.add(chartFieldDTO);


        mvc = MockMvcBuilders.standaloneSetup(ChartController).build();
    }

    @Test
    void getByIdTest() throws Exception {
        given(chartService.getObject(any(), anyMap())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/chart/by-id?id=0&param1=value1&param2=value2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title"), "dummyTitleDTO");
    }

    @Test
    void getByDataTest() throws Exception {
        given(chartService.getData(any(), anyMap())).willReturn(chartFieldDTOList);
        MockHttpServletResponse response = mvc.perform(get("/chart/data?id=1&param1=value1&param2=value2").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].description"), "dummyDescriptionDTO");
    }


}
