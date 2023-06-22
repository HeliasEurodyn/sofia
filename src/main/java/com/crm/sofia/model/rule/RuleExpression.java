package com.crm.sofia.model.rule;

import com.crm.sofia.dto.rule.RuleExpressionDTO;
import com.crm.sofia.dto.rule.RuleFieldDTO;
import com.crm.sofia.dto.rule.RuleOperatorDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.Component;
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
@Entity(name = "RuleExpression")
@Table(name = "rule_xpression")
public class RuleExpression extends BaseEntity {

    @Column
    String fieldCode;

    @Column
    String fieldName;

    @Column
    String operatorCode;

    @Column
    String operatorName;

    @Column
    String command;

    @Column
    String color;

    @Column
    String childrenColor;

    @Column
    String joinType;

    @Column
    String childrenJoinType;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "rule_expression_id")
    private List<RuleExpression> ruleExpressionList;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RuleField.class)
    @JoinColumn(name = "rule_field_id", referencedColumnName = "id")
    RuleField ruleField;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RuleOperator.class)
    @JoinColumn(name = "rule_operator_id", referencedColumnName = "id")
    RuleOperator ruleOperator;
}
