package com.crm.sofia.services.form;

import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.mapper.form.FormMapper;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final JWTService jwtService;
    private final FormDynamicQueryService formDynamicQueryService;

    public FormService(FormRepository formRepository,
                       FormMapper formMapper,
                       JWTService jwtService,
                       FormDynamicQueryService formDynamicQueryService) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.jwtService = jwtService;
        this.formDynamicQueryService = formDynamicQueryService;
    }

    @Transactional
    public FormDTO postObject(FormDTO formDTO) {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setCreatedOn(Instant.now());
        formEntity.setModifiedOn(Instant.now());
        formEntity.setCreatedBy(jwtService.getUserId());
        formEntity.setModifiedBy(jwtService.getUserId());

        FormEntity createdFormEntity = this.formRepository.save(formEntity);
        return this.formMapper.map(createdFormEntity);
    }

    @Transactional
    public FormDTO putObject(FormDTO formDTO) {
        FormEntity formEntity = this.formMapper.map(formDTO);
        formEntity.setModifiedOn(Instant.now());
        formEntity.setModifiedBy(jwtService.getUserId());

        FormEntity createdFormEntity = this.formRepository.save(formEntity);
        return this.formMapper.map(createdFormEntity);
    }


    public List<FormDTO> getObject() {
        List<FormEntity> formEntities = this.formRepository.findAll();
        return this.formMapper.map(formEntities);
    }

    public FormDTO getObject(Long id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Form does not exist");
        }
        FormDTO formDTO = this.formMapper.map(optionalFormEntity.get());
        return formDTO;
    }

    public FormDTO getObjectAndRetrieveData(Long id, String selectionId) {
        FormDTO formDTO = this.getObject(id);
        this.formDynamicQueryService.retrieveComponentData(formDTO.getComponent(), selectionId);
        return formDTO;
    }

    public void deleteObject(Long id) {
        Optional<FormEntity> optionalFormEntity = this.formRepository.findById(id);
        if (!optionalFormEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "FormEntity does not exist");
        }
        this.formRepository.deleteById(optionalFormEntity.get().getId());
    }

    public void save(Long id, Map<String, Map<String, Object>> parameters) throws Exception {

        /*Retrieve formObject from Database*/
        FormDTO formDTO = this.getObject(id);

        /*Send to formDynamicQueryService to generate the queries & Save*/
        this.formDynamicQueryService.generateQueriesAndSave(formDTO.getComponent(), parameters);
    }

}
