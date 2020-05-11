package com.crm.sofia.services.menu;

import com.crm.sofia.dto.menu.MenuComponentDTO;
import com.crm.sofia.dto.menu.MenuItemComponentDTO;
import com.crm.sofia.mapper.menu.MenuComponentMapper;
import com.crm.sofia.model.menu.MenuComponent;
import com.crm.sofia.repository.menu.MenuComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuComponentService {

    private final MenuComponentRepository menuComponentRepository;
    private final MenuComponentMapper menuComponentMapper;
    private final MenuItemComponentService menuItemComponentService;

    public MenuComponentService(MenuComponentRepository menuComponentRepository,
                                MenuComponentMapper menuComponentMapper, MenuItemComponentService menuItemComponentService) {

        this.menuComponentRepository = menuComponentRepository;
        this.menuComponentMapper = menuComponentMapper;
        this.menuItemComponentService = menuItemComponentService;
    }

    public List<MenuComponentDTO> getObject() {
        List<MenuComponent> entites = this.menuComponentRepository.findAll();
        entites = entites.stream().sorted((o1, o2) -> o1.getCreatedOn().compareTo(o2.getCreatedOn()))
                .collect(Collectors.toList());
        return this.menuComponentMapper.map(entites);
    }

    public MenuComponentDTO getObject(Long id) {
        Optional<MenuComponent> optionalEntity = this.menuComponentRepository.findTreeById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        MenuComponent entity = optionalEntity.get();
        MenuComponentDTO dto = this.menuComponentMapper.map(entity);

        for (MenuItemComponentDTO menuFieldDTO : dto.getMenuFieldList()) {
            List<MenuItemComponentDTO> childrenDTOs = this.menuItemComponentService.getObjectTree(menuFieldDTO.getId());
            menuFieldDTO.setMenuFieldList(childrenDTOs);
        }

        return dto;
    }

    public void deleteObject(Long id) {
        Optional<MenuComponent> optionalEntity = this.menuComponentRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        this.menuComponentRepository.deleteById(optionalEntity.get().getId());
    }

    @Transactional
    public MenuComponentDTO postObject(MenuComponentDTO menuComponentDTO) {
        MenuComponent component = this.menuComponentMapper.mapDTO(menuComponentDTO);

        MenuComponent createdComponent = this.menuComponentRepository.save(component);
        return this.menuComponentMapper.map(createdComponent);
    }

    @Transactional
    public MenuComponentDTO putObject(MenuComponentDTO componentDTO) {

        Optional<MenuComponent> optionalComponent = this.menuComponentRepository.findById(componentDTO.getId());
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        MenuComponent entity = optionalComponent.get();

        menuComponentMapper.mapUpdateDtoToEntity(componentDTO, entity);
        MenuComponent createdEntity = this.menuComponentRepository.save(entity);
        MenuComponentDTO createdDto = this.menuComponentMapper.map(createdEntity);

        return createdDto;
    }

    @Transactional
    public List<MenuItemComponentDTO> putNewObjectFields(MenuComponentDTO dto) {

        List<MenuItemComponentDTO> createdMenuItemConponentDTOs = new ArrayList<>();
        for (MenuItemComponentDTO menuItemConponentDTO : dto.getMenuFieldList()) {
            MenuItemComponentDTO createdMenuItemConponentDTO = this.menuItemComponentService.save(menuItemConponentDTO, dto.getId());
            createdMenuItemConponentDTOs.add(createdMenuItemConponentDTO);
        }

        List<Long> ids = createdMenuItemConponentDTOs.stream().map(MenuItemComponentDTO::getId).collect(Collectors.toList());
        this.menuItemComponentService.deleteNotInListForParent(ids, dto.getId());

        return createdMenuItemConponentDTOs;
    }


//    @PostMapping
//    public MenuComponentDTO postObject(@RequestBody MenuComponentDTO componentDTO) {
//        MenuComponentDTO customComponentDTO = this.menuComponentService.postObject(componentDTO);
//        return customComponentDTO;
//    }

//    @PutMapping
//    public MenuComponentDTO putObject(@RequestBody MenuComponentDTO dto) {
//
//        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(componentDTO.getId());
//        if (!optionalComponent.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
//        }
//
//        MenuComponentDTO customComponentDTO = this.menuComponentService.putObject(dto);
//        List<CustomComponentFieldDTO> fields = this.menuComponentService.putNewObjectFields(dto);
//        customComponentDTO.setCustomComponentFieldList(fields);
//        return customComponentDTO;
//    }


}
