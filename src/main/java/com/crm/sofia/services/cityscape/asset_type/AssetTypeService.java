package com.crm.sofia.services.cityscape.asset_type;

import com.crm.sofia.model.cityscape.threat.AssetType;
import com.crm.sofia.repository.cityscape.asset_type.AssetTypeRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssetTypeService {

    private final AssetTypeRepository assetTypeRepository;
    private final JWTService jwtService;

    public AssetTypeService(AssetTypeRepository assetTypeRepository, JWTService jwtService) {
        this.assetTypeRepository = assetTypeRepository;
        this.jwtService = jwtService;
    }

    public AssetType getObject(Long id) {
        AssetType entity = this.assetTypeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Entity does not exist")
        );
        return entity;
    }

    public List<AssetType> getObject() {
        List<AssetType> list = this.assetTypeRepository.findAll();
        return list;
    }
}
