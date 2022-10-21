package com.crm.sofia.mapper.info_card;

import com.crm.sofia.dto.info_card.InfoCardDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.info_card.InfoCard;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class InfoCardMapper extends BaseMapper<InfoCardDTO, InfoCard> {
}
