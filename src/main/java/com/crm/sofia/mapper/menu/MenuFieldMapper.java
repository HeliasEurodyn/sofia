package com.crm.sofia.mapper.menu;

import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.menu.MenuField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MenuMapper.class})
public abstract class MenuFieldMapper extends BaseMapper<MenuFieldDTO, MenuField> {

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void mapUpdateDtoToEntity(MenuFieldDTO dto, @MappingTarget MenuField entity);

}


