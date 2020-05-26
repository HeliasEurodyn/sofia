package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.ComponentFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.ComponentField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ComponentMapper.class})
public abstract class ComponentFieldMapper extends BaseMapper<ComponentFieldDTO, ComponentField> {

    public abstract List<ComponentField> mapDTOs(  List<ComponentFieldDTO> dtos );
    public abstract List<ComponentFieldDTO> map( List<ComponentField> entities );
    public abstract ComponentFieldDTO map( ComponentField entities );

}
