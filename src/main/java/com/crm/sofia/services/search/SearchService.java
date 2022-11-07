package com.crm.sofia.services.search;


import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.mapper.search.SearchMapper;
import com.crm.sofia.model.search.Search;
import com.crm.sofia.repository.search.SearchRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
public class SearchService {

    private final SearchRepository searchRepository;
    private final SearchMapper searchMapper;
    private final JWTService jwtService;
    private final EntityManager entityManager;

    public SearchService(SearchRepository searchRepository,
                         SearchMapper searchMapper,
                         JWTService jwtService,
                         EntityManager entityManager) {
        this.searchRepository = searchRepository;
        this.searchMapper = searchMapper;
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    public SearchDTO getObject(String id) {
        Optional<Search> optionalSearch = this.searchRepository.findById(id);
        if (!optionalSearch.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Search Entry does not exist");
        }
        return this.searchMapper.map(optionalSearch.get());
    }

    public Object getData(String id, String search) {
        SearchDTO dto = this.getObject(id);
        String queryString = dto.getQuery();

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("userid", this.jwtService.getUserId().toString());
        query.setParameter("search", search.replace("*", "%"));

        //  return query.getResultList();
        List<Object[]> fields = query.getResultList();

        List<Object> responces = new ArrayList<>();
        for (Object[] field : fields) {
            Map<String, Object> responce = new HashMap<>();
            responce.put("icon", field[0]);
            responce.put("icon_color", field[1]);
            responce.put("title", field[2]);
            responce.put("description", field[3]);
            responce.put("navigation", field[4]);
            responces.add(responce);
        }

        return responces;
    }
}
