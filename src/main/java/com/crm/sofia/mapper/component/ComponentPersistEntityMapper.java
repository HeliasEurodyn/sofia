package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.ComponentPersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ComponentPersistEntityMapper extends BaseMapper<ComponentPersistEntityDTO, ComponentPersistEntity> {

    public ComponentPersistEntityDTO mapCpe(ComponentPersistEntity componentPersistEntity) {

        /* Map */
        ComponentPersistEntityDTO componentPersistEntityDTO = this.map(componentPersistEntity);


        /* Short Component Persist Entity Fields */
        componentPersistEntityDTO.getComponentPersistEntityFieldList()
                .stream()
                .filter(cpef -> cpef.getShortOrder() == null)
                .forEach(cpef -> cpef.setShortOrder(0L));

        List<ComponentPersistEntityFieldDTO> sortedCpefList =
                componentPersistEntityDTO.getComponentPersistEntityFieldList()
                        .stream()
                        .sorted(Comparator.comparingLong(ComponentPersistEntityFieldDTO::getShortOrder)).collect(Collectors.toList());
        componentPersistEntityDTO.setComponentPersistEntityFieldList(sortedCpefList);

        /* Return */
        return componentPersistEntityDTO;
    }
}
