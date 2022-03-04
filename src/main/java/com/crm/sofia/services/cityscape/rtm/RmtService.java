package com.crm.sofia.services.cityscape.rtm;

import com.crm.sofia.dto.cityscape.rtm.*;
import com.crm.sofia.dto.sofia.auth.RmtLoginResponseDTO;
import com.crm.sofia.native_repository.rita.rmt.RmtRepository;
import com.crm.sofia.rest_template.cityscape.RtmRestTemplate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    @Transactional
    @Modifying
    public RmtDTO sendToRmt(Long id) {

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

                        List<CountermeasureDTO> counterMeasures = this.rmtRepository.retrieveCounterMeasures(compositeAsset.getId(),
                                basicAsset.getId()
                                , threat.getId());
                        threat.setCountermeasures(counterMeasures);

                    });
                });
            });
        });

        RmtLoginResponseDTO rmtLoginResponseDTO = this.rtmRestTemplate.login();
        RmtDTO rmtResponse = this.rtmRestTemplate.analysis(rmt, rmtLoginResponseDTO.getAccess_token());
        this.saveRmtResponse(rmtResponse);
        this.rmtRepository.saveRiskAssessmentOveralRisk(rmt.getId());
        rmt.getServices().forEach(service -> {
            this.rmtRepository.saveServiceOverallRisk(rmt.getId(), service.getId());
        });
        return rmtResponse;
    }

    public void saveRmtResponse(RmtDTO rmt) {
        this.rmtRepository.deleteExistingRisksForRiskAssesment(rmt.getId());
        rmt.getServices().forEach(service -> {
            service.getComposite_assets().forEach(compositeAsset -> {
                compositeAsset.getBasic_assets().forEach(basicAsset -> {
                    basicAsset.getThreats().forEach(threat -> {
                        threat.getRisk().forEach(risk -> {

                            Double confidentiality = risk.getRisk_score().getConfidentiality();
                            Double integrity = risk.getRisk_score().getIntegrity();
                            Double availability = risk.getRisk_score().getAvailability();

                            Double sumScore = confidentiality + integrity + availability;

                            this.rmtRepository.saveRisk(
                                    threat.getDescription(),
                                    rmt.getId(),
                                    service.getId(),
                                    risk,
                                    confidentiality,
                                    integrity,
                                    availability,
                                    sumScore
                                    );
                        });
                    });
                });
            });
        });
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

                        List<RiskDTO> risks = this.rmtRepository.retrieveRisks(id, Long.valueOf(threat.getDescription()));
                        threat.setRisk(risks);

                        List<CountermeasureDTO> counterMeasures = this.rmtRepository.retrieveCounterMeasures(compositeAsset.getId(), basicAsset.getId(), threat.getId());
                        threat.setCountermeasures(counterMeasures);

                    });
                });
            });
        });

        return rmt;
    }
}
