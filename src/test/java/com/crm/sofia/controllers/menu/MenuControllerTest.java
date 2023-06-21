package com.crm.sofia.controllers.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.mapper.list.ListMapperImpl;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.mapper.menu.MenuMapperImpl;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.services.menu.MenuService;
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
public class MenuControllerTest {
    @Spy
    private final MenuMapper menuMapper = new MenuMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private MenuService menuService;

    private MockMvc mvc;

    private Menu menu;

    private MenuDTO dto;

    private List<MenuDTO> menuDTOList;


    @Mock
    private MenuRepository listRepository;

    @InjectMocks
    private MenuController menuController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.menuDTOList = new ArrayList<>();
        dto = new MenuDTO().setName("dummyName");
        dto.setId("1");
        this.menuDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    void getByIdTest() throws Exception {
        given(menuService.getObject(anyString(), anyString())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/menu/by-id?id=0&language-id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name"), "dummyName");
    }
}
