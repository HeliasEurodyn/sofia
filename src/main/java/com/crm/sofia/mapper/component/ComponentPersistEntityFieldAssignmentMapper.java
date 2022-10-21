package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldAssignmentDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityFieldAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ComponentPersistEntityFieldAssignmentMapper
        extends BaseMapper<ComponentPersistEntityFieldAssignmentDTO, ComponentPersistEntityFieldAssignment> {
}
