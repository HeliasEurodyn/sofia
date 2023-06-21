package com.crm.sofia.controllers.info_card;

import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.mapper.info_card.InfoCardMapper;
import com.crm.sofia.mapper.info_card.InfoCardMapperImpl;
import com.crm.sofia.model.info_card.InfoCard;
import com.crm.sofia.repository.info_card.InfoCardRepository;
import com.crm.sofia.services.info_card.InfoCardService;
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
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class InfoCardControllerTest {

    @Spy
    private final InfoCardMapper infoCardMapper = new InfoCardMapperImpl();

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private InfoCardService infoCardService;

    private MockMvc mvc;

    private InfoCard infoCard;

    private InfoCardDTO dto;

    private List<InfoCardDTO> infoCardDTOList;


    @Mock
    private InfoCardRepository infoCardRepository;

    @InjectMocks
    private InfoCardController infoCardController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.infoCardDTOList = new ArrayList<>();
        dto = new InfoCardDTO().setDescription("dummyDescription");
        dto.setId("1");
        this.infoCardDTOList.add(dto);


        mvc = MockMvcBuilders.standaloneSetup(infoCardController).build();
    }

    @Test
    void getByIdTest() throws Exception {
        given(infoCardService.getObject(anyString(), anyMap())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/info-card/by-id?id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.description"), "dummyDescription");
    }
}
