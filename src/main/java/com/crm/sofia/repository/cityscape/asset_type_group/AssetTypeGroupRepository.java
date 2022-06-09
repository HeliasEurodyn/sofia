package com.crm.sofia.repository.cityscape.asset_type_group;

import com.crm.sofia.model.cityscape.threat.AssetTypeGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetTypeGroupRepository extends PagingAndSortingRepository<AssetTypeGroup, Long> {
    List<AssetTypeGroup> findAll();
}
