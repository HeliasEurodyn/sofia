package com.crm.sofia.model.rule;

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
@Entity(name = "RuleSettings")
@Table(name = "rule_settings")
public class RuleSettings extends MainEntity {

    @Column
    private String name;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String ruleSectionTitle;

    @Column
    private String ruleSectionDescription;

    @Column
    private String fieldCommand;

    @Column
    private String operatorCommand;
}
