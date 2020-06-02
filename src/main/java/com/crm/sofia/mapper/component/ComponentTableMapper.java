package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.ComponentTableDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.ComponentTable;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)//, uses = {ComponentMapper.class})
public abstract class ComponentTableMapper extends BaseMapper<ComponentTableDTO, ComponentTable> {
}
