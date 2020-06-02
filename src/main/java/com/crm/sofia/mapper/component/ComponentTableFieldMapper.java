package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.ComponentTableFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.ComponentTableField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)//, uses = {ComponentMapper.class})
public abstract class ComponentTableFieldMapper extends BaseMapper<ComponentTableFieldDTO, ComponentTableField> {

    public abstract List<ComponentTableField> mapDTOs(List<ComponentTableFieldDTO> dtos );
    public abstract List<ComponentTableFieldDTO> map(List<ComponentTableField> entities );
    public abstract ComponentTableFieldDTO map(ComponentTableField entities );

}
