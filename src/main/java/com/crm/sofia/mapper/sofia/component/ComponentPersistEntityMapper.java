package com.crm.sofia.mapper.sofia.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.component.ComponentPersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ComponentPersistEntityMapper extends BaseMapper<ComponentPersistEntityDTO, ComponentPersistEntity> {
}
