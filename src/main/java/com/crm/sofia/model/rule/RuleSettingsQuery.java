package com.crm.sofia.model.rule;

import com.crm.sofia.model.common.BaseEntity;
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
@Entity(name = "RuleSettingsQuery")
@Table(name = "rule_settings_query")
public class RuleSettingsQuery extends BaseEntity {

    @Column
    String title;

    @Column(length = 2000)
    String query;

}
