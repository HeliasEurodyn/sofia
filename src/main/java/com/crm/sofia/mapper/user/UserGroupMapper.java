package com.crm.sofia.mapper.user;

import com.crm.sofia.dto.user.UserGroupDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.user.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserGroupMapper extends BaseMapper<UserGroupDTO, UserGroup> {
}
