package com.crm.sofia.dto.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class GroupEntryDTO {

    String id;

    String code;

    Object value;

    int count;

    GroupEntryDTO parrent;

    List<GroupEntryDTO> children = new ArrayList<>();
}
