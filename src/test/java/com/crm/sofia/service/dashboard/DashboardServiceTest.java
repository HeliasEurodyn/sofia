package com.crm.sofia.service.dashboard;

import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.mapper.dashboard.DashboardMapperImpl;
import com.crm.sofia.model.dashboard.Dashboard;
import com.crm.sofia.repository.dashboard.DashboardRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.dashboard.DashboardService;
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
public class DashboardServiceTest {

    @Spy
    private final DashboardMapper dashboardMapper = new DashboardMapperImpl();

    @InjectMocks
    private DashboardService dashboardService;

    @Mock
    private DashboardRepository dashboardRepository;

    @Mock
    private JWTService jwtService;

    private List<Dashboard> dashboardList;

    private Dashboard dashboard;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dashboardList = new ArrayList<>();
        dashboard = new Dashboard().setDescription("dummyDescription").setDashboardAreaList(new ArrayList<>());
        dashboard.setId("1");
        dashboardList.add(dashboard);


    }

    @Test
    public void getObjectTest() {
        given(dashboardRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(dashboard));
        DashboardDTO dto = dashboardService.getObject("id");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getDescription().equals("dummyDescription"));

    }
}
