package com.crm.sofia.mapper.sofia.menu;

import com.crm.sofia.dto.sofia.list.user.ListUiDTO;
import com.crm.sofia.dto.sofia.menu.MenuDTO;
import com.crm.sofia.dto.sofia.menu.MenuFieldDTO;
import com.crm.sofia.dto.sofia.menu.MenuTranslationDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.list.ListEntity;
import com.crm.sofia.model.sofia.menu.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {MenuFieldMapper.class})
public abstract class MenuMapper extends BaseMapper<MenuDTO, Menu>  {


    public Menu mapDTO(MenuDTO dto) {
        Menu entity = this.map(dto);
        return entity;
    }

    public MenuDTO mapMenu(Menu entity, String languageId) {
        MenuDTO dto = this.map(entity);

        if (languageId != null) {
            this.mapUserMenuLanguageToTree(
                    dto.getMenuFieldList(),
                    languageId);
        }

        return dto;
    }

    public void mapUserMenuLanguageToTree(List<MenuFieldDTO> menuFieldList, String languageId) {
        menuFieldList
                .forEach(menuField -> {
                    if (menuField.getTranslations() == null) {
                        menuField.setTranslations(new ArrayList<>());
                    }

                    MenuTranslationDTO translation =
                            menuField.getTranslations()
                                    .stream()
                                    .filter(x -> x.getLanguage().getId().equals(languageId))
                                    .findFirst()
                                    .orElse(null);

                    if (translation != null) {
                        menuField.setName(translation.getName());
                    }

                    this.mapUserMenuLanguageToTree(menuField.getMenuFieldList(), languageId);
                });
    }


    public void mapDtoToEntity(MenuDTO dto, @MappingTarget Menu entity){
        this.dtoToEntity(dto,entity);
    }

    @Mapping(ignore = true, target = "modifiedBy")
    @Mapping(ignore = true, target = "modifiedOn")
    @Mapping(ignore = true, target = "createdBy")
//    @Mapping(ignore = true, target = "version")
    public abstract void dtoToEntity(MenuDTO dto, @MappingTarget Menu entity);

}
