package com.crm.sofia.services.cityscape.rtm;

import com.crm.sofia.dto.cityscape.rtm.*;
import com.crm.sofia.dto.sofia.auth.RmtLoginResponseDTO;
import com.crm.sofia.native_repository.rita.rmt.RmtRepository;
import com.crm.sofia.rest_template.cityscape.RtmRestTemplate;
import com.crm.sofia.services.sofia.user.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RmtService {

    private final RtmRestTemplate rtmRestTemplate;
    private final RmtRepository rmtRepository;

    public RmtService(RtmRestTemplate rtmRestTemplate,
                      RmtRepository rmtRepository) {
        this.rtmRestTemplate = rtmRestTemplate;
        this.rmtRepository = rmtRepository;
    }

    public List<RmtDTO> runRiskAssessmentPage(Long page, Long size) {
        List<RmtDTO> rtms = this.rmtRepository.retrieveRiskAssessmentsPage(page, size);
        rtms.forEach(rtm -> {
            List<ServiceDTO> services = this.rmtRepository.retrieveBusinessServices(rtm.getId());
            rtm.setServices(services);

            services.forEach(service -> {
                List<CompositeAssetDTO> compositeAssets = this.rmtRepository.retrieveCompositeAssets(service.getId());
                service.setComposite_assets(compositeAssets);
            });

        });
        return rtms;
    }

    public List<RmtDTO> runRiskAssessmentList() {
        List<RmtDTO> rtms = this.rmtRepository.retrieveRiskAssessments();
        rtms.forEach(rtm -> {
            List<ServiceDTO> services = this.rmtRepository.retrieveBusinessServices(rtm.getId());
            rtm.setServices(services);

            services.forEach(service -> {
                List<CompositeAssetDTO> compositeAssets = this.rmtRepository.retrieveCompositeAssets(service.getId());
                service.setComposite_assets(compositeAssets);
            });

        });

        return rtms;
    }

    public RmtDTO sendToRmt(Long id){
        RmtDTO rmtDTO = this.retrieveRiskAssessmentById(id);
        RmtLoginResponseDTO rmtLoginResponseDTO = this.rtmRestTemplate.login();
        return this.rtmRestTemplate.analysis(rmtDTO, rmtLoginResponseDTO.getAccess_token());
    }

    public RmtDTO retrieveRiskAssessmentById(Long id) {
        RmtDTO rmt = this.rmtRepository.retrieveRiskAssessment(id);

        List<ServiceDTO> services = this.rmtRepository.retrieveBusinessServices(id);
        rmt.setServices(services);

        services.forEach(service -> {
            List<CompositeAssetDTO> compositeAssets = this.rmtRepository.retrieveCompositeAssets(service.getId());
            service.setComposite_assets(compositeAssets);

            List<ServiceCommunicationLinkDTO> serviceLinks = this.rmtRepository.retrieveServiceLinks(service.getId());
            service.setCommunication_links(serviceLinks);

            compositeAssets.forEach(compositeAsset -> {
                List<BasicAssetDTO> basicAssets = this.rmtRepository.retrieveAssetsWithThreats(Long.valueOf(compositeAsset.getId()));
                compositeAsset.setBasic_assets(basicAssets);

                List<CompositeAssetCommunicationLinkDTO> compositeLinks = this.rmtRepository.retrieveCompositeAssetLinks(Long.valueOf(compositeAsset.getId()));
                compositeAsset.setCommunication_links(compositeLinks);

                basicAssets.forEach(basicAsset -> {
                    basicAsset.getThreats().forEach(threat -> {
                        List<CountermeasureDTO> cisControls = this.rmtRepository.retrieveCisControls(compositeAsset.getId(), basicAsset.getId(), threat.getId());
                        threat.setCountermeasures(cisControls);
                    });
                });
            });
        });

        return rmt;
    }
}
