package com.crm.sofia.mapper.appview;

import com.crm.sofia.dto.appview.AppViewFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.appview.AppViewField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class AppViewFieldMapper extends BaseMapper<AppViewFieldDTO, AppViewField> {
}
