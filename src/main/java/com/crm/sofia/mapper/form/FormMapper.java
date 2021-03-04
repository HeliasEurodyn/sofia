package com.crm.sofia.mapper.form;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.form.FormDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class FormMapper extends BaseMapper<FormDTO, FormEntity> {

}
