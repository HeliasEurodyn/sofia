package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.list.ListComponentFieldDTO;
import com.crm.sofia.dto.sofia.list.ListDTO;
import com.crm.sofia.mapper.sofia.list.ListMapper;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.repository.sofia.list.ListRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@Service
public class ListDesignerService {

    private final ListRepository listRepository;
    private final ListMapper listMapper;
    private final JWTService jwtService;

    public ListDesignerService(ListRepository listRepository,
                               ListMapper listMapper,
                               JWTService jwtService) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
        this.jwtService = jwtService;
    }

    @Transactional
    public ListDTO postObject(ListDTO listDTO) {
        ListEntity listEntity = this.listMapper.map(listDTO);
        listEntity.setCreatedOn(Instant.now());
        listEntity.setModifiedOn(Instant.now());
        listEntity.setCreatedBy(jwtService.getUserId());
        listEntity.setModifiedBy(jwtService.getUserId());
        listEntity.setInstanceVersion(UUID.randomUUID().toString());
        ListEntity createdListEntity = this.listRepository.save(listEntity);
        return this.listMapper.map(createdListEntity);
    }

    @Transactional
    public ListDTO putObject(ListDTO listDTO) {
        ListEntity listEntity = this.listMapper.map(listDTO);
        listEntity.setModifiedOn(Instant.now());
        listEntity.setModifiedBy(jwtService.getUserId());
        listEntity.setInstanceVersion(UUID.randomUUID().toString());
        ListEntity createdListEntity = this.listRepository.save(listEntity);
        return this.listMapper.map(createdListEntity);
    }

    public List<ListDTO> getObject() {
        List<ListEntity> views = this.listRepository.findAll();
        return this.listMapper.map(views);
    }

    public ListDTO getObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        ListDTO listDTO = this.listMapper.map(optionalListEntity.get());
        listDTO.getComponent().getComponentPersistEntityList().sort(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder));
        listDTO.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));

        return listDTO;
    }

    public ListDTO getObjectByName(String name) {
        ListEntity listEntity = this.listRepository.findFirstByName(name);
        if (listEntity == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        return this.listMapper.map(listEntity);
    }

    public void deleteObject(Long id) {
        Optional<ListEntity> optionalListEntity = this.listRepository.findById(id);
        if (!optionalListEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        this.listRepository.deleteById(optionalListEntity.get().getId());
    }

}
