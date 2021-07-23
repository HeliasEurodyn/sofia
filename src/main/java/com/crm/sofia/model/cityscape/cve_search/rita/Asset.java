package com.crm.sofia.model.cityscape.cve_search.rita;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
//@Accessors(chain = true)
//@Entity(name = "Asset")
//@Table(name = "asset")
public class Asset extends BaseEntity {

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "asset_id")
    private List<Interface> interfaces;

}
