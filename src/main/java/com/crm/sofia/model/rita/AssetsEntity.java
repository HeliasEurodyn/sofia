package com.crm.sofia.model.rita;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity(name = "AssetsEntity")
@Table(name = "assets_entity")
public class AssetsEntity extends BaseEntity {

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL },
            orphanRemoval=true
    )
    @JoinColumn(name = "asset_entity_id")
    private List<Asset> assets;

}
