package com.crm.sofia.mapper.sofia.user;

import com.crm.sofia.dto.sofia.menu.MenuFieldDTO;
import com.crm.sofia.dto.sofia.user.UserDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper extends BaseMapper<UserDTO, User> {

    public UserDTO mapUserToDtoWithMenu(User entity){
        UserDTO userDTO = this.mapUserToDto(entity);
        if(userDTO.getSidebarMenu() != null && userDTO.getSidebarMenu().getMenuFieldList() != null) {
            List<MenuFieldDTO> shorted = this.shortUserMenu(userDTO.getSidebarMenu().getMenuFieldList());
            userDTO.getSidebarMenu().setMenuFieldList(shorted);
        }

        if(userDTO.getHeaderMenu() != null && userDTO.getHeaderMenu().getMenuFieldList() != null) {
            List<MenuFieldDTO> shorted = this.shortUserMenu(userDTO.getHeaderMenu().getMenuFieldList());
            userDTO.getHeaderMenu().setMenuFieldList(shorted);
        }

        return userDTO;
    }

    private List<MenuFieldDTO> shortUserMenu(List<MenuFieldDTO> menuFieldList){

        menuFieldList =
                menuFieldList
                        .stream()
                        .sorted(Comparator.comparingLong(MenuFieldDTO::getShortOrder))
                        .collect(Collectors.toList());

        menuFieldList
                .stream()
                .filter(x -> x.getMenuFieldList() != null)
                .filter(x -> x.getMenuFieldList().size() > 0)
                .forEach(x -> {
                    List<MenuFieldDTO> shorted = this.shortUserMenu(x.getMenuFieldList());
                    x.setMenuFieldList(shorted);
                });

        return menuFieldList;
    }


    public List<MenuFieldDTO> shortMenu(List<MenuFieldDTO> menuFieldDTOS) {

        if (menuFieldDTOS == null) return null;

        menuFieldDTOS = menuFieldDTOS.stream()
                .sorted(Comparator.comparingLong(MenuFieldDTO::getShortOrder))
                .collect(Collectors.toList());

        menuFieldDTOS
                .stream()
                .filter(x -> x.getMenuFieldList() != null)
                .filter(x -> x.getMenuFieldList().size() > 0)
                .forEach(x -> {
                    List<MenuFieldDTO> shorted = shortMenu( x.getMenuFieldList());
                    x.setMenuFieldList(shorted);
                });

        return menuFieldDTOS;
    }


    @Mapping(ignore = true, target = "password")
    public abstract UserDTO mapUserToDto(User entity);

    public List<UserDTO> mapUsersToDtos(List<User> all) {
        return all.stream().map(this::mapUserToDto).collect(Collectors.toList());
    }

}
