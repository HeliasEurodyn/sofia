package com.crm.sofia.repository.rule;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.rule.RuleDTO;
import com.crm.sofia.model.rule.Rule;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends BaseRepository<Rule> {

    @Query("SELECT new com.crm.sofia.dto.rule.RuleDTO(r.id, r.name, r.modifiedOn) FROM Rule r ORDER BY r.modifiedOn DESC")
    List<RuleDTO> getObject();

}
