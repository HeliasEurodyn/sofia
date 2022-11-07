package com.crm.sofia.model.xls_import;

import com.crm.sofia.model.access_control.AccessControl;
import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "XlsImport")
@Table(name = "xls_import")
public class XlsImport extends MainEntity {

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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Component.class)
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
