package com.crm.sofia.services.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public MenuDTO getObject(String id, String languageId) {

        Optional<Menu> optionalEntity = this.menuRepository.findTreeById(id);
        if (!optionalEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        Menu entity = optionalEntity.get();
        MenuDTO dto = this.menuMapper.mapMenu(entity, languageId);

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

}
