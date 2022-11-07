package com.crm.sofia.mapper.search;

import com.crm.sofia.dto.search.SearchDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.search.Search;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class SearchMapper extends BaseMapper<SearchDTO, Search> {
}
