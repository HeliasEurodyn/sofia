package com.crm.sofia.services.menu;

import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.mapper.menu.MenuFieldMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.menu.MenuField;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.repository.menu.MenuFieldRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MenuFieldService {

    private final MenuRepository menuRepository;
    private final MenuFieldRepository menuFieldRepository;
    private final MenuFieldMapper menuFIeldMapper;

    public MenuFieldService(MenuRepository menuRepository,
                            MenuFieldRepository menuFieldRepository,
                            MenuFieldMapper menuFIeldMapper) {
        this.menuRepository = menuRepository;
        this.menuFieldRepository = menuFieldRepository;
        this.menuFIeldMapper = menuFIeldMapper;
    }


//    public MenuFieldDTO save(MenuFieldDTO menuItemConponentDTO, Long parentId) {
//        Optional<Menu> optionalMenuComponent = this.menuRepository.findById(parentId);
//        if (!optionalMenuComponent.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
//        }
//        Menu menu = optionalMenuComponent.get();
//
//        MenuField menuField;
//        Long id = 0L;
//        if (menuItemConponentDTO.getId() != null) id = menuItemConponentDTO.getId();
//        Optional<MenuField> optionalMenuItemComponent = this.menuFieldRepository.findById(id);
//
//        if (optionalMenuItemComponent.isPresent()) {
//            menuField = optionalMenuItemComponent.get();
//            menuFIeldMapper.mapUpdateDtoToEntity(menuItemConponentDTO, menuField);
//
//        } else {
//            menuField = menuFIeldMapper.map(menuItemConponentDTO);
//            menuField.setId(null);
//        }
//        menuField.setMenu(menu);
//        return menuFIeldMapper.map(this.menuFieldRepository.save(menuField));
//    }

//    public void deleteNotInListForParent(List<Long> ids, Long id) {
//        menuFieldRepository.deleteObjectsNotInListForParentId(ids, id);
//    }

    public List<MenuFieldDTO> getObjectTree(Long id) {
        Optional<MenuField> optionalMenuItemComponent = this.menuFieldRepository.findById(id);
        if (!optionalMenuItemComponent.isPresent()) {
            return null;
        }
        MenuField menuField = optionalMenuItemComponent.get();
        List<MenuFieldDTO> menuFieldDTOS = menuFIeldMapper.map(menuField.getMenuFieldList());

        if (menuFieldDTOS == null) return null;

        for (MenuFieldDTO entityDTO : menuFieldDTOS) {
            List<MenuFieldDTO> childrenEntities = this.getObjectTree(entityDTO.getId());
            entityDTO.setMenuFieldList(childrenEntities);
        }

        return menuFieldDTOS;
    }

}
