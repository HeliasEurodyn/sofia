package com.crm.sofia.repository.rule;

import com.crm.sofia.dto.rule.RuleOperatorDTO;
import com.crm.sofia.model.rule.RuleOperator;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleOperatorRepository extends BaseRepository<RuleOperator> {

    @Query("SELECT new com.crm.sofia.dto.rule.RuleOperatorDTO(r.id, r.name, r.modifiedOn) FROM RuleOperator r ORDER BY r.modifiedOn DESC")
    List<RuleOperatorDTO> getObject();



}
