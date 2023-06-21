package com.crm.sofia.service.chart;

import com.crm.sofia.dto.chart.ChartDTO;
import com.crm.sofia.dto.chart.ChartFieldDTO;
import com.crm.sofia.mapper.chart.ChartMapper;
import com.crm.sofia.mapper.chart.ChartMapperImpl;
import com.crm.sofia.model.chart.Chart;
import com.crm.sofia.native_repository.chart.ChartNativeRepository;
import com.crm.sofia.repository.chart.ChartRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.chart.ChartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ChartServiceTest {
    @Spy
    private final ChartMapper chartMapper = new ChartMapperImpl();
    @Mock
    private ChartRepository chartRepository;
    @Mock
    private ChartNativeRepository chartNativeRepository;
    @InjectMocks
    private ChartService chartService;

    @Mock
    private JWTService jwtService;

    private Chart chart;
    private ChartDTO chartDTO;

    private ChartFieldDTO chartFieldDTO;

    private List<ChartDTO> chartDTOList;

    private List<ChartFieldDTO> chartFieldDTOList;

    private final Map<String, String> map = new HashMap<>();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        chartDTOList = new ArrayList<>();
        chartFieldDTOList = new ArrayList<>();
        chart = new Chart().setTitle("dummyTitle").setQuery("dummyQuery");
        chart.setId("1");
        chart.setFilterList(new ArrayList<>());
        chartDTOList.add(chartDTO);
        chartFieldDTO = new ChartFieldDTO().setDescription("dummyDescription");
        chartFieldDTO.setId("1");
        chartFieldDTOList.add(chartFieldDTO);
        map.put("value1", "value2");

    }

    @Test
    public void getObjectByIdTest() {
        given(chartRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(chart));
        given(chartNativeRepository.getData(any(), any(), any())).willReturn(chartFieldDTOList);

        ChartDTO dto = chartService.getObject("id", Map.of("param1", "value1", "param2", "value2"));

        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getTitle().equals("dummyTitle"));
        assertThat(dto.getQuery().equals("dummyQuery"));
    }

    @Test
    public void getDataByIdTest() {
        given(chartRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(chart));
        given(chartNativeRepository.getData(any(), anyString(), anyMap())).willReturn(chartFieldDTOList);
        List<ChartFieldDTO> dtoList = chartService.getData("id",
                Map.of("param1", "value1", "param2", "value2"));
        assertThat(dtoList).isNotNull();
        assertThat(dtoList.get(0).getId().equals("1"));

    }
}
