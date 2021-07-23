package com.crm.sofia.mapper.sofia.form.designer;

import com.crm.sofia.dto.sofia.form.designer.FormDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class FormMapper extends BaseMapper<FormDTO, FormEntity> {

    public FormDTO mapForm(FormEntity entity) {
        FormDTO dto = this.map(entity);
        dto.getFormScripts().forEach(formScriptDTO -> formScriptDTO.setScript(""));
        return dto;
    }

}
