package com.crm.sofia.services.cityscape.cve_search;

import com.crm.sofia.dto.cityscape.cve_search.CveSearchSettingsDTO;
import com.crm.sofia.dto.cityscape.cve_search.VendorDTO;
import com.crm.sofia.mapper.cityscape.cve_search.CveSearchSettingsMapper;
import com.crm.sofia.model.cityscape.cve_search.CveSearchSettings;
import com.crm.sofia.repository.cityscape.cve_search.CveSearchSettingsRepository;
import com.crm.sofia.rest_call.cityscape.CveSearchRestTemplate;
import com.crm.sofia.services.sofia.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CveSearchSettingsService {
    private final CveSearchSettingsRepository cveSearchSettingsRepository;
    private final CveSearchSettingsMapper cveSearchSettingsMapper;
    private final CveSearchRestTemplate cveSearchRestTemplate;
    private final UserService userService;
    private final EntityManager entityManager;

    public CveSearchSettingsService(CveSearchSettingsRepository cveSearchSettingsRepository,
                                    CveSearchSettingsMapper cveSearchSettingsMapper,
                                    CveSearchRestTemplate cveSearchRestTemplate,
                                    UserService userService,
                                    EntityManager entityManager) {
        this.cveSearchSettingsRepository = cveSearchSettingsRepository;
        this.cveSearchSettingsMapper = cveSearchSettingsMapper;
        this.cveSearchRestTemplate = cveSearchRestTemplate;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    public CveSearchSettingsDTO getObject() {
        List<CveSearchSettings> cveSearchSettings = this.cveSearchSettingsRepository.findAll();
        if (cveSearchSettings.size() == 0) {
            CveSearchSettingsDTO cveSearchSettingsDTO = new CveSearchSettingsDTO();
            cveSearchSettingsDTO.setCreatedOn(Instant.now());
            cveSearchSettingsDTO.setCreatedBy(userService.getLoggedInUser().getId());
            return cveSearchSettingsDTO;
        }
        return this.cveSearchSettingsMapper.map(cveSearchSettings.get(0));
    }

    @Transactional
    public CveSearchSettingsDTO postObject(CveSearchSettingsDTO dto) {

        CveSearchSettingsDTO cveSearchSettingsDTO = this.getObject();
        cveSearchSettingsDTO.setServerUrl(dto.getServerUrl());
        cveSearchSettingsDTO.setVendorTableName(dto.getVendorTableName());

        CveSearchSettings cveSearchSettings = this.cveSearchSettingsMapper.map(cveSearchSettingsDTO);
        cveSearchSettings.setModifiedOn(Instant.now());
        cveSearchSettings.setModifiedBy(userService.getLoggedInUser().getId());

        CveSearchSettings createdCveSearchSettings = this.cveSearchSettingsRepository.save(cveSearchSettings);
        return this.cveSearchSettingsMapper.map(createdCveSearchSettings);
    }

    @Transactional
    public void updateVendors() {
        VendorDTO vendorDTO = cveSearchRestTemplate.getVendors("*");
        CveSearchSettingsDTO cveSearchSettingsDTO = this.getObject();
        String uuid = UUID.randomUUID().toString();
        String tableName = cveSearchSettingsDTO.getVendorTableName().replace(" ","");
        vendorDTO.getVendor().forEach(vendor -> {

            Boolean vendorExists = this.vendorExists(tableName, vendor);

            if(vendorExists){
                this.updateVendor(tableName, vendor, uuid);
            } else {
                this.insertVendor(tableName, vendor, uuid);
            }

        });

        this.deleteVendors(tableName, uuid);
    }

    public boolean vendorExists(String tableName, String vendor) {
        String queryString =
                " SELECT * FROM "+tableName+" WHERE name = :vendor";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("vendor",vendor);
        List<Object[]> dataList = query.getResultList();
        if (dataList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void updateVendor(String tableName, String vendor, String updateid) {
            String queryString = "UPDATE "+tableName+" set name=:vendor,update_id=:updateid";
            Query query2 = entityManager.createNativeQuery(queryString);
            query2.setParameter("vendor",vendor);
            query2.setParameter("updateid",updateid);
            query2.executeUpdate();
    }

    public void insertVendor(String tableName, String vendor, String updateid) {
            String queryString =
                    " INSERT INTO "+tableName+" (name, update_id) VALUES (:vendor,:updateid);";

            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter("vendor",vendor);
            query.setParameter("updateid",updateid);
            query.executeUpdate();
    }

    public void deleteVendors(String tableName, String updateid) {
        String queryString = "DELETE FROM "+tableName+" WHERE update_id != :updateid";
        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("updateid",updateid);
        query.executeUpdate();
    }

}
