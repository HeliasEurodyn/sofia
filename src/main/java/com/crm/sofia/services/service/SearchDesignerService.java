package com.crm.sofia.services.service;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.form.FormAreaDTO;
import com.crm.sofia.dto.form.FormControlDTO;
import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.dto.form.FormTabDTO;
import com.crm.sofia.dto.info_card.InfoCardTextResponceDTO;
import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.mapper.form.FormMapper;
import com.crm.sofia.mapper.search.SearchMapper;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.model.info_card.InfoCard;
import com.crm.sofia.model.search.Search;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.repository.search.SearchRepository;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentRetrieverService;
import com.crm.sofia.services.component.crud.ComponentSaverService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class SearchDesignerService {

    private final SearchRepository searchRepository;
    private final SearchMapper searchMapper;

    public SearchDesignerService(SearchRepository searchRepository,
                                 SearchMapper searchMapper) {
        this.searchRepository = searchRepository;
        this.searchMapper = searchMapper;
    }


    public List<SearchDTO> getObject() {
        List<Search> seaches = this.searchRepository.findAll();
        return this.searchMapper.map(seaches);
    }

    public SearchDTO getObject(Long id) {
        Optional<Search> optionalSearch = this.searchRepository.findById(id);
        if (!optionalSearch.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Search does not exist");
        }
        return this.searchMapper.map(optionalSearch.get());
    }

    @Transactional
    @Modifying
    public SearchDTO postObject(SearchDTO dto) {
        Search search = this.searchMapper.map(dto);
        Search createdSearch = this.searchRepository.save(search);
        return this.searchMapper.map(createdSearch);
    }

    public void deleteObject(Long id) {
        Optional<Search> optionalSearch = this.searchRepository.findById(id);
        if (!optionalSearch.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Search does not exist");
        }
        this.searchRepository.deleteById(optionalSearch.get().getId());
    }

    public InfoCardTextResponceDTO getData(String sql) {
        //return this.searchRepository.getData(sql);
        return null;
    }
}
