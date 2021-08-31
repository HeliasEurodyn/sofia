package com.crm.sofia.controllers.sofia.download;

import com.crm.sofia.dto.sofia.download.DownloadDTO;
import com.crm.sofia.filters.JWTAuthFilter;
import com.crm.sofia.services.sofia.download.DownloadService;
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





@ExtendWith(MockitoExtension.class)
public class DownloadControllerTest {

    private MockMvc mvc;
    private DownloadDTO dto;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private DownloadService downloadService;

    private List<DownloadDTO> downloadDTOList;

    @Mock
    private JWTAuthFilter filter;

    @InjectMocks
    private DownloadController downloadController;

    @BeforeEach
    void setUp() {
        this.downloadDTOList = new ArrayList<>();
        dto = new DownloadDTO();
        dto.setCode("1234");
        dto.setName("dummy");
        this.downloadDTOList.add(dto);

        mvc = MockMvcBuilders.standaloneSetup(downloadController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {

        given(downloadService.getObject()).willReturn(downloadDTOList);
        MockHttpServletResponse response = mvc.perform(get("/download")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].name")  ,"dummy");
    }

    @Test
    void getDownloadByIdTest() throws Exception {
        given(downloadService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/download/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"dummy");
    }

    @Test
    void postObjectTest() throws Exception {
        given(downloadService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/download")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"dummy");
    }
    @Test
    void putObjectTest() throws Exception {
        given(downloadService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/download")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"dummy");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(downloadService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/download?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

}
