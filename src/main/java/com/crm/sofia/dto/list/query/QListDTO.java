package com.crm.sofia.dto.list.query;

import com.crm.sofia.dto.common.BaseDTO;
import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class QListDTO extends BaseDTO {

    private String filterFieldStructure;
    private Boolean customFilterFieldStructure;

    private Boolean hasPagination;
    private Long pageSize;
    private Long totalPages;
    private Long currentPage;
    private Long totalRows;
    private Boolean hasMaxSize;
    private Long maxSize;

    private QComponentDTO component;

    private List<QListComponentFieldDTO> listComponentColumnFieldList;
    private List<QListComponentFieldDTO> listComponentFilterFieldList;
    private List<QListComponentFieldDTO> listComponentLeftGroupFieldList;
    private List<QListComponentFieldDTO> listComponentOrderByFieldList;

    private String selectQuery;
    private String fromQuery;
}
