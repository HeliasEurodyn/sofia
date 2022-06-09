package com.crm.sofia.services.cityscape.threat;

import com.crm.sofia.model.cityscape.threat.Threat;
import com.crm.sofia.repository.cityscape.threat.ThreatRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class ThreatService {

    private final ThreatRepository threatRepository;
    private final JWTService jwtService;

    public ThreatService(ThreatRepository threatRepository, JWTService jwtService) {
        this.threatRepository = threatRepository;
        this.jwtService = jwtService;
    }

    public Threat getObject(Long id) {
        String userId = jwtService.getUserId();
        Threat threat = this.threatRepository.findByCreatedByAndId(userId, id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Threat does not exist")
        );
        return threat;
    }

    public List<Threat> getObject() {
        String userId = jwtService.getUserId();
        List<Threat> threats = this.threatRepository.findByCreatedBy(userId);
        return threats;
    }

    @Transactional
    public Threat postObject(Threat threat) {
        String userId = jwtService.getUserId();

        if (threat.getId() != null) {
            this.getObject(threat.getId());
        }

        if (threat.getId() == null) {
            threat.setCreatedBy(userId);
            threat.setCreatedOn(Instant.now());
        }

        threat.setModifiedBy(userId);
        threat.setModifiedOn(Instant.now());

        return this.threatRepository.save(threat);
    }

    public Boolean delete(Long id) {
        this.threatRepository.deleteById(id);
        return true;
    }

}
