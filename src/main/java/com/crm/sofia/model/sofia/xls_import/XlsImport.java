package com.crm.sofia.model.sofia.xls_import;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.sofia.access_control.AccessControl;
import com.crm.sofia.model.sofia.component.Component;
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
@Entity(name = "XlsImport")
@Table(name = "xls_import")
public class XlsImport extends BaseEntity {

    @Column
    private String code;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String icon;

    @Column
    private Long firstLine;

    @Column
    private String xlsIterationColumn;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "xls_import_id")
    private List<XlsImportLine> xlsImportLineList;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.crm.sofia.model.sofia.component.Component.class)
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private Component component;

    @Column
    private Boolean accessControlEnabled;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "xls_import_id")
    private List<AccessControl> accessControls;
}
