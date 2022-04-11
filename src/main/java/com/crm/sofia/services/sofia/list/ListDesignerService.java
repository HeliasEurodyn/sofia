package com.crm.sofia.services.sofia.list;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.list.base.ListComponentFieldDTO;
import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.mapper.sofia.list.designer.ListMapper;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.repository.sofia.list.ListRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ListDesignerService {

    private final ListRepository listRepository;
    private final ListMapper listMapper;
    private final JWTService jwtService;
    private final ListJavascriptService listJavascriptService;

    @Autowired
    CacheManager cacheManager;

    public ListDesignerService(ListRepository listRepository,
                               ListMapper listMapper,
                               JWTService jwtService,
                               ListJavascriptService listJavascriptService) {
        this.listRepository = listRepository;
        this.listMapper = listMapper;
        this.jwtService = jwtService;
        this.listJavascriptService = listJavascriptService;
    }

    @Transactional
    public ListDTO postObject(ListDTO listDTO) throws Exception {
        ListEntity listEntity = this.listMapper.mapListDTO(listDTO);
        listEntity.setCreatedOn(Instant.now());
        listEntity.setModifiedOn(Instant.now());
        listEntity.setCreatedBy(jwtService.getUserId());
        listEntity.setModifiedBy(jwtService.getUserId());
        Long instanceVersion = listEntity.getInstanceVersion();
        if (instanceVersion == null) {
            instanceVersion = 0L;
        } else {
            instanceVersion += 1L;
        }
        listEntity.setInstanceVersion(instanceVersion);

        ListEntity createdListEntity = this.listRepository.save(listEntity);
        ListDTO createdListDTO =  this.listMapper.map(createdListEntity);

        String script = this.listJavascriptService.generateDynamicScript(createdListDTO);
        String scriptMin = this.listJavascriptService.minify(script);
        this.listRepository.updateScripts(createdListDTO.getId(),script, scriptMin);

        return createdListDTO;
    }

    @Transactional
   // @CacheEvict(value = "list_ui_cache", key = "#listDTO.id")
    public ListDTO putObject(ListDTO listDTO) throws Exception {

        ListEntity listEntity = this.listMapper.mapListDTO(listDTO);
        listEntity.setModifiedOn(Instant.now());
        listEntity.setModifiedBy(jwtService.getUserId());
        Long instanceVersion = listEntity.getInstanceVersion();
        if (instanceVersion == null) {
            instanceVersion = 0L;
        } else {
            instanceVersion += 1L;
        }
        listEntity.setInstanceVersion(instanceVersion);

        ListEntity createdListEntity = this.listRepository.save(listEntity);
        ListDTO createdListDTO = this.listMapper.map(createdListEntity);

        String script = this.listJavascriptService.generateDynamicScript(createdListDTO);
        String scriptMin = this.listJavascriptService.minify(script);
        this.listRepository.updateScripts(createdListDTO.getId(),script, scriptMin);

        cacheManager.getCache( "list_ui_cache").evict(createdListEntity.getId());
        cacheManager.getCache( "list_ui_cache").evict(new Object[]{createdListEntity.getId(), 0});
        createdListEntity.getTranslations().forEach( translation -> {
            cacheManager.getCache( "list_ui_cache").evict(new Object[]{createdListEntity.getId(), translation.getLanguage().getId()});
        });

        return createdListDTO;
    }

    public List<ListDTO> getById() {
        List<ListEntity> views = this.listRepository.findAll();
        return this.listMapper.mapEntitiesForList(views);
    }

    public ListDTO getById(Long id) {

        ListEntity listEntity =
                this.listRepository.findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist")
                        );

        ListDTO listDTO = this.listMapper.map(listEntity);
        listDTO.getComponent().getComponentPersistEntityList().sort(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder));
        listDTO.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        listDTO.getListComponentActionFieldList().forEach(af -> {
            if( af.getListComponentActionFieldList() != null) {
                af.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
            }
        });

        return listDTO;
    }

    public ListDTO getObjectByName(String name) {
        ListEntity listEntity = this.listRepository.findFirstByName(name);
        if (listEntity == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ListEntity does not exist");
        }
        return this.listMapper.map(listEntity);
    }

//    @CacheEvict(value = "list_ui_cache", key = "#id")
    public void deleteObject(Long id) {
        ListEntity lienEntity = this.listRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "List does not exist"));

        cacheManager.getCache( "list_ui_cache").evict(lienEntity.getId());
        cacheManager.getCache( "list_ui_cache").evict(new Object[]{lienEntity.getId(), 0});
        lienEntity.getTranslations().forEach( translation -> {
            cacheManager.getCache( "list_ui_cache").evict(new Object[]{lienEntity.getId(), translation.getId()});
        });

        this.listRepository.deleteById(lienEntity.getId());
    }

    public boolean clearCacheForUi() {
        this.listRepository.increaseInstanceVersions();
        return true;
    }
}
