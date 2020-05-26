package com.crm.sofia.mapper.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.menu.MenuField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MenuFieldMapper.class})

public abstract class MenuMapper extends BaseMapper<MenuDTO, Menu>  {


    public Menu mapDTO(MenuDTO dto) {
        Menu entity = this.map(dto);
        entity.getMenuFieldList().stream().forEach(u -> u.setMenu(entity));
        return entity;
    }

    public void mapDtoToEntity(MenuDTO dto, @MappingTarget Menu entity){
        this.dtoToEntity(dto,entity);
        entity.getMenuFieldList().stream().forEach(u -> u.setMenu(entity));
    }



    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
  //  @Mapping(ignore = true, target = "menuFieldList")
    public abstract void dtoToEntity(MenuDTO dto, @MappingTarget Menu entity);

}
