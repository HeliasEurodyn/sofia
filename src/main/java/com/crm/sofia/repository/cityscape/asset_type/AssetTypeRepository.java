package com.crm.sofia.repository.cityscape.asset_type;

import com.crm.sofia.model.cityscape.threat.AssetType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetTypeRepository extends PagingAndSortingRepository<AssetType, Long> {
    List<AssetType> findAll();
}
