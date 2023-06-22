package com.crm.sofia.services.rule;


import com.crm.sofia.dto.rule.RuleFieldDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.rule.RuleFieldMapper;
import com.crm.sofia.model.rule.RuleField;
import com.crm.sofia.repository.rule.RuleFieldRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class RuleFieldService {

    @Autowired
    private RuleFieldRepository RuleFieldRepository;

    @Autowired
    private RuleFieldMapper ruleFieldMapper;

    @Autowired
    private JWTService jwtService;


    public List<RuleFieldDTO> getObject() {
        List<RuleFieldDTO> list = this.RuleFieldRepository.getObject();
        return list;
    }

    public RuleFieldDTO getObject(String id) {

        RuleField entity = this.RuleFieldRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        return this.ruleFieldMapper.map(entity);
    }

    public void deleteObject(String id) {
        RuleField optionalEntity = this.RuleFieldRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        this.RuleFieldRepository.deleteById(optionalEntity.getId());
    }

    @Transactional
    public RuleFieldDTO postObject(RuleFieldDTO RuleFieldDTO) {
        RuleField component = this.ruleFieldMapper.map(RuleFieldDTO);
        component.setCreatedOn(Instant.now());
        component.setModifiedOn(Instant.now());
        component.setCreatedBy(jwtService.getUserId());
        component.setModifiedBy(jwtService.getUserId());

        RuleField createdComponent = this.RuleFieldRepository.save(component);
        return this.ruleFieldMapper.map(createdComponent);
    }

    @Transactional
    @Modifying
    public RuleFieldDTO putObject(RuleFieldDTO RuleFieldDTO) {
        RuleField entity = ruleFieldMapper.map(RuleFieldDTO);

        entity.setModifiedOn(Instant.now());
        entity.setModifiedBy(jwtService.getUserId());

        RuleField createdEntity = this.RuleFieldRepository.save(entity);
        return this.ruleFieldMapper.map(createdEntity);
    }
}
