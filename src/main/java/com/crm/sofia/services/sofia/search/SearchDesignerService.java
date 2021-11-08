package com.crm.sofia.services.sofia.search;


import com.crm.sofia.dto.sofia.search.SearchDTO;
import com.crm.sofia.mapper.sofia.search.SearchMapper;
import com.crm.sofia.model.sofia.search.Search;
import com.crm.sofia.repository.sofia.search.SearchRepository;
import com.crm.sofia.services.sofia.auth.JWTService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class SearchDesignerService {
    
    private final SearchRepository searchRepository;
    private final SearchMapper searchMapper;
    private final JWTService jwtService;
    private final EntityManager entityManager;

    public SearchDesignerService(SearchRepository searchRepository,
                                 SearchMapper searchMapper,
                                 JWTService jwtService,
                                 EntityManager entityManager) {
        this.searchRepository = searchRepository;
        this.searchMapper = searchMapper;
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    public List<SearchDTO> getObject() {
        List<Search> searcies = this.searchRepository.findAll();
        return this.searchMapper.map(searcies);
    }

    public SearchDTO getObject(Long id) {
        Optional<Search> optionalSearch = this.searchRepository.findById(id);
        if (!optionalSearch.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Info card does not exist");
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

}
