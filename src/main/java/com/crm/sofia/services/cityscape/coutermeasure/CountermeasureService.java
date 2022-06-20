package com.crm.sofia.services.cityscape.coutermeasure;

import com.crm.sofia.model.cityscape.countermeasure.Countermeasure;
import com.crm.sofia.repository.cityscape.countermeasure.CountermeasureRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class CountermeasureService {

    private final CountermeasureRepository countermeasureRepository;
    private final JWTService jwtService;

    public CountermeasureService(CountermeasureRepository countermeasureRepository, JWTService jwtService) {
        this.countermeasureRepository = countermeasureRepository;
        this.jwtService = jwtService;
    }


    public Countermeasure getObject(Long id) {
        Countermeasure countermeasure = this.countermeasureRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Countermeasure does not exist")
        );
        return countermeasure;
    }

    public List<Countermeasure> getObject() {
        List<Countermeasure> countermeasures = this.countermeasureRepository.findAll();
        return countermeasures;
    }

    @Transactional
    public Countermeasure postObject(Countermeasure countermeasure) {
        String userId = jwtService.getUserId();

        if (countermeasure.getId() != null) {
            this.getObject(countermeasure.getId());
        }

        if (countermeasure.getId() == null) {
            countermeasure.setCreatedBy(userId);
            countermeasure.setCreatedOn(Instant.now());
        }

        countermeasure.setModifiedBy(userId);
        countermeasure.setModifiedOn(Instant.now());

        return this.countermeasureRepository.save(countermeasure);
    }

    public Boolean delete(Long id) {
        this.countermeasureRepository.deleteById(id);
        return true;
    }

}
