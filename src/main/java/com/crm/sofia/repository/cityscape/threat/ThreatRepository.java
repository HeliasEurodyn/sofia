package com.crm.sofia.repository.cityscape.threat;

import com.crm.sofia.model.cityscape.threat.Threat;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ThreatRepository extends PagingAndSortingRepository<Threat, Long> {

    Optional<Threat> findByCreatedByAndId(String userId, Long id);

    List<Threat> findByCreatedBy(String userId);
}
