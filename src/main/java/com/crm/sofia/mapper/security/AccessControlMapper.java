package com.crm.sofia.mapper.security;

import com.crm.sofia.dto.security.SecurityDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.security.Security;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class AccessControlMapper extends BaseMapper<SecurityDTO, Security> {
}
