package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.component.ComponentPersistEntityField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)//, uses = {ComponentMapper.class})
public abstract class ComponentPersistEntityFieldMapper extends BaseMapper<ComponentPersistEntityFieldDTO, ComponentPersistEntityField> {

    public abstract List<ComponentPersistEntityField> mapDTOs(List<ComponentPersistEntityFieldDTO> dtos );
    public abstract List<ComponentPersistEntityFieldDTO> map(List<ComponentPersistEntityField> entities );
    public abstract ComponentPersistEntityFieldDTO map(ComponentPersistEntityField entities );

}
