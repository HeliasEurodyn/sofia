package com.crm.sofia.model.sofia.xls_import;

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
@Entity(name = "XlsImportLine")
@Table(name = "xls_import_line")
public class XlsImportLine extends BaseEntity {

    @Column
    private String code;

    @Column
    private String value;

    @Column
    private Long level;

}
