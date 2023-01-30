package com.crm.sofia.mapper.list.query;

import com.crm.sofia.dto.list.base.ListDTO;
import com.crm.sofia.dto.list.query.QListDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.list.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class QListMapper extends BaseMapper<QListDTO, ListEntity> {
}
