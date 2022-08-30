package com.crm.sofia.controllers.cityscape.asset_type;

import com.crm.sofia.model.cityscape.threat.AssetType;
import com.crm.sofia.services.cityscape.asset_type.AssetTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping( "/asset-type")
@Tag(name = "Asset Types", description = "Endpoints collection for Asset Types List.")
public class AssetTypeController {

    private final AssetTypeService assetTypeService;

    public AssetTypeController(AssetTypeService assetTypeService) {
        this.assetTypeService = assetTypeService;
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(path = "")
    public List<AssetType> getObjectById() {
        return this.assetTypeService.getObject();
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/{id}")
    public AssetType getObjectById(@PathVariable("id") Long id) {
        return this.assetTypeService.getObject(id);
    }

}
