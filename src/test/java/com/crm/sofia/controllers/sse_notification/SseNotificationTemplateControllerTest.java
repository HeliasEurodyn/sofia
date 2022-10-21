package com.crm.sofia.controllers.sse_notification;

import com.crm.sofia.controllers.sse_notification.SseNotificationTemplateController;
import com.crm.sofia.dto.sse_notification.SseNotificationDTO;
import com.crm.sofia.services.sse_notification.SseNotificationTemplateService;
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
public class SseNotificationTemplateControllerTest {

    private MockMvc mvc;

    private SseNotificationDTO dto;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private SseNotificationTemplateService sseNotificationTemplateService;

    private List<SseNotificationDTO> sseNotificationDTOList;

    @InjectMocks
    private SseNotificationTemplateController sseNotificationTemplateController;

    @BeforeEach
    void setUp() {
        this.sseNotificationDTOList = new ArrayList<>();
        dto = new SseNotificationDTO().setTitle("dummyTitleDTO").setQuery("dummyQueryDTO");
        this.sseNotificationDTOList.add(dto);

        mvc = MockMvcBuilders.standaloneSetup(sseNotificationTemplateController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {

        given(sseNotificationTemplateService.getObject()).willReturn(sseNotificationDTOList);
        MockHttpServletResponse response = mvc.perform(get("/sse-notification-template")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].title")  ,"dummyTitleDTO");
    }

    @Test
    void getByIdTest() throws Exception {
        given(sseNotificationTemplateService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/sse-notification-template/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void postObjectTest() throws Exception {
        given(sseNotificationTemplateService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/sse-notification-template")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }
    @Test
    void putObjectTest() throws Exception {
        given(sseNotificationTemplateService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/sse-notification-template")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(sseNotificationTemplateService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/sse-notification-template?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

}
