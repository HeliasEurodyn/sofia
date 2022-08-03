package com.crm.sofia.native_repository.rita.rmt;

import com.crm.sofia.dto.cityscape.rtm.*;
import com.crm.sofia.services.sofia.user.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RmtRepository {

    private final EntityManager entityManager;
    private final UserService userService;

    public RmtRepository(EntityManager entityManager,
                         UserService userService) {
        this.entityManager = entityManager;
        this.userService = userService;
    }

    public RmtDTO retrieveRiskAssessment(Long id) {
        RmtDTO rtm = new RmtDTO();

        String queryString =
                "SELECT " +
                        "id, " +
                        "name AS ra_name, " +
                        "created_by, " +
                        "created_on, " +
                        "modified_by, " +
                        "modified_on, " +
                        "short_order, " +
                        "version, " +
                        "name, " +
                        "description " +
                        "FROM risk_assessment " +
                        "WHERE id = :id " +
                        "AND created_by = :user_id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", id);
        query.setParameter("user_id", userService.getCurrentUser().getId());

        List<Object[]> fields = query.getResultList();
        for (Object[] field : fields) {
            rtm.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            rtm.setName(field[1] == null ? null : (String) field[1]);
            rtm.setCreated_on(field[3] == null ? null : ((Timestamp) field[3]).toInstant());
        }

        return rtm;
    }

    public List<RmtDTO> retrieveRiskAssessmentsPage(Long page, Long size) {
        List<RmtDTO> rtms = new ArrayList<>();

        String queryString =
                "SELECT " +
                        "id, " +
                        "name AS ra_name, " +
                        "created_by, " +
                        "created_on, " +
                        "modified_by, " +
                        "modified_on, " +
                        "short_order, " +
                        "version, " +
                        "name, " +
                        "description " +
                        "FROM risk_assessment " +
                        "WHERE created_by = :user_id " +
                        "AND analyzed = 0 " +
                        "ORDER BY created_on DESC " +
                        "LIMIT :size OFFSET :offset";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("user_id", userService.getCurrentUser().getId());
        query.setParameter("size", size);
        query.setParameter("offset", (page * size));

        List<Object[]> fields = query.getResultList();
        for (Object[] field : fields) {
            RmtDTO rtm = new RmtDTO();
            rtm.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            rtm.setName(field[1] == null ? null : (String) field[1]);
            rtm.setCreated_on(field[3] == null ? null : ((Timestamp) field[3]).toInstant());
            rtms.add(rtm);
        }

        return rtms;
    }

    public List<RmtDTO> retrieveRiskAssessments() {
        List<RmtDTO> rtms = new ArrayList<>();

        String queryString =
                "SELECT " +
                        "id, " +
                        "name AS ra_name, " +
                        "created_by, " +
                        "created_on, " +
                        "modified_by, " +
                        "modified_on, " +
                        "short_order, " +
                        "version, " +
                        "name, " +
                        "description " +
                        "FROM risk_assessment " +
                        "WHERE created_by = :user_id " +
                        "AND analyzed = 0 " +
                        "ORDER BY created_on DESC ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("user_id", userService.getCurrentUser().getId());

        List<Object[]> fields = query.getResultList();
        for (Object[] field : fields) {
            RmtDTO rtm = new RmtDTO();
            rtm.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            rtm.setName(field[1] == null ? null : (String) field[1]);
            rtm.setCreated_on(field[3] == null ? null : ((Timestamp) field[3]).toInstant());
            rtms.add(rtm);
        }

        return rtms;
    }

    public List<ServiceDTO> retrieveBusinessServices(Long id) {
        String queryString =
                "SELECT " +
                        "br.id, " +
                        "b.code, " +
                        "b.name, " +
                        "b.description, " +
                        "br.so_confidentiality, " +
                        "br.so_integrity, " +
                        "br.so_availability " +
                        "FROM business_service_to_risk_assessment br  " +
                        "INNER JOIN business_service b ON br.business_service_id = b.id " +
                        "WHERE br.risk_assessment_id = :id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", id);

        List<Object[]> fields = query.getResultList();
        List<ServiceDTO> services = new ArrayList<>();

        for (Object[] field : fields) {
            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            serviceDTO.setName(field[1] == null ? null : (String) field[1]);
            ImpactDTO impact = new ImpactDTO();
            impact.setConfidentiality(field[4] == null ? 0 : (Integer) field[4]);
            impact.setIntegrity(field[5] == null ? 0 : (Integer) field[5]);
            impact.setAvailability(field[6] == null ? 0 : (Integer) field[6]);
            serviceDTO.setImpact(impact);
            services.add(serviceDTO);
        }

        return services;
    }

    public List<CompositeAssetCommunicationLinkDTO> retrieveCompositeAssetLinks(Long compositeAssetId) {
        String queryString =
                "SELECT " +
                        "acal.id, " +
                        "acal.type, " +
                        "aca_source.id AS aca_source_id, " +
                        "aca_dest.id  AS aca_dest_id " +
                        "FROM asset_to_composite_asset_link acal " +
                        "INNER JOIN asset_to_composite_asset aca_source ON aca_source.composite_asset_id = acal.composite_asset_id AND aca_source.asset_id = acal.asset_id_source " +
                        "INNER JOIN asset_to_composite_asset aca_dest ON aca_dest.composite_asset_id = acal.composite_asset_id AND aca_dest.asset_id = acal.asset_id_destination " +
                        "WHERE acal.composite_asset_id = :id " +
                        "AND IFNULL(acal.asset_id_source,0) > 0 " +
                        "AND IFNULL(acal.asset_id_destination,0) > 0 ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", compositeAssetId);

        List<Object[]> fields = query.getResultList();
        List<CompositeAssetCommunicationLinkDTO> links = new ArrayList<>();
        for (Object[] field : fields) {
            CompositeAssetCommunicationLinkDTO link = new CompositeAssetCommunicationLinkDTO();

            link.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            link.setType(field[1] == null ? null : (String) field[1]);
            link.setBasic_id_source(field[2] == null ? null : ((BigInteger) field[2]).longValue());
            link.setBasic_id_destination(field[3] == null ? null : ((BigInteger) field[3]).longValue());
            links.add(link);
        }

        return links;
    }

    public List<String> retrieveIntangibleAssets(Long businessServiceId) {
        String queryString =
                "SELECT " +
                        "ia.id, " +
                        "ia.code " +
                        "FROM intangible_asset_to_composite_asset iaca " +
                        "INNER JOIN intangible_asset ia ON ia.id = iaca.intangible_asset_id " +
                        "WHERE iaca.composite_asset_id = :id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", businessServiceId);

        List<Object[]> fields = query.getResultList();

        List<String> intangibleAssets = new ArrayList<>();
        for (Object[] field : fields) {
            intangibleAssets.add(field[1] == null ? null : (String) field[1]);
        }

        return intangibleAssets;
    }

    public List<ServiceCommunicationLinkDTO> retrieveServiceLinks(Long businessServiceId) {
        String queryString =
                "SELECT " +
                        "cbsl.id, " +
                        "cbsl.type, " +
                        "acas.composite_asset_id AS cais, " +
                        "acas.asset_id AS ais, " +
                        "acad.composite_asset_id AS caid, " +
                        "acad.asset_id AS aid " +
                        "FROM composite_asset_to_business_service_link cbsl " +
                        "INNER JOIN business_service_to_risk_assessment bsra ON bsra.business_service_id = cbsl.business_service_id " +
                        "INNER JOIN asset_to_composite_asset acas ON cbsl.asset_to_composite_asset_id_source = acas.id " +
                        "INNER JOIN asset_to_composite_asset acad ON cbsl.asset_to_composite_asset_id_destination = acad.id " +
                        "WHERE bsra.id = :id " +
                        "AND IFNULL(acas.composite_asset_id,0) > 0 " +
                        "AND IFNULL(acas.asset_id,0) > 0 " +
                        "AND IFNULL(acad.composite_asset_id,0) > 0 " +
                        "AND IFNULL(acad.asset_id,0) > 0 ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", businessServiceId);

        List<Object[]> fields = query.getResultList();

        List<ServiceCommunicationLinkDTO> links = new ArrayList<>();
        for (Object[] field : fields) {
            ServiceCommunicationLinkDTO link = new ServiceCommunicationLinkDTO();

            link.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            link.setType(field[1] == null ? null : (String) field[1]);
            link.setComposite_id_source(field[2] == null ? null : ((BigInteger) field[2]).longValue());
            link.setBasic_id_source(field[3] == null ? null : ((BigInteger) field[3]).longValue());
            link.setComposite_id_destination(field[4] == null ? null : ((BigInteger) field[4]).longValue());
            link.setBasic_id_destination(field[5] == null ? null : ((BigInteger) field[5]).longValue());
            links.add(link);
        }

        return links;
    }

    public List<CountermeasureDTO> retrieveCounterMeasures(Long compositeAssetId, Long assetId, Long threatId) {

        String queryString =
                "SELECT " +
                        "cc.id, " +
                        "cc.code, " +
                        "cc.title, " +
                        "cc.security_function, " +
                        "cc.cis_control_category_id, " +
                        "cc.asset_type_group_id, " +
                        "cc.cis_asset_type, " +
                        "cm.id AS cmid, " +
                        "cm.code AS cmcode, " +
                        "cm.name AS cmname " +
                        "FROM composite_asset ca " +
                        "INNER JOIN asset_to_composite_asset aca ON aca.composite_asset_id = ca.id " +
                        "INNER JOIN asset_to_composite_asset_threat acat ON acat.asset_to_composite_asset_id = aca.id " +
                        "INNER JOIN asset_to_composite_asset_threat_counter_measure acatc ON acatc.asset_to_composite_asset_threat_id = acat.id " +
                        "INNER JOIN countermeasure cm on cm.id = acatc.counter_measure_id " +
                        "INNER JOIN cis_control cc on cc.id = cm.cis_control_id " +
                        "WHERE ca.id = :composite_asset_id " +
                        "AND aca.id = :asset_id " +
                        "AND acat.threat_id = :threat_id";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("composite_asset_id", compositeAssetId);
        query.setParameter("asset_id", assetId);
        query.setParameter("threat_id", threatId);
        List<Object[]> fields = query.getResultList();

        List<CountermeasureDTO> countermeasures = new ArrayList<>();
        for (Object[] field : fields) {
            CountermeasureDTO countermeasure = new CountermeasureDTO();
            countermeasure.setId(field[7] == null ? null : ((BigInteger) field[7]).longValue());
            countermeasure.setCode(field[8] == null ? null : (String) field[8]);
            countermeasure.setName(field[9] == null ? null : (String) field[9]);

            CisControlDTO cisControl = new CisControlDTO();
            cisControl.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            cisControl.setCode(field[1] == null ? null : (String) field[1]);
            cisControl.setTitle(field[2] == null ? null : (String) field[2]);
            cisControl.setSecurity_function(field[3] == null ? null : (String) field[3]);
            cisControl.setCis_asset_type(field[6] == null ? null : (String) field[6]);
            countermeasure.setCisControl(cisControl);

            CountermeasureEfficiencyDTO efficiencyDTO = new CountermeasureEfficiencyDTO();
            efficiencyDTO.setAvailability(1L);
            efficiencyDTO.setConfidentiality(1L);
            efficiencyDTO.setIntegrity(1L);
            countermeasure.setEfficiency(efficiencyDTO);

            countermeasures.add(countermeasure);
        }

        return countermeasures;
    }

    public List<CompositeAssetDTO> retrieveCompositeAssets(Long businessServiceId) {
        String queryString =
                "SELECT " +
                        "ca.id, " +
                        "ca.code, " +
                        "ca.name, " +
                        "ca.description, " +
                        "ca.total_cost_of_ownership, " +
                        "ca.pessimistic_economic_value, " +
                        "ca.common_economic_value, " +
                        "ca.optimistic_economic_value " +
                        "FROM composite_asset_to_business_service cbs " +
                        "INNER JOIN business_service_to_risk_assessment bsra ON bsra.business_service_id = cbs.business_service_id " +
                        "INNER JOIN composite_asset ca ON cbs.composite_asset_id = ca.id " +
                        "WHERE bsra.id = :id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", businessServiceId);

        List<Object[]> fields = query.getResultList();
        List<CompositeAssetDTO> compositeAssets = new ArrayList<>();
        for (Object[] field : fields) {
            CompositeAssetDTO compositeAsset = new CompositeAssetDTO();
            compositeAsset.setEconomic(new CompositeAssetEconomicValuesDTO());
            compositeAsset.setId(field[0] == null ? null : ((BigInteger) field[0]).longValue());
            compositeAsset.setName(field[1] == null ? null : (String) field[1]);
            compositeAsset.getEconomic().setOptimistic((Double) field[5]);
            compositeAsset.getEconomic().setCommon((Double) field[6]);
            compositeAsset.getEconomic().setPessimistic((Double) field[7]);
            compositeAssets.add(compositeAsset);
        }

        return compositeAssets;
    }

    public List<BasicAssetDTO> retrieveAssetsWithThreats(Long compositeAssetId) {
        String queryString =
                "SELECT a.id AS asset_id, " +
                        "a.code AS asset_code, " +
                        "a.name AS asset_name, " +
                        "a.description AS asset_description, " +
                        "aty.id AS asset_type_id,  " +
                        "aty.code AS asset_type_code, " +
                        "act.id AS asset_category_id, " +
                        "act.code AS asset_category_code, " +
                        "vd.id AS asset_vendor_id, " +
                        "vd.name AS asset_vendor_name, " +
                        "a.vendor_product AS asset_vendor_product, " + //10
                        "a.cpe AS asset_cpe, " +
                        "a.detailed_cpe AS asset_detailed_cpe,  " +
                        "acat.id AS asset_to_composite_asset_threat_id, " +
                        "t.id AS threat_id,  " +
                        "t.code AS threat_code, " +
                        "t.name AS threat_name, " +
                        "t.description AS threat_description, " +
                        "t.capec AS threat_capec, " +
                        "t.cwe AS threat_cwe, " +
                        "t.confidentiality AS threat_confidentiality, " + //20
                        "t.integrity AS threat_integrity, " +
                        "t.accountability AS threat_availability, " +
                        "aca.id AS asset_to_composite_asset_id, " +
                        "acat.threat_propability AS threat_propability " + //24
                        "FROM asset_to_composite_asset aca " +
                        "INNER JOIN asset a ON aca.asset_id = a.id " +
                        "LEFT OUTER JOIN asset_type aty ON a.type_id = aty.id " +
                        "LEFT OUTER JOIN asset_category act ON a.asset_category_id = act.id " +
                        "LEFT OUTER JOIN cve_search_vendor vd ON a.vendor_id = vd.id " +
                        "LEFT OUTER JOIN asset_to_composite_asset_threat acat ON aca.id = acat.asset_to_composite_asset_id AND acat.active = 1 " +
                        "INNER JOIN threat t ON t.id = acat.threat_id " +
                        "WHERE aca.composite_asset_id = :id  " +
                        "AND IFNULL(a.detailed_cpe, '') != ''  " +
                        "ORDER BY aca.id, a.id, t.id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("id", compositeAssetId);

        List<Object[]> fields = query.getResultList();
        List<BasicAssetDTO> assets = new ArrayList<>();
        BasicAssetDTO asset = new BasicAssetDTO();
        String assetToCompositeAssetId = "";

        for (Object[] field : fields) {

            String curAssetToCompositeAssetId = field[23].toString();
            if (!curAssetToCompositeAssetId.equals(assetToCompositeAssetId)) {
                asset = new BasicAssetDTO();
                asset.setThreats(new ArrayList<>());
                asset.setId(field[23] == null ? null : ((BigInteger) field[23]).longValue());
                asset.setName(field[1] == null ? null : (String) field[1]);
                asset.setDescription(field[3] == null ? null : (String) field[3]);
                asset.setType_id(field[4] == null ? null : ((BigInteger) field[4]).longValue());
                asset.setType(field[5] == null ? null : (String) field[5]);
                asset.setCategory(field[7] == null ? null : field[7].toString());
                asset.setVendor(field[9] == null ? null : field[9].toString());
                asset.setProduct(field[10] == null ? null : field[10].toString());
                asset.setVersion(field[11] == null ? null : field[11].toString());
                asset.setCpe(field[12] == null ? null : field[12].toString());
                assets.add(asset);
            }

            ThreatDTO threat = new ThreatDTO();
            threat.setId(field[14] == null ? null : ((BigInteger) field[14]).longValue());
            threat.setCode(field[15] == null ? null : (String) field[15]);
            threat.setName(field[16] == null ? null : (String) field[16]);
            threat.setDescription(field[13] == null ? "" : ((BigInteger) field[13]).toString());
            threat.setOccurrence(field[24] == null ? null : (Double) field[24]);
            ThreatImpactSelectionDTO impact = new ThreatImpactSelectionDTO();
            threat.setImpact(impact);
            impact.setConfidentiality(field[20] == null ? false : ((Integer) field[20] != 0));
            impact.setIntegrity(field[21] == null ? false : ((Integer) field[21] != 0));
            impact.setAvailability(field[22] == null ? false : ((Integer) field[22] != 0));
            asset.getThreats().add(threat);
            assetToCompositeAssetId = curAssetToCompositeAssetId;
        }

        return assets;
    }

    public void saveRisk(String assetToCompositeAssetThreatId,
                         Long risk_assessment_id,
                         Long bsToRiskAssessmentId,
                         RiskDTO risk,
                         Double confidentialityScore,
                         Double integrityScore,
                         Double availabilityScore,
                         Double sumScore) {

        Query query =
                entityManager.createNativeQuery("INSERT INTO risk ( cve_id, description, link, asset_to_composite_asset_threat_id, risk_assessment_id, " +
                        "business_service_to_risk_assessment_id, " +
                        "confidentiality, integrity, availability, confidentiality_score, integrity_score, availability_score, score_sum) " +
                        "VALUES (:cve_id, :description, :link, :asset_to_composite_asset_threat_id, :risk_assessment_id, " +
                        ":business_service_to_risk_assessment_id, " +
                        ":confidentiality, :integrity, :availability, :confidentiality_score, :integrity_score, :availability_score, :score_sum);");
        query.setParameter("cve_id", risk.getCve_id());
        query.setParameter("description", risk.getDescription());
        query.setParameter("link", risk.getLink());
        query.setParameter("asset_to_composite_asset_threat_id", Double.valueOf(assetToCompositeAssetThreatId));
        query.setParameter("risk_assessment_id", risk_assessment_id);
        query.setParameter("business_service_to_risk_assessment_id", bsToRiskAssessmentId);

        query.setParameter("confidentiality", risk.getRisk_score().getConfidentiality());
        query.setParameter("integrity", risk.getRisk_score().getIntegrity());
        query.setParameter("availability", risk.getRisk_score().getAvailability());

        query.setParameter("confidentiality_score", confidentialityScore);
        query.setParameter("integrity_score", integrityScore);
        query.setParameter("availability_score", availabilityScore);

        query.setParameter("score_sum", sumScore);

        query.executeUpdate();
    }

    public void saveRiskAssessmentOveralRisk(Long riskAssessmentId) {
        Query query =
                entityManager.createNativeQuery(
                        " UPDATE risk_assessment ra " +
                                " SET ra.overal_risk = " +
                                " (SELECT r.score_sum FROM risk r WHERE r.risk_assessment_id = ra.id ORDER BY r.score_sum DESC LIMIT 1) , " +
                                "ra.analyzed = 0 " +
                                " WHERE id = :id "
                );
        query.setParameter("id", riskAssessmentId);
        query.executeUpdate();
    }

    public void saveServiceOverallRisk(Long riskAssessmentId, Long bsToRiskAssessment) {
        Query query =
                entityManager.createNativeQuery(
                        " UPDATE business_service_to_risk_assessment bsra " +
                                " SET bsra.overal_risk = " +
                                " ( SELECT r.score_sum FROM risk r " +
                                " WHERE r.risk_assessment_id = :ra_id AND r.business_service_to_risk_assessment_id = :bsra_id " +
                                " ORDER BY r.score_sum DESC LIMIT 1 ) " +
                                " WHERE bsra.id = :bsra_id ");

        query.setParameter("ra_id", riskAssessmentId);
        query.setParameter("bsra_id", bsToRiskAssessment);

        query.executeUpdate();
    }

    public void deleteExistingRisksForRiskAssesment(Long id) {
        Query query =
                entityManager.createNativeQuery("DELETE FROM `risk` WHERE risk_assessment_id = :risk_assessment_id ");
        query.setParameter("risk_assessment_id", id);
        query.executeUpdate();
    }

    public List<RiskDTO> retrieveRisks(Long riskAssessmentId, Long assetToCompositeAssetThreatId) {
        String queryString =
                "SELECT " +
                        "r.id, " + //0
                        "r.cve_id, " +
                        "r.description, " +
                        "r.link, " +
                        "r.asset_to_composite_asset_threat_id, " +
                        "r.risk_assessment_id, " + //5

                        "r.confidentiality, " + //6
                        "r.integrity, " +
                        "r.availability, " +
                        "r.confidentiality_score, " +
                        "r.integrity_score, " +
                        "r.availability_score, " +
                        "r.score_sum " +

                        "FROM risk r " +
                        "WHERE r.asset_to_composite_asset_threat_id = :asset_to_composite_asset_threat_id " +
                        "AND r.risk_assessment_id = :risk_assessment_id ";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("risk_assessment_id", riskAssessmentId);
        query.setParameter("asset_to_composite_asset_threat_id", assetToCompositeAssetThreatId);

        List<Object[]> fields = query.getResultList();
        List<RiskDTO> risks = new ArrayList<>();
        for (Object[] field : fields) {
            RiskDTO risk = new RiskDTO();
            risk.setCve_id(field[1] == null ? null : (String) field[1]);
            risk.setDescription("");
            risk.setLink("");
            RiskScoreDTO riskScore = new RiskScoreDTO();
            riskScore.setConfidentiality(field[9] == null ? null : (Double) field[9]);
            riskScore.setIntegrity(field[10] == null ? null : (Double) field[10]);
            riskScore.setAvailability(field[11] == null ? null : (Double) field[11]);
            risk.setRisk_score(riskScore);

            risks.add(risk);
        }

        return risks;
    }
}
