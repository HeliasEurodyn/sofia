package com.crm.sofia.repository.search;

import com.crm.sofia.model.search.Search;
import com.crm.sofia.repository.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface SearchRepository extends BaseRepository<Search> {

    List<Search> findAll();

}
