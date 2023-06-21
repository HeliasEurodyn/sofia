package com.crm.sofia.service.html_dashboard;

import com.crm.sofia.dto.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapper;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapperImpl;
import com.crm.sofia.model.html_dashboard.HtmlDashboard;
import com.crm.sofia.repository.html_dashboard.HtmlDashboardRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.html_dashboard.HtmlDashboardService;
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
public class HtmlDashboardServiceTest {


    @Spy
    private final HtmlDashboardMapper htmlDashboardMapper = new HtmlDashboardMapperImpl();

    @InjectMocks
    private HtmlDashboardService htmlDashboardService;

    @Mock
    private HtmlDashboardRepository htmlDashboardRepository;

    @Mock
    private JWTService jwtService;

    private HtmlDashboard htmlDashboard;


    private HtmlDashboardDTO htmlDashboardDTO;

    private List<HtmlDashboard> htmlDashboardList;

    private List<HtmlDashboardDTO> htmlDashboardDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        htmlDashboardList = new ArrayList<>();
        htmlDashboard = new HtmlDashboard().setName("dummyName");
        htmlDashboard.setId("1");
        htmlDashboardList.add(htmlDashboard);
        htmlDashboardDTO = new HtmlDashboardDTO().setName("dummyName");

    }


    @Test
    public void getObjectTest() {
        given(htmlDashboardRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(htmlDashboard));
        HtmlDashboardDTO dto = htmlDashboardService.getObject("id");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getName().equals("dummyName"));

    }
}
