package com.crm.sofia.services.menu;

import com.crm.sofia.dto.menu.MenuItemComponentDTO;
import com.crm.sofia.mapper.menu.MenuItemComponentMapper;
import com.crm.sofia.model.menu.MenuComponent;
import com.crm.sofia.model.menu.MenuItemComponent;
import com.crm.sofia.repository.menu.MenuComponentRepository;
import com.crm.sofia.repository.menu.MenuItemComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemComponentService {

    private final MenuComponentRepository menuComponentRepository;
    private final MenuItemComponentRepository menuItemComponentRepository;
    private final MenuItemComponentMapper menuItemComponentMapper;

    public MenuItemComponentService(MenuComponentRepository menuComponentRepository,
                                    MenuItemComponentRepository menuItemComponentRepository,
                                    MenuItemComponentMapper menuItemComponentMapper) {
        this.menuComponentRepository = menuComponentRepository;
        this.menuItemComponentRepository = menuItemComponentRepository;
        this.menuItemComponentMapper = menuItemComponentMapper;
    }


    public MenuItemComponentDTO save(MenuItemComponentDTO menuItemConponentDTO, Long parentId) {
        Optional<MenuComponent> optionalMenuComponent = this.menuComponentRepository.findById(parentId);
        if (!optionalMenuComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        MenuComponent menuComponent = optionalMenuComponent.get();

        MenuItemComponent menuItemComponent;
        Long id = 0L;
        if (menuItemConponentDTO.getId() != null) id = menuItemConponentDTO.getId();
        Optional<MenuItemComponent> optionalMenuItemComponent = this.menuItemComponentRepository.findById(id);

        if (optionalMenuItemComponent.isPresent()) {
            menuItemComponent = optionalMenuItemComponent.get();
            menuItemComponentMapper.mapUpdateDtoToEntity(menuItemConponentDTO, menuItemComponent);

        } else {
            menuItemComponent = menuItemComponentMapper.map(menuItemConponentDTO);
            menuItemComponent.setId(null);
        }
        menuItemComponent.setMenuComponent(menuComponent);
        return menuItemComponentMapper.map(this.menuItemComponentRepository.save(menuItemComponent));
    }

    public void deleteNotInListForParent(List<Long> ids, Long id) {
        menuItemComponentRepository.deleteObjectsNotInListForParentId(ids, id);

    }

    public List<MenuItemComponentDTO> getObjectTree(Long id) {
        Optional<MenuItemComponent> optionalMenuItemComponent = this.menuItemComponentRepository.findById(id);
        if (!optionalMenuItemComponent.isPresent()) {
            return null;
        }
        MenuItemComponent menuItemComponent = optionalMenuItemComponent.get();
        List<MenuItemComponentDTO> menuItemComponentDTOS = menuItemComponentMapper.map(menuItemComponent.getMenuFieldList());

        if (menuItemComponentDTOS == null) return null;

        for (MenuItemComponentDTO entityDTO : menuItemComponentDTOS) {
            List<MenuItemComponentDTO> childrenEntities = this.getObjectTree(entityDTO.getId());
            entityDTO.setMenuFieldList(childrenEntities);
        }

        return menuItemComponentDTOS;
    }

}
