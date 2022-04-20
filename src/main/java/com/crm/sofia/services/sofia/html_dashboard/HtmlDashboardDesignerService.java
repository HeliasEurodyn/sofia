package com.crm.sofia.services.sofia.html_dashboard;


import com.crm.sofia.dto.sofia.html_dashboard.HtmlDashboardDTO;
import com.crm.sofia.mapper.sofia.html_dashboard.HtmlDashboardMapper;
import com.crm.sofia.model.sofia.html_dashboard.HtmlDashboard;
import com.crm.sofia.repository.sofia.html_dashboard.HtmlDashboardRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class HtmlDashboardDesignerService {

    private final HtmlDashboardMapper htmlDashboardMapper;
    private final HtmlDashboardRepository htmlDashboardRepository;
    private final JWTService jwtService;

    public HtmlDashboardDesignerService(HtmlDashboardMapper htmlDashboardMapper,
                                        HtmlDashboardRepository htmlDashboardRepository,
                                        JWTService jwtService) {
        this.htmlDashboardMapper = htmlDashboardMapper;
        this.htmlDashboardRepository = htmlDashboardRepository;
        this.jwtService = jwtService;
    }

    public List<HtmlDashboardDTO> getObject() {
        List<HtmlDashboard> entites = htmlDashboardRepository.findAll();
        return htmlDashboardMapper.map(entites);
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

    @Transactional
    public HtmlDashboardDTO postObject(HtmlDashboardDTO htmlDashboardDto) {

        HtmlDashboard htmlDashboard = htmlDashboardMapper.map(htmlDashboardDto);
        if (htmlDashboardDto.getId() == null) {
            htmlDashboard.setCreatedOn(Instant.now());
            htmlDashboard.setCreatedBy(jwtService.getUserId());
        }
        htmlDashboard.setModifiedOn(Instant.now());
        htmlDashboard.setModifiedBy(jwtService.getUserId());
        HtmlDashboard savedData = htmlDashboardRepository.save(htmlDashboard);

        return htmlDashboardMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<HtmlDashboard> optionalEntity = htmlDashboardRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        htmlDashboardRepository.deleteById(optionalEntity.get().getId());
    }


}
