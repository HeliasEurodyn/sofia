package com.crm.sofia.services.cityscape.node_graph;

import com.crm.sofia.dto.cityscape.node_graph.NodeDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompositeAssetNodeGraphService {
    private final EntityManager entityManager;

    public CompositeAssetNodeGraphService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Object getById(String id) {
        String queryString = "SELECT a.id," +
                " a.code," +
                " a.name," +
                " a.graph_top," +
                " a.graph_left," +
                " a.graph_pos_saved" +
                " FROM composite_asset a WHERE a.id = " + id;
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

        Object relatedAssets;
        relatedAssets = this.getRelatedCompositAssets(id);
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
                " atoa.graph_pos_saved," +
                " atoa.economic_value_priority" +
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
            responce.put("economic_value_priority", field[7]);
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
    public void updateRelationship(Long atoaId, String relCode) {
        String queryString =
                " UPDATE asset_to_composite_asset SET asset_relationship = :asset_relationship WHERE id = :atoa_id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("asset_relationship", relCode);
        query.setParameter("atoa_id", atoaId);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public String insertRelated(Long assetId, Long relAssetId) {
        String queryString =
                "INSERT INTO asset_to_composite_asset (composite_asset_id, asset_id, economic_value_priority) VALUES ( :asset_id , :related_asset_id, 3 )";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("asset_id", assetId);
        query.setParameter("related_asset_id", relAssetId);
        query.executeUpdate();

        String id = (entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).toString();

        List<Long> threats = this.getThreatsByAssetId(relAssetId);

        threats.forEach(t -> {
            String threatQueryString =
                    "INSERT INTO asset_to_composite_asset_threat (asset_to_composite_asset_id, threat_id, active)" +
                            " VALUES ( :asset_to_composite_asset_id , :threat_id, 1 )";

            Query threatQuery = entityManager.createNativeQuery(threatQueryString);
            threatQuery.setParameter("asset_to_composite_asset_id", id);
            threatQuery.setParameter("threat_id", t);
            threatQuery.executeUpdate();
        });

        return id;
    }

    public List<Long> getThreatsByAssetId(Long assetId) {
        String queryString =
                " SELECT  t.id " +
                        " FROM `asset` a " +
                        " INNER JOIN `asset_type` ast ON a.type_id = ast.id " +
                        " INNER JOIN `asset_type_group` atg on ast.`asset_type_group_id` = atg.id " +
                        " INNER JOIN `threat_to_asset_type_group` ttatgp ON ttatgp.asset_type_group_id = atg.id " +
                        " INNER JOIN `threat` t on t.id = ttatgp.threat_id " +
                        " WHERE a.id = :asset_id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("asset_id", assetId);
        List<BigInteger> fields = query.getResultList();

        List<Long> responces = new ArrayList<>();
        for (BigInteger field : fields) {
            responces.add(field.longValue());
        }
        return responces;
    }

    @Transactional
    @Modifying
    public void removeRelated(Long atoaId) {
        String queryString =
                " DELETE FROM asset_to_composite_asset WHERE id = :id";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", atoaId);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public void savePositions(NodeDTO dto) {

        String queryString =
                " UPDATE composite_asset SET " +
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

        dto.getNodes().forEach(node -> {
            String queryString2 =
                    " UPDATE asset_to_composite_asset SET " +
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

    public Object getThreats(Long atoaId) {
        String queryString =
                " SELECT  a.id, t.code, t.name, a.threat_propability, a.active, t.id as threat_id " +
                        " FROM `asset_to_composite_asset_threat` a " +
                        " INNER JOIN `threat` t on t.id = a.threat_id " +
                        " WHERE a.asset_to_composite_asset_id = :atoa_id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("atoa_id", atoaId);
        List<Object[]> fields = query.getResultList();
        List<Object> responces = new ArrayList<>();
        for (Object[] field : fields) {
            Map<String, Object> responce = new HashMap<>();
            responce.put("id", field[0]);
            responce.put("code", field[1]);
            responce.put("name", field[2]);
            responce.put("threat_propability", field[3]);
            responce.put("active", field[4]);
            responce.put("threat_id", field[5]);
            responces.add(responce);
        }
        return responces;
    }

    @Transactional
    @Modifying
    public void saveRelationshipProbability(String id, String threatProbability) {
        String queryString =
                " UPDATE asset_to_composite_asset_threat SET threat_propability = :threat_propability WHERE id = :id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("threat_propability", threatProbability);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    @Modifying
    public void saveRelationshipActive(String id, String active) {
        String queryString =
                " UPDATE asset_to_composite_asset_threat SET active = :active WHERE id = :id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("active", active);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}


