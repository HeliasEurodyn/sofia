package com.crm.sofia.mapper.view;

import com.crm.sofia.dto.view.ViewDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.view.View;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ViewFieldMapper.class})
public abstract class ViewMapper extends BaseMapper<ViewDTO, View> {
}
