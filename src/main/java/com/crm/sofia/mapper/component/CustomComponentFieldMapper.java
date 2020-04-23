package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.dto.component.CustomComponentFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.CustomComponent;
import com.crm.sofia.model.component.CustomComponentField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,  uses = {CustomComponentMapper.class})
public abstract class CustomComponentFieldMapper extends BaseMapper<CustomComponentFieldDTO, CustomComponentField> {

    public void setDtoToEntity(CustomComponentFieldDTO dto, CustomComponentField entity){
        this.mapUpdateDtoToEntity(dto,entity);
    }

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void mapUpdateDtoToEntity(CustomComponentFieldDTO dto, @MappingTarget CustomComponentField entity);

}


