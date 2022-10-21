package com.crm.sofia.services.custom_query;

import com.crm.sofia.dto.custom_query.CustomQueryDTO;
import com.crm.sofia.mapper.custom_query.CustomQueryMapper;
import com.crm.sofia.model.custom_query.CustomQuery;
import com.crm.sofia.repository.custom_query.CustomQueryRepository;
import com.crm.sofia.services.auth.JWTService;

import com.crm.sofia.services.custom_query.CustomQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CustomQueryServiceTest {
    @Mock
    private CustomQueryRepository customQueryRepository;
    @InjectMocks
    private CustomQueryService customQueryService;

    @Mock
    private JWTService jwtService;
    private List<CustomQuery> customQueryList;

    private CustomQuery customQuery;
    private CustomQueryDTO customQueryDto;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private CustomQueryMapper customQueryMapper ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customQueryList = new ArrayList<>();
        customQuery = new CustomQuery();
        customQueryDto = new CustomQueryDTO();
        customQuery.setQuery("Query");
        customQuery.setName("dummy");
        customQueryList.add(customQuery);
        customQueryDto.setName("dummy");
        customQueryDto.setQuery("LOTR");
    }


    @Test
    public void getObjectTest() {
        given(customQueryRepository.findAll()).willReturn(customQueryList);
        List<CustomQueryDTO> list = customQueryService.getObject();
        assertThat(list).isNotNull();
    }

    @Test
    public void getObjectByIdTest(){
        given(customQueryRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(customQuery));
        CustomQueryDTO dto = customQueryService.getObject("6L");
    }

    @Test
    public void getObjectByIdWhenEmptyTest(){
        given(customQueryRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            customQueryService.getObject("6L");
        });

        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void postObjectTest(){
        given(customQueryRepository.save(ArgumentMatchers.any(CustomQuery.class))).willReturn(customQuery);
        given(customQueryMapper.map(ArgumentMatchers.any(CustomQueryDTO.class))).willReturn(customQuery);
        customQueryService.postObject(customQueryDto);
    }

    @Test
    public void getDeleteByIdTest(){
        given(customQueryRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(customQuery));
        customQueryService.deleteObject("6L");

    }

    @Test
    public void getDeleteByIdWhenEmptyTest(){
        given(customQueryRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            customQueryService.deleteObject("6L");
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,expectedMessage);
    }




}
