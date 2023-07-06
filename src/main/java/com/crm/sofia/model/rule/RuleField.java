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
@Entity(name = "RuleField")
@Table(name = "rule_field")
public class RuleField extends MainEntity {

    @Column
    private String code;

    @Column
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "rule_field_id")
    List<RuleCode> codeList;

    @Column(columnDefinition = "TEXT")
    private String description;

}
