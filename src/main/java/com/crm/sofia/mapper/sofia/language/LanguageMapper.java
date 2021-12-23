package com.crm.sofia.mapper.sofia.language;

import com.crm.sofia.dto.sofia.language.LanguageDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.language.Language;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class LanguageMapper extends BaseMapper<LanguageDTO, Language> {
}
