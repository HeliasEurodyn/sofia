package com.crm.sofia.dto.list;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.ComponentDTO;
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

    private ComponentDTO component;
    private List<ListComponentFieldDTO> listComponentColumnFieldList;
    private List<ListComponentFieldDTO> listComponentFilterFieldList;
    private List<ListComponentFieldDTO> listComponentLeftGroupFieldList;
    private List<ListComponentFieldDTO> listComponentTopGroupFieldList;
    private List<ListComponentFieldDTO> listComponentOrderByFieldList;
    private List<ListComponentFieldDTO> listComponentActionFieldList;

    // private List<ListComponentDTO> listComponentList;
}
