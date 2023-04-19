package com.crm.sofia.model.rule;

import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "Rule")
@Table(name = "rule")
public class Rule extends MainEntity {

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    String childrenColor;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "rule_id")
    private List<RuleExpression> ruleExpressionList;

}
