package com.crm.sofia.services.cityscape.node_graph;

import com.crm.sofia.dto.cityscape.node_graph.NodeDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NodeGraphService {
    private final EntityManager entityManager;

    public NodeGraphService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Object getAsset(String id, String type) {
        String prefix = "";
        if (type.toUpperCase().equals("COMPOSIT")) {
            prefix = "composite_";
        }

        String queryString = "SELECT a.id," +
                " a.code," +
                " a.name," +
                " a.graph_top," +
                " a.graph_left," +
                " a.graph_pos_saved" +
                " FROM " + prefix + "asset a WHERE a.id = " + id;
        Query query = entityManager.createNativeQuery(queryString);
        Map<String, Object> responce = new HashMap<>();

        List<Object[]> fields = query.getResultList();
        for (Object[] field : fields) {
            responce.put("id", field[0].toString());
            responce.put("code", field[1].toString());
            responce.put("name", field[2].toString());
            responce.put("graph_top", field[3]);
            responce.put("graph_left", field[4]);
            responce.put("graph_pos_saved", field[5]);
        }

        Object relatedAssets ;
        if (type.toUpperCase().equals("COMPOSIT")) {
            relatedAssets = this.getRelatedCompositAssets(id);
        } else {
            relatedAssets = this.getRelatedAssets(id);
        }

        responce.put("assets", relatedAssets);
        return responce;
    }

    public Object getRelatedAssets(String id) {
        String queryString = " SELECT " +
                " a.id, " +
                " a.code," +
                " a.name," +
                " atoa.id AS atoa_id, " +
                " atoa.asset_relationship AS relationship_id," +
                " atoa.asset_relationship AS relationship_code," +
                " ar.name AS relationship_name," +
                " atoa.graph_top," +
                " atoa.graph_left," +
                " atoa.graph_pos_saved" +
                " FROM `asset` a " +
                " INNER JOIN `asset_to_asset` atoa ON a.id = atoa.related_asset_id " +
                " LEFT OUTER JOIN  `asset_relationship` ar ON ar.code = atoa.asset_relationship " +
                " WHERE atoa.asset_id = " + id;

        Query query = entityManager.createNativeQuery(queryString);
        Object[] responceList = {};

        List<Object[]> fields = query.getResultList();
        responceList = new Object[fields.size()];
        int counter = 0;
        for (Object[] field : fields) {
            Map<String, Object> responce = new HashMap<>();
            responce.put("id", field[0]);
            responce.put("code", field[1]);
            responce.put("name", field[2]);
            responce.put("atoa_id", field[3]);
            responce.put("relationship_id", field[4]);
            responce.put("relationship_code", field[5]);
            responce.put("relationship_name", field[6]);
            responce.put("graph_top", field[7]);
            responce.put("graph_left", field[8]);
            responce.put("graph_pos_saved", field[9]);
            responceList[counter] = responce;
            counter++;
        }

        return responceList;
    }

    public Object getRelatedCompositAssets(String id) {
        String queryString = " SELECT " +
                " a.id, " +
                " a.code," +
                " a.name," +
                " atoa.id AS atoa_id, " +
                " atoa.graph_top," +
                " atoa.graph_left," +
                " atoa.graph_pos_saved" +
                " FROM `asset` a " +
                " INNER JOIN `asset_to_composite_asset` atoa ON a.id = atoa.asset_id " +
                " WHERE atoa.composite_asset_id = " + id;

        Query query = entityManager.createNativeQuery(queryString);
        Object[] responceList = {};

        List<Object[]> fields = query.getResultList();
        responceList = new Object[fields.size()];
        int counter = 0;
        for (Object[] field : fields) {
            Map<String, Object> responce = new HashMap<>();
            responce.put("id", field[0]);
            responce.put("code", field[1]);
            responce.put("name", field[2]);
            responce.put("atoa_id", field[3]);
            responce.put("graph_top", field[4]);
            responce.put("graph_left", field[5]);
            responce.put("graph_pos_saved", field[6]);
            responceList[counter] = responce;
            counter++;
        }

        return responceList;
    }

    public Object getRelationships() {
        String queryString = "SELECT a.id, a.code, a.name FROM asset_relationship a  ";
        Query query = entityManager.createNativeQuery(queryString);
        List<Object> responces = new ArrayList<>();

        List<Object[]> fields = query.getResultList();
        for (Object[] field : fields) {
            Map<String, Object> responce = new HashMap<>();
            responce.put("id", field[0]);
            responce.put("code", field[1]);
            responce.put("name", field[2]);
            responces.add(responce);
        }
        return responces;
    }

    @Transactional
    @Modifying
    public void updateAssetRelationship(Long atoaId, String relCode, String type) {
        String prefix = "";
        if (type.toUpperCase().equals("COMPOSIT")) {
            prefix = "composite_";
        }

        String queryString =
                " UPDATE asset_to_" + prefix + "asset SET asset_relationship = :asset_relationship WHERE id = :atoa_id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("asset_relationship", relCode);
        query.setParameter("atoa_id", atoaId);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public String insertRelatedAsset(Long assetId, Long relAssetId, String type) {
        String queryString = "";
        if (type.toUpperCase().equals("COMPOSIT")) {
            queryString =
                    "INSERT INTO asset_to_composite_asset (composite_asset_id, asset_id) VALUES ( :asset_id , :related_asset_id )";
        } else {
            queryString =
                    "INSERT INTO asset_to_asset (asset_id,related_asset_id) VALUES ( :asset_id , :related_asset_id )";
        }



        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("asset_id", assetId);
        query.setParameter("related_asset_id", relAssetId);
        query.executeUpdate();

        String id = (entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).toString();
        return id;
    }

    @Transactional
    @Modifying
    public void removeRelatedAsset(Long atoaId, String type) {
        String prefix = "";
        if (type.toUpperCase().equals("COMPOSIT")) {
            prefix = "composite_";
        }

        String queryString =
                " DELETE FROM asset_to_" + prefix + "asset WHERE id = :id";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", atoaId);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public void saveAssetPositions(NodeDTO dto, String type) {
        String prefix = "";
        if (type.toUpperCase().equals("COMPOSIT")) {
            prefix = "composite_";
        }

        String queryString =
                " UPDATE " + prefix + "asset SET " +
                        "graph_top = :graph_top , " +
                        "graph_left = :graph_left , " +
                        "graph_pos_saved = :graph_pos_saved " +
                        "WHERE id = :id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("graph_top", dto.getPosition().getTop());
        query.setParameter("graph_left", dto.getPosition().getLeft());
        query.setParameter("graph_pos_saved", 1);
        query.setParameter("id", dto.getId());
        query.executeUpdate();

        String finalPrefix = prefix;
        dto.getNodes().forEach(node -> {
            String queryString2 =
                    " UPDATE asset_to_" + finalPrefix + "asset SET " +
                            "graph_top = :graph_top , " +
                            "graph_left = :graph_left , " +
                            "graph_pos_saved = :graph_pos_saved " +
                            "WHERE id = :id ";

            Query query2 = entityManager.createNativeQuery(queryString2);
            query2.setParameter("graph_top", node.getPosition().getTop());
            query2.setParameter("graph_left", node.getPosition().getLeft());
            query2.setParameter("graph_pos_saved", 1);
            query2.setParameter("id", node.getId());
            query2.executeUpdate();
        });


    }

}


