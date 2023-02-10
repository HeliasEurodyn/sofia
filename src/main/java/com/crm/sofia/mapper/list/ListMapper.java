package com.crm.sofia.mapper.list;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.dto.list.ListComponentSubFieldDTO;
import com.crm.sofia.dto.list.ListDTO;
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
