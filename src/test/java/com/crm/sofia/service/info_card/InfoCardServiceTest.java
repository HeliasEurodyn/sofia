package com.crm.sofia.service.info_card;

import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.dto.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.mapper.info_card.InfoCardMapper;
import com.crm.sofia.mapper.info_card.InfoCardMapperImpl;
import com.crm.sofia.model.info_card.InfoCard;
import com.crm.sofia.native_repository.info_card.InfoCardNativeRepository;
import com.crm.sofia.repository.info_card.InfoCardRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.info_card.InfoCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class InfoCardServiceTest {

    @Spy
    private final InfoCardMapper infoCardMapper = new InfoCardMapperImpl();

    @InjectMocks
    private InfoCardService infoCardService;

    @Mock
    private InfoCardRepository infoCardRepository;

    @Mock
    private InfoCardNativeRepository infoCardNativeRepository;

    @Mock
    private JWTService jwtService;

    private InfoCard dto;


    private InfoCardDTO infoCardDTO;

    private InfoCardTextResponceDTO infoCardTextResponceDTO;

    private List<InfoCard> infoCardList;

    private List<InfoCardDTO> infoCardDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        infoCardList = new ArrayList<>();
        infoCardDTOList = new ArrayList<>();
        dto = new InfoCard().setTitle("dummyTitle");
        dto.setId("2");
        infoCardList.add(dto);
        infoCardDTO = new InfoCardDTO().setTitle("dummyTitle").setDescription("dummyDescription").setQuery("dummyQ");
        infoCardDTO.setId("1");
        infoCardDTOList.add(infoCardDTO);
        infoCardTextResponceDTO = new InfoCardTextResponceDTO();

    }

    @Test
    public void getObjectTest() {
        given(infoCardRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(dto));
        given(infoCardNativeRepository.getData(any(), anyMap())).willReturn(infoCardTextResponceDTO);
        InfoCardDTO dto = infoCardService.getObject("1", Map.of("param1", "value1", "param2", "value2"));
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("2"));
        assertThat(dto.getQuery().equals("dummyQ"));

    }
}
