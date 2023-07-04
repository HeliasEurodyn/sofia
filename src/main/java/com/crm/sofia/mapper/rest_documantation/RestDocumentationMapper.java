package com.crm.sofia.mapper.rest_documantation;

import com.crm.sofia.dto.rest_documentation.RestDocumentationDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.rest_documentation.RestDocumentation;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class RestDocumentationMapper extends BaseMapper<RestDocumentationDTO, RestDocumentation> {

    public List<RestDocumentationDTO> mapEntitiesForList(List<RestDocumentation> entities) {
        return entities.stream().map(this::mapEntityIgnoringQuery).collect(Collectors.toList());
    }


    public abstract RestDocumentationDTO mapEntityIgnoringQuery(RestDocumentation restDocumentation);
}
