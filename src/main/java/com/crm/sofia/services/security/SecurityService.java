package com.crm.sofia.services.security;

import com.crm.sofia.dto.sofia.security.SecurityDTO;
import com.crm.sofia.mapper.security.AccessControlMapper;
import com.crm.sofia.model.sofia.security.Security;
import com.crm.sofia.repository.security.SecurityRepository;
import com.crm.sofia.services.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SecurityService {

    @Autowired
    private AccessControlMapper accessControlMapper;
    @Autowired
    private SecurityRepository securityRepository;
    @Autowired
    private JWTService jwtService;

    public List<SecurityDTO> getObject() {
        List<Security> entities = securityRepository.findAll();
        return accessControlMapper.map(entities);
    }
    public SecurityDTO getObject(String id) {
        Optional<Security> optionalEntity = securityRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Security entity = optionalEntity.get();
        SecurityDTO dto = accessControlMapper.map(entity);
        return dto;
    }

    public SecurityDTO postObject(SecurityDTO securityDto) {

        Security security = accessControlMapper.map(securityDto);
        if (securityDto.getId() == null) {
            security.setCreatedOn(Instant.now());
            security.setCreatedBy(jwtService.getUserId());
        }
        security.setModifiedOn(Instant.now());
        security.setModifiedBy(jwtService.getUserId());
        Security savedData = securityRepository.save(security);

        return accessControlMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<Security> optionalEntity = securityRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        securityRepository.deleteById(optionalEntity.get().getId());
    }
}
