package com.crm.sofia.services.component;

import org.springframework.stereotype.Service;


@Service
public class ComponentFieldService {

//    private final ComponentFieldRepository componentFieldRepository;
//    private final ComponentFieldMapper componentFieldMapper;
//
//    public ComponentFieldService(ComponentFieldRepository componentFieldRepository, ComponentFieldMapper componentFieldMapper) {
//        this.componentFieldRepository = componentFieldRepository;
//        this.componentFieldMapper = componentFieldMapper;
//    }

//    public List<ComponentTableFieldDTO> getObjectTree(Long id) {
//        Optional<ComponentTableField> optionalEntity = this.componentFieldRepository.findById(id);
//        if (!optionalEntity.isPresent()) {
//            return null;
//        }
//        ComponentTableField entity = optionalEntity.get();
//        List<ComponentTableFieldDTO> dtos = this.componentFieldMapper.map(entity.getComponentTableFieldList());
//
//        if (dtos == null) return null;
//
//        for (ComponentTableFieldDTO dto : dtos) {
//            List<ComponentTableFieldDTO> childrenEntities = this.getObjectTree(dto.getId());
//            dto.setComponentFieldList(childrenEntities);
//        }
//
//        return dtos;
//    }

}
