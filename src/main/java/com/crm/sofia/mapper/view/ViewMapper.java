package com.crm.sofia.mapper.view;

import com.crm.sofia.dto.sofia.view.ViewDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ViewMapper extends BaseMapper<ViewDTO, PersistEntity> {

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(target="persistEntityFieldList", source="dto.viewFieldList")
    public abstract PersistEntity map(ViewDTO dto);

    @Mapping(target = "viewFieldList", source = "entity.persistEntityFieldList")
    public abstract ViewDTO map(PersistEntity entity);

    public List<ViewDTO> map(List<PersistEntity> all) {
        return all.stream().map(this::map).collect(Collectors.toList());
    }

}
