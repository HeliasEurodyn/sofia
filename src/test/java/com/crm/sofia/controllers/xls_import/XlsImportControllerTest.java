package com.crm.sofia.controllers.xls_import;

import com.crm.sofia.controllers.timeline.TimelineController;
import com.crm.sofia.dto.timeline.TimelineDTO;
import com.crm.sofia.dto.timeline.TimelineResponseDTO;
import com.crm.sofia.dto.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.mapper.dashboard.DashboardMapperImpl;
import com.crm.sofia.mapper.xls_import.XlsImportMapper;
import com.crm.sofia.mapper.xls_import.XlsImportMapperImpl;
import com.crm.sofia.repository.timeline.TimelineRepository;
import com.crm.sofia.repository.xls_import.XlsImportRepository;
import com.crm.sofia.services.timeline.TimelineService;
import com.crm.sofia.services.xls_import.XlsImportService;
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

@ExtendWith(MockitoExtension.class)
public class XlsImportControllerTest {

    @Spy
    private final XlsImportMapper xlsImportMapper = new XlsImportMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private XlsImportService xlsImportService;

    private MockMvc mvc;

    private XlsImportDTO dto;


    private List<XlsImportDTO> xlsImportDTOList;



    @Mock
    private XlsImportRepository xlsImportRepository;

    @InjectMocks
    private XlsImportController xlsImportController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.xlsImportDTOList = new ArrayList<>();
        dto = new XlsImportDTO().setDescription("dummyDescription");
        dto.setId("1");
        this.xlsImportDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(xlsImportController).build();
    }

    @Test
    void getDataTest() throws Exception {
        given(xlsImportService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/xls-import/by-id?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"),"dummyDescription");
    }
}
