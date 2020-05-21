package com.crm.sofia.mapper.menu;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.dto.menu.MenuComponentDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.mapper.component.CustomComponentFieldMapper;
import com.crm.sofia.model.component.CustomComponent;
import com.crm.sofia.model.menu.MenuComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MenuItemComponentMapper.class})

public abstract class MenuComponentMapper extends BaseMapper<MenuComponentDTO, MenuComponent>  {


    public MenuComponent mapDTO(MenuComponentDTO dto) {
        MenuComponent entity = this.map(dto);
    //    entity.setVersion(0L);
        entity.getMenuFieldList().stream().forEach(u -> u.setMenuComponent(entity));
  //      entity.getMenuFieldList().stream().forEach(u -> u.setVersion(0L));
        return entity;
    }


    public void mapDtoToEntity(MenuComponentDTO dto, @MappingTarget MenuComponent entity){
        this.dtoToEntity(dto,entity);
        entity.getMenuFieldList().stream().forEach(u -> u.setMenuComponent(entity));
    }



    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
  //  @Mapping(ignore = true, target = "menuFieldList")
    public abstract void dtoToEntity(MenuComponentDTO dto, @MappingTarget MenuComponent entity);

}
