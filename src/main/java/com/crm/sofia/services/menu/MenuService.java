package com.crm.sofia.services.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper;
    private final MenuFieldService menuFieldService;
    private final JWTService jwtService;

    public MenuService(MenuRepository menuRepository,
                       MenuMapper menuMapper,
                       MenuFieldService menuFieldService,
                       JWTService jwtService) {

        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.menuFieldService = menuFieldService;
        this.jwtService = jwtService;
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

        List<MenuFieldDTO> menuFieldList =
                dto.getMenuFieldList()
                        .stream()
                        .sorted(Comparator.comparingLong(MenuFieldDTO::getShortOrder))
                        .collect(Collectors.toList());

        menuFieldList.forEach( x -> {
            List<MenuFieldDTO> childrenDTOs = this.menuFieldService.getObjectTree( x.getId());
            x.setMenuFieldList(childrenDTOs);
        });

        dto.setMenuFieldList(menuFieldList);

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
        component.setCreatedOn(Instant.now());
        component.setModifiedOn(Instant.now());
        component.setCreatedBy(jwtService.getUserId());
        component.setModifiedBy(jwtService.getUserId());

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

        entity.setModifiedOn(Instant.now());
        entity.setModifiedBy(jwtService.getUserId());

        Menu createdEntity = this.menuRepository.save(entity);
        MenuDTO createdDto = this.menuMapper.map(createdEntity);

        return createdDto;
    }

}
