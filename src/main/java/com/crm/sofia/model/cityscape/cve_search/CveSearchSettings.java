package com.crm.sofia.model.cityscape.cve_search;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity(name = "CveSearchSettings")
@Table(name = "cve_search_settings")
public class CveSearchSettings extends BaseEntity {

    @Column
    String serverUrl;

    @Column
    String vendorTableName;
}
