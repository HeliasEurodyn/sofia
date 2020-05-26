package com.crm.sofia.mapper.table;

import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.table.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {TableFieldMapper.class})
public abstract class TableMapper extends BaseMapper<TableDTO, Table> {

    public Table mapDTO(TableDTO dto) {
        Table table = this.map(dto);
        table.setVersion(0L);
        table.getTableFieldList().stream().forEach(u -> u.setTable(table));
        table.getTableFieldList().stream().forEach(u -> u.setVersion(0L));
        return table;
    }


    public void setDtoToEntity(TableDTO dto, Table entity){
      this.mapUpdateDtoToEntity(dto,entity);
    }

   // @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void mapUpdateDtoToEntity(TableDTO dto, @MappingTarget Table entity);
}
