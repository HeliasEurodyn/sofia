package com.crm.sofia.services.cityscape.asset_type_group;

import com.crm.sofia.model.cityscape.threat.AssetTypeGroup;
import com.crm.sofia.repository.cityscape.asset_type_group.AssetTypeGroupRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssetTypeGroupService {

    private final AssetTypeGroupRepository assetTypeGroupRepository;
    private final JWTService jwtService;

    public AssetTypeGroupService(AssetTypeGroupRepository assetTypeGroupRepository, JWTService jwtService) {
        this.assetTypeGroupRepository = assetTypeGroupRepository;
        this.jwtService = jwtService;
    }

    public AssetTypeGroup getObject(Long id) {
        AssetTypeGroup entity = this.assetTypeGroupRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Entity does not exist")
        );
        return entity;
    }

    public List<AssetTypeGroup> getObject() {
        List<AssetTypeGroup> list = this.assetTypeGroupRepository.findAll();
        return list;
    }
}
