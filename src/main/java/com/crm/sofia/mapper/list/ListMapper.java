package com.crm.sofia.mapper.list;

import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.list.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ListComponentMapper.class, ListComponentFieldMapper.class})
public abstract class ListMapper extends BaseMapper<ListDTO, ListEntity> {
}
