package com.crm.sofia.services.component;


import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.dto.appview.AppViewFieldDTO;
import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.table.TableDTO;
import com.crm.sofia.dto.table.TableFieldDTO;
import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.dto.view.ViewFieldDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.repository.component.ComponentRepository;
import com.crm.sofia.services.appview.AppViewService;
import com.crm.sofia.services.table.TableService;
import com.crm.sofia.services.view.ViewService;
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

    private final TableService tableService;
    private final ViewService viewService;
    private final AppViewService appViewService;

    public ComponentService(ComponentMapper menuMapper,
                            ComponentRepository componentRepository,
                            ComponentFieldService componentFieldService,
                            TableService tableService,
                            ViewService viewService,
                            AppViewService appViewService) {
        this.componentMapper = menuMapper;
        this.componentRepository = componentRepository;
        this.tableService = tableService;
        this.viewService = viewService;
        this.appViewService = appViewService;
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

        for (ComponentPersistEntityDTO componentPersistEntityDTO : dto.getComponentPersistEntityList()) {
            String entitytype = componentPersistEntityDTO.getPersistEntity().getEntitytype();
            if (entitytype.equals("Table")) {
                TableDTO tableDto = tableService.getObject(componentPersistEntityDTO.getPersistEntity().getId());
                componentPersistEntityDTO.setPersistEntity(tableDto);

                for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : componentPersistEntityDTO.getComponentPersistEntityFieldList()) {
                    for (TableFieldDTO tableFieldDTO : tableDto.getTableFieldList()) {
                        if (tableFieldDTO.getId().equals(componentPersistEntityFieldDTO.getPersistEntityField().getId())) {
                            componentPersistEntityFieldDTO.setPersistEntityField(tableFieldDTO);
                        }
                    }
                }

            } else if (entitytype.equals("View")) {
                ViewDTO viewDTO = viewService.getObject(componentPersistEntityDTO.getPersistEntity().getId());
                componentPersistEntityDTO.setPersistEntity(viewDTO);

                for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : componentPersistEntityDTO.getComponentPersistEntityFieldList()) {
                    for (ViewFieldDTO viewFieldDTO : viewDTO.getViewFieldList()) {
                        if (viewFieldDTO.getId().equals(componentPersistEntityFieldDTO.getPersistEntityField().getId())) {
                            componentPersistEntityFieldDTO.setPersistEntityField(viewFieldDTO);
                        }
                    }
                }

            } else if (entitytype.equals("AppView")) {
                AppViewDTO appViewDTO = appViewService.getObject(componentPersistEntityDTO.getPersistEntity().getId());
                componentPersistEntityDTO.setPersistEntity(appViewDTO);

                for (ComponentPersistEntityFieldDTO componentPersistEntityFieldDTO : componentPersistEntityDTO.getComponentPersistEntityFieldList()) {
                    for (AppViewFieldDTO appViewFieldDTO : appViewDTO.getAppViewFieldList()) {
                        if (appViewFieldDTO.getId().equals(componentPersistEntityFieldDTO.getPersistEntityField().getId())) {
                            componentPersistEntityFieldDTO.setPersistEntityField(appViewFieldDTO);
                        }
                    }
                }

            }

        }

        return dto;
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
