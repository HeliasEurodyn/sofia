package com.crm.sofia.mapper.business_unit;

import com.crm.sofia.dto.sofia.business_unit.BusinessUnitDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.business_unit.BusinessUnit;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class BusinessUnitMapper extends BaseMapper<BusinessUnitDTO, BusinessUnit> {
}
