package com.crm.sofia.dto.list.base;

import com.crm.sofia.dto.access_control.AccessControlDTO;
import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.list.base.translation.ListTranslationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ListDTO extends BaseDTO {

    private String code;
    private String name;
    private String headerTitle;
    private String HeaderDescription;
    private String headerIcon;
    private String title;
    private String description;
    private String groupingTitle;
    private String groupingDescription;
    private String icon;
    private String selector;
    private String filterFieldStructure;
    private Boolean customFilterFieldStructure;
    private Boolean exportExcel;
    private String defaultPage;
    private Boolean autoRun;
    private Boolean listVisible;
    private Boolean filterVisible;
    private Boolean hasPagination;
    private Long pageSize;
    private Long totalPages;
    private Long currentPage;
    private Long totalRows;
    private Boolean hasMaxSize;
    private Long maxSize;
    private Boolean HeaderFilters;
    private String rowNavigation;
    private String jsonUrl;
    private ComponentDTO component;
    private Long instanceVersion;
    private Boolean accessControlEnabled;
    private String businessUnit;
    private List<AccessControlDTO> accessControls;
    private List<ListScriptDTO> listScripts;
    private List<ListCssDTO> listCssList;

    private List<ListTranslationDTO> translations;

    private List<ListActionButtonDTO> listActionButtons;
    private List<ListComponentFieldDTO> listComponentColumnFieldList;
    private List<ListComponentFieldDTO> listComponentFilterFieldList;
    private List<ListComponentFieldDTO> listComponentLeftGroupFieldList;
    private List<ListComponentFieldDTO> listComponentTopGroupFieldList;
    private List<ListComponentFieldDTO> listComponentOrderByFieldList;
    private List<ListComponentFieldDTO> listComponentActionFieldList;

    private String selectQuery;
    private String selectLeftGroupQuery;
    private String fromQuery;
}
