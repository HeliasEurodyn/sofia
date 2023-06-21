package com.crm.sofia.service.list;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapper;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapperImpl;
import com.crm.sofia.mapper.list.ListMapper;
import com.crm.sofia.mapper.list.ListMapperImpl;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.component.ComponentPersistEntity;
import com.crm.sofia.model.html_dashboard.HtmlDashboard;
import com.crm.sofia.model.list.ListEntity;
import com.crm.sofia.repository.html_dashboard.HtmlDashboardRepository;
import com.crm.sofia.repository.list.ListRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.html_dashboard.HtmlDashboardService;
import com.crm.sofia.services.list.ListService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ListServiceTest {

    @Spy
    private final ListMapper listMapper = new ListMapperImpl();

    @InjectMocks
    private ListService listService;

    @Mock
    private ListRepository listRepository;

    @Mock
    private JWTService jwtService;


    private ListEntity listEntity;

    private ComponentDTO component;

    //private Component component;


    private ListDTO listDTO;

    private ListDTO dto;

    private ComponentPersistEntityDTO componentPersistEntityDTO;

    private List<ComponentPersistEntityDTO> componentPersistEntityDTOList;



    private List<ListEntity> listEntityList;

    private List<List> listList;

    private List<ListDTO> listDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        listDTOList = new ArrayList<>();
        listEntityList = new ArrayList<>();
        componentPersistEntityDTOList = new ArrayList<>();
        componentPersistEntityDTO = new ComponentPersistEntityDTO().setCode("1");
        listEntity = new ListEntity().setName("dummyName").setComponent(new Component().setComponentPersistEntityList(new ArrayList<>())).setListActionButtons(new ArrayList<>()).setListComponentColumnFieldList(new ArrayList<>())
                .setListComponentTopGroupFieldList(new ArrayList<>()).setListComponentLeftGroupFieldList(new ArrayList<>())
                .setListComponentFilterFieldList(new ArrayList<>()).setListComponentActionFieldList(new ArrayList<>()).setListComponentOrderByFieldList(new ArrayList<>());
        listEntity.setId("1");
        listEntityList.add(listEntity);

        listDTOList.add(listDTO);

    }

    @Test
    public void getObjectTest() {
        given(listRepository.findById(any())).willReturn(Optional.of(listEntity));
        ListDTO dto = listService.getObject("id","languageId");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getName().equals("dummyName"));

    }
}
