package com.crm.sofia.dto.sofia.list.user;

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
public class GroupEntryUiDTO {

    String id;

    String code;

    Object value;

    int count;

    GroupEntryUiDTO parrent;

    List<GroupEntryUiDTO> children = new ArrayList<>();
}
