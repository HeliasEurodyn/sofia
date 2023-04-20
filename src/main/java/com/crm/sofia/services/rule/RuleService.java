package com.crm.sofia.services.rule;

import com.crm.sofia.dto.rule.RuleDTO;
import com.crm.sofia.dto.rule.RuleSettingsDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.rule.RuleMapper;
import com.crm.sofia.mapper.rule.RuleSettingsMapper;
import com.crm.sofia.model.rule.Rule;
import com.crm.sofia.model.rule.RuleSettings;
import com.crm.sofia.repository.rule.RuleRepository;
import com.crm.sofia.repository.rule.RuleSettingsRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.menu.MenuFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private RuleSettingsRepository ruleSettingsRepository;

    @Autowired
    private RuleSettingsMapper ruleSettingsMapper;


    @Autowired
    private JWTService jwtService;


    public List<RuleDTO> getObject() {
        List<RuleDTO> list = this.ruleRepository.getObject();
        return list;
    }

    public RuleDTO getObject(String id) {

        Rule entity = this.ruleRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        return this.ruleMapper.map(entity);
    }

    public void deleteObject(String id) {
        Rule optionalEntity = this.ruleRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        this.ruleRepository.deleteById(optionalEntity.getId());
    }

    @Transactional
    public RuleDTO postObject(RuleDTO ruleDTO) {
        Rule component = this.ruleMapper.map(ruleDTO);
        component.setCreatedOn(Instant.now());
        component.setModifiedOn(Instant.now());
        component.setCreatedBy(jwtService.getUserId());
        component.setModifiedBy(jwtService.getUserId());

        Rule createdComponent = this.ruleRepository.save(component);
        return this.ruleMapper.map(createdComponent);
    }

    @Transactional
    public RuleDTO putObject(RuleDTO ruleDTO) {
        Rule entity = ruleMapper.map(ruleDTO);

        entity.setModifiedOn(Instant.now());
        entity.setModifiedBy(jwtService.getUserId());

        Rule createdEntity = this.ruleRepository.save(entity);
        return this.ruleMapper.map(createdEntity);
    }

    public RuleSettingsDTO getObjectSettings(String id) {
        RuleSettings entity = this.ruleSettingsRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistException("Rule Setting Does Not Exist"));

        return this.ruleSettingsMapper.map(entity);

    }
}
