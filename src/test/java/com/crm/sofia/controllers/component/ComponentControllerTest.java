package com.crm.sofia.controllers.component;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.chart.ChartMapper;
import com.crm.sofia.mapper.chart.ChartMapperImpl;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.mapper.component.ComponentMapperImpl;
import com.crm.sofia.model.chart.Chart;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.component.ComponentPersistEntityField;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.component.crud.ComponentDeleterService;
import com.crm.sofia.services.component.crud.ComponentPersistEntityRetrieverService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.component.crud.ComponentSaverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class ComponentControllerTest {

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Spy
    private final ComponentMapper componentMapper = new ComponentMapperImpl();

    private MockMvc mvc;

    private ComponentDTO dto;

    private ComponentPersistEntityDTO componentPersistEntityDTO;

    private ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO;

    private ComponentPersistEntity componentPersistEntity;

    @Mock
    private ComponentService componentService;

    @Mock
    private ComponentPersistEntityRetrieverService componentPersistEntityRetrieverService;

    @Mock
    private ComponentRetrieverService componentRetrieverService;

    @Mock
    private ComponentDeleterService componentDeleterService;

    @Mock
    private ComponentSaverService componentSaverService;

    private List<ComponentDTO> componentDTOList;

    private List<ComponentPersistEntityField> componentPersistEntityFieldList;

    private List<ComponentPersistEntityDTO> componentPersistEntityDTOList;

    private List<ComponentPersistEntityFieldDTO> componentPersistEntityFieldDTOList;


    @InjectMocks
    private ComponentController ComponentController;

    @BeforeEach
    void setup() {

        this.componentDTOList = new ArrayList<>();
        dto = new ComponentDTO().setName("dummyNameDTO").setDescription("dummyDescriptionDTO");
        dto.setId("1");
        this.componentDTOList.add(dto);
        this.componentPersistEntityFieldDTOList = new ArrayList<>();
        componentPersistEntityFieldDTO = new ComponentPersistEntityFieldDTO().setCode("2");
        componentPersistEntityFieldDTO.setId("1");
        this.componentPersistEntityFieldDTOList.add(componentPersistEntityFieldDTO);
        this.componentPersistEntityDTOList = new ArrayList<>();
        componentPersistEntityDTO = new ComponentPersistEntityDTO().setCode("3");
        componentPersistEntityDTO.setId("1");
        this.componentPersistEntityDTOList.add(componentPersistEntityDTO);


        mvc = MockMvcBuilders.standaloneSetup(ComponentController).build();
    }

    @Test
    void getComponentPersistEntityByIdTest() throws Exception {
        given(componentPersistEntityRetrieverService.getComponentPersistEntityDataById(anyString(),anyString())).willReturn(componentPersistEntityDTO);
        MockHttpServletResponse response = mvc.perform(get("/component/component-persist-entity/by-id?component-persist-entity-id=1&selection-id=0")
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.code"), "3");
    }

    @Test
    void getComponentByIdTest() throws Exception {
        given(componentRetrieverService.retrieveComponentWithData("1","0")).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/component/by-id?id=1&selection-id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyNameDTO");
   }

    @Test
    void postObjectTest() throws Exception {
        given(componentSaverService.save(anyString(),any())).willReturn("ok");
        MockHttpServletResponse response = mvc.perform(post("/component?id=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(Map.of("value1",Map.of("param1","value1","param2","value2"))))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "ok");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(componentDeleterService).retrieveComponentAndDelete(ArgumentMatchers.anyString(),ArgumentMatchers.anyString());
        MockHttpServletResponse response = mvc.perform(delete("/component?id=1&selection-id=1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());

    }



}
