package com.crm.sofia.mapper.sofia.menu;

import com.crm.sofia.dto.sofia.menu.MenuDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.menu.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MenuFieldMapper.class})
public abstract class MenuMapper extends BaseMapper<MenuDTO, Menu>  {


    public Menu mapDTO(MenuDTO dto) {
        Menu entity = this.map(dto);
        return entity;
    }

    public void mapDtoToEntity(MenuDTO dto, @MappingTarget Menu entity){
        this.dtoToEntity(dto,entity);
    }

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void dtoToEntity(MenuDTO dto, @MappingTarget Menu entity);

}
