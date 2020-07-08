package com.crm.sofia.services.component;


import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.repository.component.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponentService {

    private final ComponentMapper componentMapper;
    private final ComponentRepository componentRepository;

    public ComponentService(ComponentMapper menuMapper,
                            ComponentRepository componentRepository,
                            ComponentFieldService componentFieldService) {
        this.componentMapper = menuMapper;
        this.componentRepository = componentRepository;
    }

    public List<ComponentDTO> getObject() {
        List<Component> entites = this.componentRepository.findAll();
        entites = entites.stream().sorted((o1, o2) -> o1.getCreatedOn().compareTo(o2.getCreatedOn()))
                .collect(Collectors.toList());
        return this.componentMapper.map(entites);
    }

    public ComponentDTO getObject(Long id) {
        Optional<Component> optionalEntity = this.componentRepository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Component entity = optionalEntity.get();
        ComponentDTO dto = this.componentMapper.map(entity);

//        for (ComponentFieldDTO componentFieldDTO : dto.getComponentFieldList()) {
//            List<ComponentFieldDTO> childrenDTOs = this.componentFieldService.getObjectTree(componentFieldDTO.getId());
//            componentFieldDTO.setComponentFieldList(childrenDTOs);
//        }

        return this.componentMapper.map(optionalEntity.get());
    }


    @Transactional
    public ComponentDTO postObject(ComponentDTO dto) {
        Component entity = this.componentMapper.mapWithPersistEntities(dto);

        Component createdEntity = this.componentRepository.save(entity);
        return this.componentMapper.map(createdEntity);
    }


    @Transactional
    public ComponentDTO putObject(ComponentDTO dto) {


        Component entity = this.componentMapper.map(dto);

        Component createdEntity = this.componentRepository.save(entity);
        return this.componentMapper.map(createdEntity);

//        Optional<Component> optionalComponent = this.componentRepository.findById(dto.getId());
//        if (!optionalComponent.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
//        }
////        Component entity = optionalComponent.get();
////        componentMapper.mapDtoToEntity(dto, entity);
////        Component createdEntity = this.componentRepository.save(entity);
////        ComponentDTO createdDto = this.componentMapper.map(createdEntity);
////        return createdDto;
//        return null;
    }

    public void deleteObject(Long id) {
        this.componentRepository.deleteById(id);
    }
}
