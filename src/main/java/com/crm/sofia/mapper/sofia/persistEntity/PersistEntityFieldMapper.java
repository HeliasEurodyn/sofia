package com.crm.sofia.mapper.sofia.persistEntity;

import com.crm.sofia.dto.sofia.persistEntity.PersistEntityFieldDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntityField;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PersistEntityFieldMapper extends BaseMapper<PersistEntityFieldDTO, PersistEntityField> {}