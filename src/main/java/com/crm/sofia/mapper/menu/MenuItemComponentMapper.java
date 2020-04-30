package com.crm.sofia.mapper.menu;

import com.crm.sofia.dto.component.CustomComponentFieldDTO;
import com.crm.sofia.dto.menu.MenuItemComponentDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.mapper.component.CustomComponentFieldMapper;
import com.crm.sofia.model.component.CustomComponentField;
import com.crm.sofia.model.menu.MenuItemComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MenuComponentMapper.class})
public abstract class MenuItemComponentMapper extends BaseMapper<MenuItemComponentDTO, MenuItemComponent> {


    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void mapUpdateDtoToEntity(MenuItemComponentDTO dto, @MappingTarget MenuItemComponent entity);

}


