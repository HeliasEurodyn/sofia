package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.CustomComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {CustomComponentFieldMapper.class})
public abstract class CustomComponentMapper extends BaseMapper<CustomComponentDTO, CustomComponent> {

    public CustomComponent mapDTO(CustomComponentDTO dto) {
        CustomComponent customComponent = this.map(dto);
        customComponent.setVersion(0L);
        customComponent.getCustomComponentFieldList().stream().forEach(u -> u.setCustomComponent(customComponent));
        customComponent.getCustomComponentFieldList().stream().forEach(u -> u.setVersion(0L));
        return customComponent;
    }


    public void setDtoToEntity(CustomComponentDTO dto,  CustomComponent entity){
      this.mapUpdateDtoToEntity(dto,entity);
    }

   // @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    public abstract void mapUpdateDtoToEntity(CustomComponentDTO dto, @MappingTarget CustomComponent entity);
}
