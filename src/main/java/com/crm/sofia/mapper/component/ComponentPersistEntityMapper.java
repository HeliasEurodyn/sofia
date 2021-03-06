package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.ComponentPersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ComponentPersistEntityMapper extends BaseMapper<ComponentPersistEntityDTO, ComponentPersistEntity> {
}
