package com.crm.sofia.mapper.sofia.appview;

import com.crm.sofia.dto.sofia.appview.AppViewDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class AppViewMapper extends BaseMapper<AppViewDTO, PersistEntity> {

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(target="persistEntityFieldList", source="dto.appViewFieldList")
    public abstract PersistEntity map(AppViewDTO dto);

    @Mapping(target = "appViewFieldList", source = "entity.persistEntityFieldList")
    public abstract AppViewDTO map(PersistEntity entity);

    public List<AppViewDTO> map(List<PersistEntity> all) {
        return all.stream().map(this::map).collect(Collectors.toList());
    }

}
