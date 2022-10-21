package com.crm.sofia.services.business_unit;

import com.crm.sofia.dto.sofia.business_unit.BusinessUnitDTO;
import com.crm.sofia.mapper.sofia.business_unit.BusinessUnitMapper;
import com.crm.sofia.model.sofia.business_unit.BusinessUnit;
import com.crm.sofia.repository.business_unit.BusinessUnitRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessUnitDesignerService {

    @Autowired
    private BusinessUnitMapper businessUnitMapper;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private JWTService jwtService;

    public List<BusinessUnitDTO> getObject() {
        List<BusinessUnit> entities = businessUnitRepository.findAll();
        return businessUnitMapper.map(entities);
    }

    public BusinessUnitDTO getObject(String id) {
        Optional<BusinessUnit> optionalEntity = businessUnitRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        BusinessUnit entity = optionalEntity.get();
        BusinessUnitDTO dto = businessUnitMapper.map(entity);

        return dto;
    }

    public BusinessUnitDTO postObject(BusinessUnitDTO businessUnitDTO) {

        BusinessUnit businessUnit = businessUnitMapper.map(businessUnitDTO);
        if (businessUnit.getId() == null) {
            businessUnit.setCreatedOn(Instant.now());
            businessUnit.setCreatedBy(jwtService.getUserId());
        }
        businessUnit.setModifiedOn(Instant.now());
        businessUnit.setModifiedBy(jwtService.getUserId());
        BusinessUnit savedData = businessUnitRepository.save(businessUnit);

        return businessUnitMapper.map(savedData);
    }

    public void deleteObject(String id) {
        Optional<BusinessUnit> optionalEntity = businessUnitRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        businessUnitRepository.deleteById(optionalEntity.get().getId());
    }

}
