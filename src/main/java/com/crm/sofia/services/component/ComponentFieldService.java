package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.ComponentFieldDTO;
import com.crm.sofia.mapper.component.ComponentFieldMapper;
import com.crm.sofia.model.component.ComponentField;
import com.crm.sofia.repository.component.ComponentFieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ComponentFieldService {

    private final ComponentFieldRepository componentFieldRepository;
    private final ComponentFieldMapper componentFieldMapper;

    public ComponentFieldService(ComponentFieldRepository componentFieldRepository, ComponentFieldMapper componentFieldMapper) {
        this.componentFieldRepository = componentFieldRepository;
        this.componentFieldMapper = componentFieldMapper;
    }

    public List<ComponentFieldDTO> getObjectTree(Long id) {
        Optional<ComponentField> optionalEntity = this.componentFieldRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            return null;
        }
        ComponentField entity = optionalEntity.get();
        List<ComponentFieldDTO> dtos = this.componentFieldMapper.map(entity.getComponentFieldList());

        if (dtos == null) return null;

        for (ComponentFieldDTO dto : dtos) {
            List<ComponentFieldDTO> childrenEntities = this.getObjectTree(dto.getId());
            dto.setComponentFieldList(childrenEntities);
        }

        return dtos;
    }

}
