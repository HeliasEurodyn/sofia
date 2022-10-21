package com.crm.sofia.mapper.search;

import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.search.Search;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class SearchMapper extends BaseMapper<SearchDTO, Search> {
}
