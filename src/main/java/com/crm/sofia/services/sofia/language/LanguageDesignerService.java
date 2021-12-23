package com.crm.sofia.services.sofia.language;

import com.crm.sofia.dto.sofia.language.LanguageDTO;
import com.crm.sofia.mapper.sofia.language.LanguageMapper;
import com.crm.sofia.model.sofia.language.Language;
import com.crm.sofia.repository.sofia.language.LanguageRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageDesignerService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private JWTService jwtService;

    public List<LanguageDTO> getObject() {
        List<Language> entites = languageRepository.findAll();
        return languageMapper.map(entites);
    }

    public LanguageDTO getObject(Long id) {
        Optional<Language> optionalEntity = languageRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Language entity = optionalEntity.get();
        LanguageDTO dto = languageMapper.map(entity);
        return dto;
    }

    public LanguageDTO postObject(LanguageDTO objectDto) {
        Language object = languageMapper.map(objectDto);
        if (objectDto.getId() == null) {
            object.setCreatedOn(Instant.now());
            object.setCreatedBy(jwtService.getUserId());
        }
        object.setModifiedOn(Instant.now());
        object.setModifiedBy(jwtService.getUserId());
        Language savedData = languageRepository.save(object);

        return languageMapper.map(savedData);
    }

    public void deleteObject(Long id) {
        Optional<Language> optionalEntity = languageRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        languageRepository.deleteById(optionalEntity.get().getId());
    }

}
