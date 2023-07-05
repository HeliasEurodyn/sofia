package com.crm.sofia.services.rule;

import com.crm.sofia.dto.rule.*;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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

        Rule entity = this.ruleRepository.findById(id).orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

        return this.ruleMapper.map(entity);
    }

    public void deleteObject(String id) {
        Rule optionalEntity = this.ruleRepository.findById(id).orElseThrow(() -> new DoesNotExistException("Rule Does Not Exist"));

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
        RuleSettings entity = this.ruleSettingsRepository.findById(id).orElseThrow(() -> new DoesNotExistException("Rule Setting Does Not Exist"));

        return this.ruleSettingsMapper.map(entity);

    }

    public Map <String, Object> getResults(QueryParametersDTO queryParameters, String queryId) {
        Query query = this.buildSqlQuery(queryParameters, queryId);

        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;
        nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        Map <String, Object> results = new HashMap<>();
        results.put("datalines", nativeQuery.getResultList() );
        results.put("fields", queryParameters.getUniqueRuleFields());

        return results;
    }

    public void execute(QueryParametersDTO queryParameters, String queryId) {
        Query query = this.buildSqlQuery(queryParameters, queryId);
        query.executeUpdate();
    }

    public Query buildSqlQuery(QueryParametersDTO queryParameters, String queryId) {

        this.retrieveRulesByParameters(queryParameters.getRuleExecParameters());

        RuleSettingsQuery ruleSettingsQuery = this.ruleSettingsQueryRepository.findById(queryId).orElseThrow(() -> new DoesNotExistException("Query Does Not Exist"));

        // 1. Iterate rules & build Expressions
        AtomicBoolean isFirstIteration = new AtomicBoolean(true);
        StringBuilder ruleQueryBuilder = new StringBuilder();
        queryParameters.getRuleExecParameters().forEach(ruleExecParameter -> {
            String subQueryStr = createRuleQueryStr(ruleExecParameter.getRule().getRuleExpressionList());

            if (!isFirstIteration.get()) {
                ruleQueryBuilder.append(" AND ");
            }
            ruleQueryBuilder.append(subQueryStr);
            isFirstIteration.set(false);
        });

        String ruleQueryStr = ruleQueryBuilder.toString();
        String queryStr = ruleSettingsQuery.getQuery().replace("#rules#", ruleQueryStr);

        // 2. Iterate QueryFields and replace in Query String
        for (QueryFieldDTO getQueryField : queryParameters.getQueryFields()) {
            if (queryStr.contains(":" + getQueryField.getCode())) {
                queryStr = queryStr.replace(":" + getQueryField.getCode(), getQueryField.getValue());
            }
        }

        // 3. Iterate QueryFields and replace in Query String
        if(queryStr.contains("#rule-fields#")) {
            String ruleFieldQueryStr = createRuleFieldQueryStr(queryParameters.getUniqueRuleFields());
            queryStr = queryStr.replace("#rule-fields#", ruleFieldQueryStr);
        }

        Query query = entityManager.createNativeQuery(queryStr);
        if (queryStr.contains(":userid")) {
            query.setParameter("userid", this.jwtService.getUserId());
        }

        return query;
    }

    public void retrieveRulesByParameters(List<RuleExecutionParametersDTO> ruleExecParameters) {
        ruleExecParameters.forEach(ruleExecutionParameter -> {
            RuleDTO ruleDTO = this.getObject(ruleExecutionParameter.getRuleId());
            ruleExecutionParameter.setRule(ruleDTO);
        });
    }

    public String createRuleQueryStr(List<RuleExpressionDTO> ruleExpressionList) {
        StringBuilder queryBuilder = new StringBuilder();

        for (int index = 0; index < ruleExpressionList.size(); index++) {
            RuleExpressionDTO ruleExpression = ruleExpressionList.get(index);

            if (ruleExpression.getRuleExpressionList() != null && !ruleExpression.getRuleExpressionList().isEmpty()) {
                queryBuilder.append(" ( ");
            }

            queryBuilder.append(ruleExpression.getRuleField().getCode()).append(" ").append(ruleExpression.getRuleOperator().getCode()).append(" '").append(ruleExpression.getCommand()).append("'");

            if (ruleExpression.getRuleExpressionList() != null && !ruleExpression.getRuleExpressionList().isEmpty()) {
                String childrenJoinType = ruleExpression.getChildrenJoinType();
                queryBuilder.append(" ").append(childrenJoinType).append(" ( ").append(this.createRuleQueryStr(ruleExpression.getRuleExpressionList())).append(" ) ").append(" ) ");
            }

            if (index < ruleExpressionList.size() - 1) {
                queryBuilder.append(" ").append(ruleExpression.getJoinType()).append(" ");
            }
        }

        return queryBuilder.toString();
    }

    public String createRuleFieldQueryStr(List<RuleFieldDTO> ruleFields) {
        StringBuilder queryBuilder = new StringBuilder();

        ruleFields.forEach(ruleField -> {
            queryBuilder
                    .append(" ,")
                    .append(ruleField.getCode())
                    .append(" AS ")
                    .append("'")
                    .append(ruleField.getCode())
                    .append("'");
        });

        return queryBuilder.toString();
    }

    public List<RuleFieldDTO> findUniqueRuleFields(List<RuleExpressionDTO> ruleExpressionList, List<RuleFieldDTO> ruleFields) {
        Set<String> codes = ruleFields.stream().map(RuleFieldDTO::getCode).collect(Collectors.toSet());

        for (RuleExpressionDTO ruleExpression : ruleExpressionList) {
            if (!codes.contains(ruleExpression.getRuleField().getCode())) {
                ruleFields.add(ruleExpression.getRuleField());
                codes.add(ruleExpression.getRuleField().getCode());
            }

            List<RuleExpressionDTO> childExpressionList = ruleExpression.getRuleExpressionList();
            if (childExpressionList != null && !childExpressionList.isEmpty()) {
                findUniqueRuleFields(childExpressionList, ruleFields);
            }
        }

        return ruleFields;
    }

}
