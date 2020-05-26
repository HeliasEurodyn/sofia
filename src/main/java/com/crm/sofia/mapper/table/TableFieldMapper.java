package com.crm.sofia.mapper.table;

import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.table.TableField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,  uses = {TableMapper.class})
public abstract class TableFieldMapper extends BaseMapper<TableFieldDTO, TableField> {

    public void setDtoToEntity(TableFieldDTO dto, TableField entity){
        this.mapUpdateDtoToEntity(dto,entity);
    }

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void mapUpdateDtoToEntity(TableFieldDTO dto, @MappingTarget TableField entity);

}


