package com.crm.sofia.mapper.form.designer;

import com.crm.sofia.dto.sofia.form.base.FormDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class FormMapper extends BaseMapper<FormDTO, FormEntity> {

    public FormDTO mapForm(FormEntity entity) {
        FormDTO dto = this.map(entity);
        dto.getFormScripts().forEach(formScriptDTO -> formScriptDTO.setScript(""));
        return dto;
    }

    public List<FormDTO> mapEntitiesForList(List<FormEntity> entities) {
        return entities.stream().map(this::mapEntityForList).collect(Collectors.toList());
    }

    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    @Mapping(ignore = true, target = "shortOrder")
    @Mapping(ignore = true, target = "title")
    @Mapping(ignore = true, target = "description")
    @Mapping(ignore = true, target = "icon")
    @Mapping(ignore = true, target = "formTabs")
    @Mapping(ignore = true, target = "formPopups")
    @Mapping(ignore = true, target = "formScripts")
    @Mapping(ignore = true, target = "formCssList")
    @Mapping(ignore = true, target = "instanceVersion")
    @Mapping(ignore = true, target = "formActionButtons")
    @Mapping(ignore = true, target = "component.componentPersistEntityList")
    public abstract FormDTO mapEntityForList(FormEntity entity);

}
