package com.crm.sofia.mapper.list;

import com.crm.sofia.dto.list.ListComponentDTO;
import com.crm.sofia.dto.list.ListDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.list.ListComponent;
import com.crm.sofia.model.list.ListComponentField;
import com.crm.sofia.model.list.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ListMapper.class, ListComponentFieldMapper.class})
public abstract class ListComponentMapper extends BaseMapper<ListComponentDTO, ListComponent> {
}
