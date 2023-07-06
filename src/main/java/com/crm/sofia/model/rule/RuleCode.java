package com.crm.sofia.model.rule;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "RuleCode")
@Table(name = "rule_code")
public class RuleCode extends BaseEntity {

    @Column
    private String name;

    @Column
    private String value;

}
