package com.crm.sofia.mapper.sofia.list;

import com.crm.sofia.dto.sofia.list.base.ListComponentDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.list.ListComponent;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ListMapper.class, ListComponentFieldMapper.class})
public abstract class ListComponentMapper extends BaseMapper<ListComponentDTO, ListComponent> {
}
