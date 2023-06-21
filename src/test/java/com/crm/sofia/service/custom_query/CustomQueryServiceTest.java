package com.crm.sofia.service.custom_query;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.custom_query.CustomQueryDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.mapper.component.ComponentMapperImpl;
import com.crm.sofia.mapper.custom_query.CustomQueryMapper;
import com.crm.sofia.mapper.custom_query.CustomQueryMapperImpl;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.custom_query.CustomQuery;
import com.crm.sofia.repository.component.ComponentRepository;
import com.crm.sofia.repository.custom_query.CustomQueryRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.custom_query.CustomQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CustomQueryServiceTest {

    @Spy
    private final CustomQueryMapper customQueryMapper = new CustomQueryMapperImpl();

    @InjectMocks
    private CustomQueryService customQueryService;

    @Mock
    private CustomQueryRepository customQueryRepository;

    @Mock
    private JWTService jwtService;

    private List<CustomQuery> customQueryList;

    private CustomQuery customQuery;

    private CustomQueryDTO customQueryDTO;

    private List<CustomQueryDTO> customQueryDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customQueryList = new ArrayList<>();
        customQuery = new CustomQuery().setName("dummyName").setQuery("dummyQuery");
        customQuery.setId("1");
        customQueryList.add(customQuery);
        customQueryDTO = new CustomQueryDTO().setName("dummyNameDTO");
        customQueryDTO.setId("1");

    }

    @Test
    public void getObjectTest() {
        given(customQueryRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(customQuery));
        CustomQueryDTO dto = customQueryService.getObject("id");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getName().equals("dummyName"));
        assertThat(dto.getQuery().equals("dummyDescription"));
    }

}
