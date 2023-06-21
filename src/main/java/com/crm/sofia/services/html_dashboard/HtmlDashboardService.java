package com.crm.sofia.services.html_dashboard;


import com.crm.sofia.dto.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.mapper.html_dashboard.HtmlDashboardMapper;
import com.crm.sofia.model.html_dashboard.HtmlDashboard;
import com.crm.sofia.repository.html_dashboard.HtmlDashboardRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class HtmlDashboardService {
    @Autowired
    private  HtmlDashboardMapper htmlDashboardMapper;
    @Autowired
    private  HtmlDashboardRepository htmlDashboardRepository;
    @Autowired
    private  JWTService jwtService;
    @Autowired
    public HtmlDashboardService(HtmlDashboardMapper htmlDashboardMapper,
                                HtmlDashboardRepository htmlDashboardRepository,
                                JWTService jwtService) {
        this.htmlDashboardMapper = htmlDashboardMapper;
        this.htmlDashboardRepository = htmlDashboardRepository;
        this.jwtService = jwtService;
    }

    public HtmlDashboardDTO getObject(String id) {
        Optional<HtmlDashboard> optionalEntity = htmlDashboardRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        HtmlDashboard entity = optionalEntity.get();
        HtmlDashboardDTO dto = htmlDashboardMapper.map(entity);
        return dto;
    }

}
