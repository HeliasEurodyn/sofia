package com.crm.sofia.mapper.sofia.form.user;

import com.crm.sofia.dto.sofia.form.user.FormUiDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class FormUiMapper extends BaseMapper<FormUiDTO, FormEntity> {

    public FormUiDTO mapForm(FormEntity entity) {
        FormUiDTO dto = this.map(entity);
        dto.getFormScripts().forEach(formScriptDTO -> formScriptDTO.setScript(""));
        return dto;
    }

}
