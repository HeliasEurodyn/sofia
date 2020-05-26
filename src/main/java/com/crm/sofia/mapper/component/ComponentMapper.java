package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.mapper.menu.MenuFieldMapper;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.table.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ComponentFieldMapper.class})
public abstract class ComponentMapper extends BaseMapper<ComponentDTO, com.crm.sofia.model.component.Component> {


//    @Mapping(ignore = true, target = "modifiedBy")
//    @Mapping(ignore = true, target = "modifiedOn")
//    @Mapping(ignore = true, target = "createdBy")
//    public abstract void mapDtoToEntity(ComponentDTO dto, @MappingTarget Component entity);

}
