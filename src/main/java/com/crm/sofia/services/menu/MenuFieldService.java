package com.crm.sofia.services.menu;

import com.crm.sofia.dto.sofia.menu.MenuFieldDTO;
import com.crm.sofia.mapper.menu.MenuFieldMapper;
import com.crm.sofia.model.sofia.menu.MenuField;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.repository.menu.MenuFieldRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<MenuFieldDTO> getObjectTree(String id) {
        Optional<MenuField> optionalMenuItemComponent = this.menuFieldRepository.findById(id);
        if (!optionalMenuItemComponent.isPresent()) {
            return null;
        }
        MenuField menuField = optionalMenuItemComponent.get();
        List<MenuFieldDTO> menuFieldDTOS = menuFIeldMapper.map(menuField.getMenuFieldList());

        if (menuFieldDTOS == null) return null;

        menuFieldDTOS = menuFieldDTOS.stream()
                .sorted(Comparator.comparingLong(MenuFieldDTO::getShortOrder))
                .collect(Collectors.toList());

        for (MenuFieldDTO entityDTO : menuFieldDTOS) {
            List<MenuFieldDTO> childrenEntities = this.getObjectTree(entityDTO.getId());
            entityDTO.setMenuFieldList(childrenEntities);
        }

        return menuFieldDTOS;
    }

    public List<MenuFieldDTO> shortMenuFields(List<MenuFieldDTO> menuFieldDTOS) {

        if (menuFieldDTOS == null) return null;

        menuFieldDTOS = menuFieldDTOS.stream()
                .sorted(Comparator.comparingLong(MenuFieldDTO::getShortOrder))
                .collect(Collectors.toList());

        for (MenuFieldDTO entityDTO : menuFieldDTOS) {
            List<MenuFieldDTO> childrenEntities = this.shortMenuFields(entityDTO.getMenuFieldList());
            entityDTO.setMenuFieldList(childrenEntities);
        }

        return menuFieldDTOS;
    }



}
