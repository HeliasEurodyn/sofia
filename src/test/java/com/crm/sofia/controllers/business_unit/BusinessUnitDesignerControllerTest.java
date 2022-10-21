package com.crm.sofia.controllers.business_unit;

import com.crm.sofia.controllers.business_unit.BusinessUnitDesignerController;
import com.crm.sofia.dto.business_unit.BusinessUnitDTO;
import com.crm.sofia.services.business_unit.BusinessUnitDesignerService;
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
public class BusinessUnitDesignerControllerTest {

    private MockMvc mvc;

    private BusinessUnitDTO businessUnitDTO;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private BusinessUnitDesignerService businessUnitDesignerService;

    private List<BusinessUnitDTO> businessUnitDTOList;

    @InjectMocks
    private BusinessUnitDesignerController businessUnitDesignerController;

    @BeforeEach
    void setUp() {
        this.businessUnitDTOList = new ArrayList<>();
        businessUnitDTO = new BusinessUnitDTO().setTitle("dummyTitleDTO").setDescription("dummyDescriptionDTO");
        this.businessUnitDTOList.add(businessUnitDTO);

        mvc = MockMvcBuilders.standaloneSetup(businessUnitDesignerController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {

        given(businessUnitDesignerService.getObject()).willReturn(businessUnitDTOList);
        MockHttpServletResponse response = mvc.perform(get("/business-unit-designer")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].title")  ,"dummyTitleDTO");
    }

    @Test
    void getByIdTest() throws Exception {
        given(businessUnitDesignerService.getObject(any())).willReturn(businessUnitDTO);
        MockHttpServletResponse response = mvc.perform(get("/business-unit-designer/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void postObjectTest() throws Exception {
        given(businessUnitDesignerService.postObject(any())).willReturn(businessUnitDTO);
        MockHttpServletResponse response = mvc.perform(post("/business-unit-designer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(businessUnitDTO))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }
    @Test
    void putObjectTest() throws Exception {
        given(businessUnitDesignerService.postObject(any())).willReturn(businessUnitDTO);
        MockHttpServletResponse response = mvc.perform(put("/business-unit-designer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(businessUnitDTO))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title")  ,"dummyTitleDTO");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(businessUnitDesignerService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/business-unit-designer?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(businessUnitDTO))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }


}
