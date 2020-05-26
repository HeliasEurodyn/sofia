package com.crm.sofia.services.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.repository.menu.MenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;
    private final MenuFieldService menuFieldService;

    public MenuService(MenuRepository menuRepository,
                       MenuMapper menuMapper, MenuFieldService menuFieldService) {

        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.menuFieldService = menuFieldService;
    }

    public List<MenuDTO> getObject() {
        List<Menu> entites = this.menuRepository.findAll();
        entites = entites.stream().sorted((o1, o2) -> o1.getCreatedOn().compareTo(o2.getCreatedOn()))
                .collect(Collectors.toList());
        return this.menuMapper.map(entites);
    }

    public MenuDTO getObject(Long id) {
        Optional<Menu> optionalEntity = this.menuRepository.findTreeById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Menu entity = optionalEntity.get();
        MenuDTO dto = this.menuMapper.map(entity);

        for (MenuFieldDTO menuFieldDTO : dto.getMenuFieldList()) {
            List<MenuFieldDTO> childrenDTOs = this.menuFieldService.getObjectTree(menuFieldDTO.getId());
            menuFieldDTO.setMenuFieldList(childrenDTOs);
        }

        return dto;
    }

    public void deleteObject(Long id) {
        Optional<Menu> optionalEntity = this.menuRepository.findById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        this.menuRepository.deleteById(optionalEntity.get().getId());
    }

    @Transactional
    public MenuDTO postObject(MenuDTO menuDTO) {
        Menu component = this.menuMapper.mapDTO(menuDTO);

        Menu createdComponent = this.menuRepository.save(component);
        return this.menuMapper.map(createdComponent);
    }

    @Transactional
    public MenuDTO putObject(MenuDTO componentDTO) {

        Optional<Menu> optionalComponent = this.menuRepository.findById(componentDTO.getId());
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Menu entity = optionalComponent.get();

        menuMapper.mapDtoToEntity(componentDTO, entity);

        Menu createdEntity = this.menuRepository.save(entity);
        MenuDTO createdDto = this.menuMapper.map(createdEntity);

        return createdDto;
    }

    @Transactional
    public List<MenuFieldDTO> putNewObjectFields(MenuDTO dto) {

        List<MenuFieldDTO> createdMenuItemConponentDTOs = new ArrayList<>();
        for (MenuFieldDTO menuItemConponentDTO : dto.getMenuFieldList()) {
            MenuFieldDTO createdMenuItemConponentDTO = this.menuFieldService.save(menuItemConponentDTO, dto.getId());
            createdMenuItemConponentDTOs.add(createdMenuItemConponentDTO);
        }

        List<Long> ids = createdMenuItemConponentDTOs.stream().map(MenuFieldDTO::getId).collect(Collectors.toList());
        this.menuFieldService.deleteNotInListForParent(ids, dto.getId());

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
