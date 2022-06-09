package com.crm.sofia.controllers.cityscape.asset_type_group;

import com.crm.sofia.model.cityscape.threat.AssetTypeGroup;
import com.crm.sofia.services.cityscape.asset_type_group.AssetTypeGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping( "/asset-type-group")
@Tag(name = "Asset Type Groups", description = "Endpoints collection for Asset Type Groups List.")
public class AssetTypeGroupController {

    private final AssetTypeGroupService assetTypeGroupService;

    public AssetTypeGroupController(AssetTypeGroupService assetTypeGroupService) {
        this.assetTypeGroupService = assetTypeGroupService;
    }

    @GetMapping(path = "")
    public List<AssetTypeGroup> getObjectById() {
        return this.assetTypeGroupService.getObject();
    }

    @GetMapping(value = "/{id}")
    public AssetTypeGroup getObjectById(@PathVariable("id") Long id) {
        return this.assetTypeGroupService.getObject(id);
    }

}
