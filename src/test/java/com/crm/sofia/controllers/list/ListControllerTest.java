package com.crm.sofia.controllers.list;

import com.crm.sofia.controllers.info_card.InfoCardController;
import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.dto.list.GroupEntryDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.dto.list.ListResultsDataDTO;
import com.crm.sofia.mapper.info_card.InfoCardMapper;
import com.crm.sofia.mapper.info_card.InfoCardMapperImpl;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.mapper.list.ListMapperImpl;
import com.crm.sofia.model.info_card.InfoCard;
import com.crm.sofia.repository.info_card.InfoCardRepository;
import com.crm.sofia.repository.list.ListRepository;
import com.crm.sofia.services.info_card.InfoCardService;
import com.crm.sofia.services.list.ListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lowagie.text.xml.simpleparser.EntitiesToUnicode.map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class ListControllerTest {

    @Spy
    private final ListMapper listMapper = new ListMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ListService listService;

    private MockMvc mvc;

    private List list;

    private ListDTO dto;

    private List<ListDTO> listDTOList;

    //private List<ListDTO> listDTOList;

    private ListResultsDataDTO listResultsDataDTO;

    private GroupEntryDTO groupEntryDTO;

    private List<GroupEntryDTO> groupEntryDTOList;


    @Mock
    private ListRepository listRepository;

    @InjectMocks
    private ListController listController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.listDTOList = new ArrayList<>();
        dto = new ListDTO().setDescription("dummyDescription");
        dto.setId("1");
        this.listDTOList.add(dto);
        listResultsDataDTO = new ListResultsDataDTO().setTotalPages(10L).setCurrentPage(20L);
        groupEntryDTO = new GroupEntryDTO().setId("1");
        groupEntryDTOList = new ArrayList<>();
        this.groupEntryDTOList.add(groupEntryDTO);
        final Map<String, String> map = new HashMap<>();
        map.put("string1", "String2");


        mvc = MockMvcBuilders.standaloneSetup(listController).build();

    }

    @Test
    void getByIdTest() throws Exception {
        given(listService.retrieveListAndCalcDefaultExpression(anyString(),anyString())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/list/ui?id=0&language-id=1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }

    @Test
    void getByResultTest() throws Exception {
        given(listService.getObjectDataByParameters(anyMap(),anyLong(), ArgumentMatchers.any())).willReturn(listResultsDataDTO);
        MockHttpServletResponse response = mvc.perform(get("/list/results?param1=value1&param2=value2&id=1&language-id=1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.totalPages").toString(),"10");
    }

    @Test
    void getPageObjectTest() throws Exception {
        given(listService.getObjectDataByParameters(anyMap(),anyLong(),ArgumentMatchers.any())).willReturn(listResultsDataDTO);
        MockHttpServletResponse response = mvc.perform(get("/list/results/page/1?param1=value1&param2=value2&id=1&language-id=1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.totalPages").toString(), "10");
    }

    @Test
    void getByRGroupTest() throws Exception {
        given(listService.getObjectLeftGroupingDataByParameters(anyMap(),any())).willReturn(groupEntryDTOList);
        MockHttpServletResponse response = mvc.perform(get("/list/left-grouping/results?param1=value1&param2=value2&id=1&language-id=0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.size()").toString(), "1");
    }
}
