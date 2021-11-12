package com.crm.sofia.controllers.cityscape.node_graph;

import com.crm.sofia.dto.cityscape.node_graph.NodeDTO;
import com.crm.sofia.services.cityscape.node_graph.CompositeAssetNodeGraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comopsit-asset-node-graph")
public class CompositeAssetNodeGraphController {

    private final CompositeAssetNodeGraphService nodeGraphService;

    public CompositeAssetNodeGraphController(CompositeAssetNodeGraphService nodeGraphService) {
        this.nodeGraphService = nodeGraphService;
    }

    @GetMapping(path = "/{id}")
    Object getById(@PathVariable("id") Long id) {
        return this.nodeGraphService.getById(id);
    }

    @GetMapping(path = "/relationships")
    Object getRelationships() {
        return this.nodeGraphService.getRelationships();
    }

    @PutMapping(path = "/update-relationship")
    void updateRelationship(@RequestParam("atoa-id") Long atoaId, @RequestParam("rel-code") String relCode) {
        this.nodeGraphService.updateRelationship(atoaId, relCode);
    }

    @PostMapping(path = "/insert-related", produces = "text/plain")
    String insertRelated(@RequestParam("asset_id") Long assetId, @RequestParam("rel_asset_id") Long relAssetId) {
        return this.nodeGraphService.insertRelated(assetId, relAssetId);
    }

    @DeleteMapping(path = "/remove-related")
    void removeRelated(@RequestParam("atoa-id") Long atoaId) {
        this.nodeGraphService.removeRelated(atoaId);
    }

    @PostMapping(path = "/save-positions")
    void savePositions(@RequestBody NodeDTO dto) {
        this.nodeGraphService.savePositions(dto);
    }

    @GetMapping(path = "/threats")
    Object getThreats(@RequestParam("atoa-id") Long atoaId) {
        return this.nodeGraphService.getThreats(atoaId);
    }

    @PutMapping(path = "/update-relationship-probability")
    void saveRelationshipProbability(@RequestParam("id") Long id,
                                     @RequestParam("threat-probability") String threatProbability) {
        this.nodeGraphService.saveRelationshipProbability(id, threatProbability);
    }

    @PutMapping(path = "/update-relationship-active")
    void saveRelationshipActive(@RequestParam("id") Long id,
                                     @RequestParam("active") String active) {
        this.nodeGraphService.saveRelationshipActive(id, active);
    }

}
