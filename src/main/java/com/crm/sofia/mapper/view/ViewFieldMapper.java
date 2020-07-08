package com.crm.sofia.mapper.view;

import com.crm.sofia.dto.view.ViewFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.mapper.persistEntity.PersistEntityFieldMapper;
import com.crm.sofia.model.view.ViewField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ViewFieldMapper extends PersistEntityFieldMapper<ViewFieldDTO, ViewField> {
}
