package com.crm.sofia.mapper.html_template;

import com.crm.sofia.dto.html_template.HtmlTemplateDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.html_template.HtmlTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class HtmlTemplateMapper extends BaseMapper<HtmlTemplateDTO, HtmlTemplate> {

    public List<HtmlTemplateDTO> mapEntitiesForList(List<HtmlTemplate> entities) {
        return entities.stream().map(this::mapEntityIgnoringQuery).collect(Collectors.toList());
    }

    @Mapping(ignore = true, target = "html")
    public abstract HtmlTemplateDTO mapEntityIgnoringQuery(HtmlTemplate entity);
}
