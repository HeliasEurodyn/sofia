package com.crm.sofia.mapper.sofia.list.user;

import com.crm.sofia.dto.sofia.list.user.ListUiDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.list.ListComponentField;
import com.crm.sofia.model.sofia.list.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ListUiMapper extends BaseMapper<ListUiDTO, ListEntity> {

    public ListUiDTO mapList(ListEntity entity, Long languageId) {

        /*
         * Do Translations on Entity
         */
        if ((languageId == null ? 0 : languageId) > 0 &&
                ((entity.getTranslations() == null ? 0 : entity.getTranslations().size()) > 0)) {

            entity.getTranslations()
                    .stream()
                    .filter(translation -> translation.getLanguage().getId() == languageId)
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
                    .filter(translation -> translation.getLanguage().getId() == languageId)
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
                    .filter(translation -> translation.getLanguage().getId() == languageId)
                    .forEach(translation -> {
                        column.setDescription(translation.getDescription());
                    });
        });

        entity.getListComponentActionFieldList().forEach(column -> {
            column.getListComponentActionFieldList().forEach(subColumn -> {
                subColumn.getTranslations()
                        .stream()
                        .filter(translation -> translation.getLanguage().getId() == languageId)
                        .forEach(translation -> {
                            subColumn.setDescription(translation.getDescription());
                        });
            });
        });



        /*
         * Do Map
         */
        ListUiDTO dto = this.map(entity);

        return dto;
    }
}
