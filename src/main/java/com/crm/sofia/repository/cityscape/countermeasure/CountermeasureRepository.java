package com.crm.sofia.repository.cityscape.countermeasure;

import com.crm.sofia.model.cityscape.countermeasure.Countermeasure;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CountermeasureRepository extends PagingAndSortingRepository<Countermeasure, Long> {

    Optional<Countermeasure> findByCreatedByAndId(String userId, Long id);

    List<Countermeasure> findByCreatedBy(String userId);

    List<Countermeasure> findAll();
}
