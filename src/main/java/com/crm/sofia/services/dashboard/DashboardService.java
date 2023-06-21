package com.crm.sofia.services.dashboard;

import com.crm.sofia.dto.dashboard.DashboardAreaDTO;
import com.crm.sofia.dto.dashboard.DashboardDTO;
import com.crm.sofia.dto.dashboard.DashboardItemDTO;
import com.crm.sofia.mapper.dashboard.DashboardMapper;
import com.crm.sofia.model.dashboard.Dashboard;
import com.crm.sofia.repository.dashboard.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private  DashboardRepository dashboardRepository;
    @Autowired
    private  DashboardMapper dashboardMapper;
    @Autowired
    public DashboardService(DashboardRepository dashboardRepository,
                            DashboardMapper dashboardMapper) {
        this.dashboardRepository = dashboardRepository;
        this.dashboardMapper = dashboardMapper;
    }

    public DashboardDTO getObject(String id) {
        Optional<Dashboard> optionalDashboard = this.dashboardRepository.findById(id);
        if (!optionalDashboard.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dashboard does not exist");
        }
        DashboardDTO dashboardDTO = dashboardMapper.map(optionalDashboard.get());

        List<String> ids = new ArrayList<>();
        List<DashboardAreaDTO> dashboardAreaList = new ArrayList<>();
        for (DashboardAreaDTO area : dashboardDTO.getDashboardAreaList()) {
            if (!ids.contains(area.getId())) {
                ids.add(area.getId());
                dashboardAreaList.add(area);
            }
        }
        dashboardDTO.setDashboardAreaList(dashboardAreaList);

        dashboardDTO.getDashboardAreaList()
                .stream()
                .filter(x -> x.getShortOrder() == null)
                .forEach(x -> x.setShortOrder(0L));

        dashboardAreaList =
                dashboardDTO.getDashboardAreaList()
                        .stream()
                        .sorted(Comparator.comparingLong(DashboardAreaDTO::getShortOrder))
                        .collect(Collectors.toList());

        dashboardDTO.setDashboardAreaList(dashboardAreaList);

        dashboardDTO.getDashboardAreaList()
                .forEach(area -> {
                    area.getDashboardItemList().stream()
                            .filter(x -> x.getShortOrder() == null)
                            .forEach(x -> x.setShortOrder(0L));

                    List<DashboardItemDTO> dashboardItemList =
                            area.getDashboardItemList()
                                    .stream()
                                    .sorted(Comparator.comparingLong(DashboardItemDTO::getShortOrder))
                                    .collect(Collectors.toList());

                    area.setDashboardItemList(dashboardItemList);
                });

        return dashboardDTO;
    }

}
