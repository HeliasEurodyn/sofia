package com.crm.sofia.mapper.persistEntity;

import com.crm.sofia.dto.persistEntity.PersistEntityFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PersistEntityFieldMapper extends BaseMapper<PersistEntityFieldDTO, PersistEntityField> {}
