package com.crm.sofia.mapper.sofia.list;

import com.crm.sofia.dto.sofia.list.ListComponentFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.list.ListComponentField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ListMapper.class, ListComponentMapper.class})
public abstract class ListComponentFieldMapper extends BaseMapper<ListComponentFieldDTO, ListComponentField> {
}
