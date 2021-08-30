package com.crm.sofia.dto.sofia.list.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class ListResultsDataUiDTO {
    List<Map<String, Object>> listContent;
//    List<GroupEntryDTO> groupContent;
    Long totalPages;
    Long currentPage;
    Long pageSize;
    Long totalRows;
}
