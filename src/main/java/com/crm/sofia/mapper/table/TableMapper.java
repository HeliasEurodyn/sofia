package com.crm.sofia.mapper.table;

import com.crm.sofia.dto.sofia.table.TableDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TableMapper extends BaseMapper<TableDTO, PersistEntity> {

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(target = "persistEntityFieldList", source = "dto.tableFieldList")
    public abstract PersistEntity map(TableDTO dto);

    @Mapping(target = "tableFieldList", source = "entity.persistEntityFieldList")
    public abstract TableDTO map(PersistEntity entity);

    public List<TableDTO> map(List<PersistEntity> all) {
        return all.stream().map(this::map).collect(Collectors.toList());
    }
}
