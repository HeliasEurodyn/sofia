package com.crm.sofia.services.rule;

import com.crm.sofia.dto.rule.RuleDTO;
import com.crm.sofia.dto.rule.RuleExecutionParametersDTO;
import com.crm.sofia.dto.rule.RuleExpressionDTO;
import com.crm.sofia.dto.rule.RuleSettingsDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.mapper.rule.RuleMapper;
import com.crm.sofia.mapper.rule.RuleSettingsMapper;
import com.crm.sofia.model.rule.Rule;
import com.crm.sofia.model.rule.RuleSettings;
import com.crm.sofia.model.rule.RuleSettingsQuery;
import com.crm.sofia.repository.rule.RuleRepository;
import com.crm.sofia.repository.rule.RuleSettingsQueryRepository;
import com.crm.sofia.repository.rule.RuleSettingsRepository;
import com.crm.sofia.services.auth.JWTService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private RuleSettingsQueryRepository ruleSettingsQueryRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private EntityManager entityManager;

    public List<RuleDTO> getObject() {
        List<RuleDTO> list = this.ruleRepository.getObject();
        return list;
    }

    public List<RuleExpressionDTO> getRuleExpressions(String id) {
        RuleDTO ruleDTO = this.getObject(id);
        return ruleDTO.getRuleExpressionList();
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

    public Object executeQuery(List<RuleExecutionParametersDTO> ruleExecParameters, String queryId) {

        this.retrieveRulesByParameters(ruleExecParameters);

        RuleSettingsQuery ruleSettingsQuery = this.ruleSettingsQueryRepository.findById(queryId)
                .orElseThrow(() -> new DoesNotExistException("Query Does Not Exist"));

        AtomicBoolean isFirstIteration = new AtomicBoolean(true);
        StringBuilder ruleQueryBuilder = new StringBuilder();
        ruleExecParameters.forEach(ruleExecParameter -> {
            String subQueryStr = createQueryStr(ruleExecParameter.getRule().getRuleExpressionList());

            if(!isFirstIteration.get()){
                ruleQueryBuilder.append(" AND ");
            }
            ruleQueryBuilder.append(subQueryStr);
            isFirstIteration.set(false);
        });

        String ruleQueryStr = ruleQueryBuilder.toString();
        String queryStr = ruleSettingsQuery.getQuery().replace("#rules#", ruleQueryStr);

        Query query = entityManager.createNativeQuery(queryStr);
        if( queryStr.contains(":userid")){
            query.setParameter("userid",this.jwtService.getUserId());
        }
        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        return nativeQuery.getResultList();
    }

    public void retrieveRulesByParameters(List<RuleExecutionParametersDTO> ruleExecParameters) {
        ruleExecParameters.forEach(ruleExecutionParameter -> {
            RuleDTO ruleDTO = this.getObject(ruleExecutionParameter.getRuleId());
            ruleExecutionParameter.setRule(ruleDTO);
        });
    }

    public String createQueryStr(List<RuleExpressionDTO> ruleExpressionList) {
        StringBuilder queryBuilder = new StringBuilder();

        for (int index = 0; index < ruleExpressionList.size(); index++) {
            RuleExpressionDTO ruleExpression = ruleExpressionList.get(index);

            if (ruleExpression.getRuleExpressionList() != null && !ruleExpression.getRuleExpressionList().isEmpty()) {
                queryBuilder.append(" ( ");
            }

            queryBuilder.append(ruleExpression.getFieldCode())
                    .append(" ")
                    .append(ruleExpression.getOperatorCode())
                    .append(" ")
                    .append(ruleExpression.getCommand());

            if (ruleExpression.getRuleExpressionList() != null && !ruleExpression.getRuleExpressionList().isEmpty()) {
                String childrenJoinType = ruleExpression.getChildrenJoinType();
                queryBuilder.append(childrenJoinType)
                        .append(this.createQueryStr(ruleExpression.getRuleExpressionList()))
                        .append(" ) ");
            }

            if (index < ruleExpressionList.size() - 1) {
                queryBuilder.append(ruleExpression.getJoinType());
            }
        }

        return queryBuilder.toString();
    }

}
