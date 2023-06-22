package com.crm.sofia.services.rule;

import com.crm.sofia.dto.rule.RuleOperatorDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.rule.RuleOperatorMapper;
import com.crm.sofia.model.rule.RuleOperator;
import com.crm.sofia.repository.rule.RuleOperatorRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class RuleOperatorService {

    @Autowired
    private RuleOperatorRepository RuleOperatorRepository;

    @Autowired
    private RuleOperatorMapper ruleOperatorMapper;

    @Autowired
    private JWTService jwtService;


    public List<RuleOperatorDTO> getObject() {
        List<RuleOperatorDTO> list = this.RuleOperatorRepository.getObject();
        return list;
    }

    public RuleOperatorDTO getObject(String id) {

        RuleOperator entity = this.RuleOperatorRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        return this.ruleOperatorMapper.map(entity);
    }

    public void deleteObject(String id) {
        RuleOperator optionalEntity = this.RuleOperatorRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        this.RuleOperatorRepository.deleteById(optionalEntity.getId());
    }

    @Transactional
    public RuleOperatorDTO postObject(RuleOperatorDTO RuleOperatorDTO) {
        RuleOperator component = this.ruleOperatorMapper.map(RuleOperatorDTO);
        component.setCreatedOn(Instant.now());
        component.setModifiedOn(Instant.now());
        component.setCreatedBy(jwtService.getUserId());
        component.setModifiedBy(jwtService.getUserId());

        RuleOperator createdComponent = this.RuleOperatorRepository.save(component);
        return this.ruleOperatorMapper.map(createdComponent);
    }

    @Transactional
    @Modifying
    public RuleOperatorDTO putObject(RuleOperatorDTO RuleOperatorDTO) {
        RuleOperator entity = ruleOperatorMapper.map(RuleOperatorDTO);

        entity.setModifiedOn(Instant.now());
        entity.setModifiedBy(jwtService.getUserId());

        RuleOperator createdEntity = this.RuleOperatorRepository.save(entity);
        return this.ruleOperatorMapper.map(createdEntity);
    }
}
