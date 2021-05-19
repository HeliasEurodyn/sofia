package com.crm.sofia.services.dashboard;

import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.dto.dashboard.DashboardItemDTO;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.mapper.info_card.InfoCardMapper;
import com.crm.sofia.model.dashboard.Dashboard;
import com.crm.sofia.repository.dashboard.DashboardRepository;
import com.crm.sofia.repository.info_card.InfoCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final DashboardMapper dashboardMapper;

    public DashboardService(DashboardRepository dashboardRepository,
                            DashboardMapper dashboardMapper) {
        this.dashboardRepository = dashboardRepository;
        this.dashboardMapper = dashboardMapper;
    }

    public DashboardDTO getObject(Long id) {
        Optional<Dashboard> optionalDashboard = this.dashboardRepository.findById(id);
        if (!optionalDashboard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dashboard does not exist");
        }
        DashboardDTO dashboardDTO = dashboardMapper.map(optionalDashboard.get());

        dashboardDTO.getDashboardItemList()
                .stream()
                .filter(x -> x.getShortOrder() == null)
                .forEach(x -> x.setShortOrder(0L));

        List<DashboardItemDTO> dashboardItemList = dashboardDTO.getDashboardItemList().stream()
                .sorted(Comparator.comparingLong(DashboardItemDTO::getShortOrder))
                .collect(Collectors.toList());

        dashboardDTO.setDashboardItemList(dashboardItemList);

        return dashboardDTO;
    }

}
