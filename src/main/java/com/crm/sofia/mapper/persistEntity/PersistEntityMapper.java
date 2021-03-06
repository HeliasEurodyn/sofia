package com.crm.sofia.mapper.persistEntity;

import com.crm.sofia.dto.persistEntity.PersistEntityDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.persistEntity.PersistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class PersistEntityMapper extends BaseMapper<PersistEntityDTO, PersistEntity> {
}
