package com.crm.sofia.mapper.appview;

import com.crm.sofia.dto.appview.AppViewDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.appview.AppView;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {AppViewFieldMapper.class})
public abstract class AppViewMapper extends BaseMapper<AppViewDTO, AppView> {
}
