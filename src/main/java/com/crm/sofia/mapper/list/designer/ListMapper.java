package com.crm.sofia.mapper.list.designer;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.base.ListComponentFieldDTO;
import com.crm.sofia.dto.list.base.ListComponentSubFieldDTO;
import com.crm.sofia.dto.list.base.ListDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.list.ListComponentField;
import com.crm.sofia.model.list.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ListMapper extends BaseMapper<ListDTO, ListEntity> {

    public List<ListDTO> mapEntitiesForList(List<ListEntity> entities) {
        return entities.stream().map(this::mapEntityForList).collect(Collectors.toList());
    }

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
    @Mapping(ignore = true, target = "hasMaxSize")
    @Mapping(ignore = true, target = "maxSize")
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


//    public ListDTO mapList(ListEntity entity) {
//        ListDTO dto = this.map(entity);
//
//        if (dto.getListComponentColumnFieldList() != null) {
//            dto.getListComponentColumnFieldList()
//                    .stream()
//                    .filter(x -> x.getEditor() != null)
//                    .forEach(x -> {
//                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
//                        x.setEditor(encQuery);
//                    });
//        }
//
//        if (dto.getListComponentFilterFieldList() != null) {
//            dto.getListComponentFilterFieldList()
//                    .stream()
//                    .filter(x -> x.getEditor() != null)
//                    .forEach(x -> {
//                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
//                        x.setEditor(encQuery);
//                    });
//        }
//
//        if (dto.getListComponentLeftGroupFieldList() != null) {
//            dto.getListComponentLeftGroupFieldList()
//                    .stream()
//                    .filter(x -> x.getEditor() != null)
//                    .forEach(x -> {
//                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
//                        x.setEditor(encQuery);
//                    });
//        }
//
//        if (dto.getListComponentTopGroupFieldList() != null) {
//            dto.getListComponentTopGroupFieldList()
//                    .stream()
//                    .filter(x -> x.getEditor() != null)
//                    .forEach(x -> {
//                        String encQuery = Base64.getEncoder().encodeToString(x.getEditor().getBytes(StandardCharsets.UTF_8));
//                        x.setEditor(encQuery);
//                    });
//        }
//
//        return dto;
//    }


    public ListDTO mapList(ListEntity entity, String languageId) {

        /*
         * Do Translations on Entity
         */
        if ( !(languageId == null ? "" : languageId).equals("") &&
                ((entity.getTranslations() == null ? 0 : entity.getTranslations().size()) > 0)) {

            entity.getTranslations()
                    .stream()
                    .filter(translation -> translation.getLanguage().getId().equals(languageId))
                    .forEach(translation -> {
                        entity.setHeaderTitle(translation.getHeaderTitle());
                        entity.setHeaderDescription(translation.getHeaderDescription());
                        entity.setTitle(translation.getTitle());
                        entity.setDescription(translation.getDescription());
                        entity.setGroupingTitle(translation.getGroupingTitle());
                        entity.setGroupingDescription(translation.getGroupingDescription());
                    });
        }

        entity.getListActionButtons().forEach(column -> {
            column.getTranslations()
                    .stream()
                    .filter(translation -> translation.getLanguage().getId().equals(languageId))
                    .forEach(translation -> {
                        column.setDescription(translation.getDescription());
                    });
        });

        List<ListComponentField> fields = new ArrayList<>();
        fields.addAll(entity.getListComponentColumnFieldList());
        fields.addAll(entity.getListComponentActionFieldList());
        fields.addAll(entity.getListComponentLeftGroupFieldList());
        fields.addAll(entity.getListComponentTopGroupFieldList());
        fields.addAll(entity.getListComponentFilterFieldList());

        fields.forEach(column -> {
            column.getTranslations()
                    .stream()
                    .filter(translation -> translation.getLanguage().getId().equals(languageId))
                    .forEach(translation -> {
                        column.setDescription(translation.getDescription());
                    });
        });

        entity.getListComponentActionFieldList().forEach(column -> {
            column.getListComponentActionFieldList().forEach(subColumn -> {
                subColumn.getTranslations()
                        .stream()
                        .filter(translation -> translation.getLanguage().getId().equals(languageId))
                        .forEach(translation -> {
                            subColumn.setDescription(translation.getDescription());
                        });
            });
        });

        /*
         * Do Map
         */
        ListDTO dto = this.map(entity);

        dto.getComponent().getComponentPersistEntityList().sort(Comparator.comparingLong(ComponentPersistEntityDTO::getShortOrder));
        dto.getListComponentColumnFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        dto.getListComponentFilterFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        dto.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        dto.getListComponentLeftGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        dto.getListComponentOrderByFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        dto.getListComponentTopGroupFieldList().sort(Comparator.comparingLong(ListComponentFieldDTO::getShortOrder));
        dto.getListComponentActionFieldList().forEach(af -> {
            if( af.getListComponentActionFieldList() != null) {
                af.getListComponentActionFieldList().sort(Comparator.comparingLong(ListComponentSubFieldDTO::getShortOrder));
            }
        });

        return dto;
    }


}
