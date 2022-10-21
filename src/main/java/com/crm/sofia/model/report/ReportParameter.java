package com.crm.sofia.model.report;

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
@Entity(name = "ReportParameter")
@Table(name = "report_parameter")
public class ReportParameter extends BaseEntity {

    @Column
    private String code;

    @Column
    private String value;

}
