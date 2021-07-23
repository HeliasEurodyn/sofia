package com.crm.sofia.mapper.sofia.persistEntity;

import com.crm.sofia.dto.sofia.persistEntity.PersistEntityDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.persistEntity.PersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PersistEntityMapper extends BaseMapper<PersistEntityDTO, PersistEntity> {
}
