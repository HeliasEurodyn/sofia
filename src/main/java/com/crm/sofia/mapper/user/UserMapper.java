package com.crm.sofia.mapper.user;

import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.menu.MenuField;
import com.crm.sofia.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper extends BaseMapper<UserDTO, User> {


    public UserDTO mapUserToDtoWithMenu(User entity){
        UserDTO userDTO = this.mapUserToDto(entity);
        if(userDTO.getMenu() == null) {
            return userDTO;
        }

        if(userDTO.getMenu().getMenuFieldList() == null) {
            return userDTO;
        }

        List<MenuFieldDTO> menuFieldDTOS =
                userDTO.getMenu().getMenuFieldList()
                        .stream()
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

        userDTO.getMenu().setMenuFieldList(menuFieldDTOS);

        return userDTO;
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
