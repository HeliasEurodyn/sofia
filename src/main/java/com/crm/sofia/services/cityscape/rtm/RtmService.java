package com.crm.sofia.services.cityscape.rtm;

import com.crm.sofia.dto.cityscape.rtm.*;
import com.crm.sofia.rest_template.cityscape.RtmRestTemplate;
import com.crm.sofia.services.sofia.user.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
public class RtmService {

    private final RtmRestTemplate rtmRestTemplate;
    private final EntityManager entityManager;
    private final UserService userService;

    public RtmService(RtmRestTemplate rtmRestTemplate,
                      EntityManager entityManager,
                      UserService userService) {
        this.rtmRestTemplate = rtmRestTemplate;
        this.entityManager = entityManager;
        this.userService = userService;
    }

    public RtmDTO postObject(RtmDTO dto) {
        return rtmRestTemplate.runRiskAssessment(dto);
    }

    public RtmDTO postObject2(RtmDTO dto) {
        return dto;
    }

    public RtmDTO getObject(RtmDTO dto) {
        return dto;
    }

//    public void runRiskAssessment(Long id) {
//        RtmDTO rtmDTO = new RtmDTO();
//        this.retrieveRiskAssessment(rtmDTO, id);
//
//        EcosystemDTO ecosystemDTO = new EcosystemDTO();
//        rtmDTO.setEcosystem(Arrays.asList(ecosystemDTO));
//        ecosystemDTO.setEcosystem_id(userService.getLoggedInUser().getId().toString());
//        ecosystemDTO.setEcosystem_name(userService.getLoggedInUser().getUsername());
//
//        List<ServiceDTO> services = this.retrieveBusinessServices(id);
//        ecosystemDTO.setService(services);
//
//        services.forEach(service -> {
//            List<CompositeAssetDTO> compositeAssets = this.retrieveCompositeAssets(Long.valueOf(service.getService_id()));
//            service.setComposite_asset(compositeAssets);
//            compositeAssets.forEach(compositeAsset -> {
//                List<BasicAssetDTO> basicAssets = this.retrieveAssets(Long.valueOf(compositeAsset.getComposite_id()));
//                compositeAsset.setBasic_asset(basicAssets);
//                basicAssets.forEach(basicAsset -> {
//
//                });
//            });
//        });
//    }

//    private void retrieveRiskAssessment(RtmDTO dto, Long id) {
//        String queryString =
//                "SELECT " +
//                "id, " +
//                "code, " +
//                "created_by, " +
//                "created_on, " +
//                "modified_by, " +
//                "modified_on, " +
//                "short_order, " +
//                "version, " +
//                "name, " +
//                "description " +
//                "FROM risk_assessment " +
//                "WHERE id = :id";
//
//        Query query = entityManager.createNativeQuery(queryString);
//        query.setParameter("id", id);
//
//        List<Object[]> fields = query.getResultList();
//        for (Object[] field : fields) {
//            dto.setApi_key(field[0].toString());
//            dto.setRisk_analysis(field[1].toString());
//        }
//    }

//    private List<ServiceDTO> retrieveBusinessServices(Long id) {
//        String queryString =
//                "SELECT " +
//                        "b.id,  " +
//                        "b.code,  " +
//                        "b.name,  " +
//                        "b.description  " +
//                        "FROM business_service_to_risk_assessment br  " +
//                        "INNER JOIN business_service b ON br.business_service_id = b.id " +
//                        "WHERE br.risk_assessment_id = :id ";
//
//        Query query = entityManager.createNativeQuery(queryString);
//        query.setParameter("id", id);
//
//        List<Object[]> fields = query.getResultList();
//        List<ServiceDTO> services = new ArrayList<>();
//        for (Object[] field : fields) {
//            ServiceDTO serviceDTO = new ServiceDTO();
//            serviceDTO.setService_id(field[0].toString());
//            serviceDTO.setService_name(field[1].toString());
//            services.add(serviceDTO);
//        }
//
//        return services;
//    }
//
//    private List<CompositeAssetDTO> retrieveCompositeAssets(Long businessServiceId) {
//        String queryString =
//                "SELECT  " +
//                        "ca.id,  " +
//                        "ca.code,  " +
//                        "ca.name,  " +
//                        "ca.description,  " +
//                        "ca.total_cost_of_ownership, " +
//                        "ca.so_confidentiality,  " +
//                        "ca.so_integrity,  " +
//                        "ca.so_availability " +
//                        "FROM composite_asset_to_business_service cbs  " +
//                        "INNER JOIN composite_asset ca ON cbs.composite_asset_id = ca.id " +
//                        "WHERE cbs.business_service_id = :id ";
//
//        Query query = entityManager.createNativeQuery(queryString);
//        query.setParameter("id", businessServiceId);
//
//        List<Object[]> fields = query.getResultList();
//        List<CompositeAssetDTO> compositeAssets = new ArrayList<>();
//        for (Object[] field : fields) {
//            CompositeAssetDTO compositeAsset = new CompositeAssetDTO();
//            compositeAsset.setImpact(new ImpactDTO());
//
//            compositeAsset.setComposite_id(field[0].toString());
//            compositeAsset.setComposite_name(field[1].toString());
//            compositeAsset.getImpact().setConfidentiality(field[5].toString());
//            compositeAsset.getImpact().setIntegrity(field[6].toString());
//            compositeAsset.getImpact().setAvailability(field[7].toString());
//            compositeAssets.add(compositeAsset);
//        }
//
//        return compositeAssets;
//    }
//
//    private List<BasicAssetDTO> retrieveAssets(Long compositeAssetId) {
//
//        String queryString =
//                "SELECT a.id,  " +
//                        "a.code,  " +
//                        "a.name,  " +
//                        "a.description,  " +
//                        "aty.id AS asset_type_id,   " +
//                        "aty.code AS asset_type_code,  " +
//                        "act.id AS asset_category_id,  " +
//                        "act.code AS asset_category_code,  " +
//                        "vd.id AS vendor_id,  " +
//                        "vd.name AS vendor_name,  " +
//                        "a.vendor_product,  " +
//                        "a.cpe,  " +
//                        "a.detailed_cpe  " +
//                        "FROM asset_to_composite_asset aca  " +
//                        "INNER JOIN asset a ON aca.asset_id = a.id  " +
//                        "LEFT OUTER JOIN asset_type aty ON a.type_id = aty.id  " +
//                        "LEFT OUTER JOIN asset_category act ON a.asset_category_id = act.id  " +
//                        "LEFT OUTER JOIN cve_search_vendor vd ON a.vendor_id = vd.id  " +
//                        "WHERE aca.composite_asset_id = :id ";
//
//        Query query = entityManager.createNativeQuery(queryString);
//        query.setParameter("id", compositeAssetId);
//
//        List<Object[]> fields = query.getResultList();
//        List<BasicAssetDTO> assets = new ArrayList<>();
//        for (Object[] field : fields) {
//            BasicAssetDTO asset = new BasicAssetDTO();
//            asset.setBasic_asset_id(field[0].toString());
//            asset.setBasic_asset_name(field[1].toString());
//            asset.setBasic_asset_type_id(field[4].toString());
//            asset.setBasic_asset_type(field[5].toString());
//            asset.setBasic_asset_part(field[11].toString());
//            asset.setBasic_asset_vendor(field[9].toString());
//            asset.setBasic_asset_product(field[10].toString());
//            asset.setBasic_asset_version(field[12].toString());
//            assets.add(asset);
//        }
//
//        return assets;
//    }
//
//    private List<BasicAssetDTO> retrieveAssetsWithThreats(Long compositeAssetId) {
//
//        String queryString =
//                "SELECT a.id AS asset_id, "+
//                        "a.code AS asset_code, "+
//                        "a.name AS asset_name, "+
//                        "a.description AS asset_description, "+
//                        "aty.id AS asset_type_id,  "+
//                        "aty.code AS asset_type_code, "+
//                        "act.id AS asset_category_id, "+
//                        "act.code AS asset_category_code, "+
//                        "vd.id AS asset_vendor_id, "+
//                        "vd.name AS asset_vendor_name, "+
//                        "a.vendor_product AS asset_vendor_product, "+
//                        "a.cpe AS asset_cpe, "+
//                        "a.detailed_cpe AS asset_detailed_cpe,  "+
//                        "acat.id AS asset_to_composite_asset_threat_id, "+
//                        "t.id AS threat_id,  "+
//                        "t.code AS threat_code, "+
//                        "t.name AS threat_name, "+
//                        "t.description AS threat_description, "+
//                        "t.capec AS threat_capec, "+
//                        "t.cwe AS threat_cwe, "+
//                        "t.confidentiality AS threat_confidentiality, "+
//                        "t.integrity AS threat_integrity, "+
//                        "t.accountability AS threat_availability, "+
//                        "aca.id AS asset_to_composite_asset_id "+
//                        "FROM asset_to_composite_asset aca "+
//                        "INNER JOIN asset a ON aca.asset_id = a.id "+
//                        "LEFT OUTER JOIN asset_type aty ON a.type_id = aty.id "+
//                        "LEFT OUTER JOIN asset_category act ON a.asset_category_id = act.id "+
//                        "LEFT OUTER JOIN cve_search_vendor vd ON a.vendor_id = vd.id "+
//                        "LEFT OUTER JOIN asset_to_composite_asset_threat acat ON aca.id = acat.asset_to_composite_asset_id "+
//                        "INNER JOIN threat t ON t.id = acat.threat_id "+
//                        "WHERE aca.composite_asset_id = :id  "+
//                        "ORDER BY aca.id, a.id, t.id ";
//
//        Query query = entityManager.createNativeQuery(queryString);
//        query.setParameter("id", compositeAssetId);
//
//        List<Object[]> fields = query.getResultList();
//        List<BasicAssetDTO> assets = new ArrayList<>();
//        BasicAssetDTO asset = new BasicAssetDTO();
//        String assetToCompositeAssetId = "";
//        for (Object[] field : fields) {
//
//            String curAssetToCompositeAssetId = field[23].toString();
//            if(curAssetToCompositeAssetId != assetToCompositeAssetId){
//                asset = new BasicAssetDTO();
//                asset.setThreats(new ArrayList<>());
//                asset.setBasic_asset_id(field[0].toString());
//                asset.setBasic_asset_name(field[1].toString());
//                asset.setBasic_asset_type_id(field[4].toString());
//                asset.setBasic_asset_type(field[5].toString());
//                asset.setBasic_asset_part(field[11].toString());
//                asset.setBasic_asset_vendor(field[9].toString());
//                asset.setBasic_asset_product(field[10].toString());
//                asset.setBasic_asset_version(field[12].toString());
//                assets.add(asset);
//            }
//
//
//
//            assetToCompositeAssetId = curAssetToCompositeAssetId;
//        }
//
//        return assets;
//    }


}
