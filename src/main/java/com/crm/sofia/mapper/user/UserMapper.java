package com.crm.sofia.mapper.user;

import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper extends BaseMapper<UserDTO, User> {

    @Mapping(ignore = true, target = "password")
    public abstract UserDTO mapUserToDto(User entity);

    public List<UserDTO> mapUsersToDtos(List<User> all) {
        return all.stream().map(this::mapUserToDto).collect(Collectors.toList());
    }

}
