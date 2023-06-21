package com.crm.sofia.controllers.search;

import com.crm.sofia.controllers.menu.MenuController;
import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.mapper.menu.MenuMapperImpl;
import com.crm.sofia.mapper.search.SearchMapper;
import com.crm.sofia.mapper.search.SearchMapperImpl;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.search.Search;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.repository.search.SearchRepository;
import com.crm.sofia.services.menu.MenuService;
import com.crm.sofia.services.search.SearchService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {
    @Spy
    private final SearchMapper searchMapper = new SearchMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private SearchService searchService;

    private MockMvc mvc;

    private Search search;

    private String response;

    private SearchDTO dto;

    private List<SearchDTO> searchDTOList;


    @Mock
    private SearchRepository searchRepository;

    @InjectMocks
    private SearchController searchController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.searchDTOList = new ArrayList<>();
        dto = new SearchDTO().setName("dummyName");
        dto.setId("1");
        this.searchDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    void getByIdTest() throws Exception {
        given(searchService.getData(anyString(), anyString())).willReturn((dto));
        MockHttpServletResponse response = mvc.perform(get("/search/data?id=0&search='ok'").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("name"), "dummyName");
    }
}
