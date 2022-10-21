package com.crm.sofia.mapper.list.designer;

import com.crm.sofia.dto.sofia.list.base.ListDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.list.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ListMapper extends BaseMapper<ListDTO, ListEntity> {

    public List<ListDTO> mapEntitiesForList(List<ListEntity> entities) {
        return entities.stream().map(this::mapEntityForList).collect(Collectors.toList());
    }

    @Mapping(ignore = true, target = "createdBy")
    @Mapping(ignore = true, target = "version")
    @Mapping(ignore = true, target = "shortOrder")
    @Mapping(ignore = true, target = "listActionButtons")
    @Mapping(ignore = true, target = "listComponentColumnFieldList")
    @Mapping(ignore = true, target = "listComponentFilterFieldList")
    @Mapping(ignore = true, target = "listComponentLeftGroupFieldList")
    @Mapping(ignore = true, target = "listComponentTopGroupFieldList")
    @Mapping(ignore = true, target = "listComponentOrderByFieldList")
    @Mapping(ignore = true, target = "listComponentActionFieldList")
    @Mapping(ignore = true, target = "headerTitle")
    @Mapping(ignore = true, target = "headerIcon")
    @Mapping(ignore = true, target = "title")
    @Mapping(ignore = true, target = "description")
    @Mapping(ignore = true, target = "icon")
    @Mapping(ignore = true, target = "selector")
    @Mapping(ignore = true, target = "filterFieldStructure")
    @Mapping(ignore = true, target = "customFilterFieldStructure")
    @Mapping(ignore = true, target = "exportExcel")
    @Mapping(ignore = true, target = "defaultPage")
    @Mapping(ignore = true, target = "autoRun")
    @Mapping(ignore = true, target = "listVisible")
    @Mapping(ignore = true, target = "filterVisible")
    @Mapping(ignore = true, target = "hasPagination")
    @Mapping(ignore = true, target = "pageSize")
    @Mapping(ignore = true, target = "totalPages")
    @Mapping(ignore = true, target = "currentPage")
    @Mapping(ignore = true, target = "totalRows")
    @Mapping(ignore = true, target = "hasMaxSize")
    @Mapping(ignore = true, target = "maxSize")
    @Mapping(ignore = true, target = "rowNavigation")
    @Mapping(ignore = true, target = "jsonUrl")
    @Mapping(ignore = true, target = "instanceVersion")
    @Mapping(ignore = true, target = "translations")
    @Mapping(ignore = true, target = "component.componentPersistEntityList")
    public abstract ListDTO mapEntityForList(ListEntity entity);

    public ListEntity mapListDTO(ListDTO dto) {

        if (dto.getListComponentColumnFieldList() != null) {
            dto.getListComponentColumnFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String dec = new String(Base64.getDecoder().decode(x.getEditor().getBytes(StandardCharsets.UTF_8)));
                        x.setEditor(dec);
                    });
        }

        if (dto.getListComponentFilterFieldList() != null) {
            dto.getListComponentFilterFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String dec = new String(Base64.getDecoder().decode(x.getEditor().getBytes(StandardCharsets.UTF_8)));
                        x.setEditor(dec);
                    });
        }

        if (dto.getListComponentLeftGroupFieldList() != null) {
            dto.getListComponentLeftGroupFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String dec = new String(Base64.getDecoder().decode(x.getEditor().getBytes(StandardCharsets.UTF_8)));
                        x.setEditor(dec);
                    });
        }

        if (dto.getListComponentTopGroupFieldList() != null) {
            dto.getListComponentTopGroupFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String dec = new String(Base64.getDecoder().decode(x.getEditor().getBytes(StandardCharsets.UTF_8)));
                        x.setEditor(dec);
                    });
        }

        return this.map(dto);
    }


    public ListDTO mapList(ListEntity entity) {
        ListDTO dto = this.map(entity);

        if (dto.getListComponentColumnFieldList() != null) {
            dto.getListComponentColumnFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
                        x.setEditor(encQuery);
                    });
        }

        if (dto.getListComponentFilterFieldList() != null) {
            dto.getListComponentFilterFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
                        x.setEditor(encQuery);
                    });
        }

        if (dto.getListComponentLeftGroupFieldList() != null) {
            dto.getListComponentLeftGroupFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
                        x.setEditor(encQuery);
                    });
        }

        if (dto.getListComponentTopGroupFieldList() != null) {
            dto.getListComponentTopGroupFieldList()
                    .stream()
                    .filter(x -> x.getEditor() != null)
                    .forEach(x -> {
                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
                        x.setEditor(encQuery);
                    });
        }

        return dto;
    }


}
