package com.crm.sofia.controllers.cityscape.node_graph;

import com.crm.sofia.dto.cityscape.node_graph.NodeDTO;
import com.crm.sofia.services.cityscape.node_graph.NodeGraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/node-graph")
public class NodeGraphController {

    private final NodeGraphService nodeGraphService;

    public NodeGraphController(NodeGraphService nodeGraphService) {
        this.nodeGraphService = nodeGraphService;
    }

    @GetMapping(path = "/asset/{id}")
    Object getAsset(@PathVariable("id") Long id, @RequestParam("type") String type) {
        return this.nodeGraphService.getAsset(id, type);
    }

    @GetMapping(path = "/relationships")
    Object getRelationships() {
        return this.nodeGraphService.getRelationships();
    }

    @PutMapping(path = "/update-asset-relationship")
    void updateAssetRelationship(@RequestParam("atoa-id") Long atoaId, @RequestParam("rel-code") String relCode, @RequestParam("type") String type) {
        this.nodeGraphService.updateAssetRelationship(atoaId, relCode, type);
    }

    @PostMapping(path = "/insert-related-asset", produces = "text/plain")
    String insertRelatedAsset(@RequestParam("asset_id") Long assetId, @RequestParam("rel_asset_id") Long relAssetId, @RequestParam("type") String type) {
        return this.nodeGraphService.insertRelatedAsset(assetId, relAssetId, type);
    }

    @DeleteMapping(path = "/remove-related-asset")
    void removeRelatedAsset(@RequestParam("atoa-id") Long atoaId, @RequestParam("type") String type) {
        this.nodeGraphService.removeRelatedAsset(atoaId, type);
    }

    @PostMapping(path = "/save-asset-positions")
    void saveAssetPositions(@RequestBody NodeDTO dto, @RequestParam("type") String type) {
        this.nodeGraphService.saveAssetPositions(dto, type);
    }

}
