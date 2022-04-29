package com.crm.sofia.model.cityscape.cve_search.rita;

import com.crm.sofia.model.common.MainEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
//@Accessors(chain = true)
//@Entity(name = "AssetsEntity")
//@Table(name = "assets_entity")
public class AssetsEntity extends MainEntity {

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "asset_entity_id")
    private List<Asset> assets;

}
