package com.crm.sofia.dto.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
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
public class GroupEntryDTO {

    String code;

    Object value;

    int count;

    /*
     * The key is the value
     */
    List<GroupEntryDTO> children = new ArrayList<>();
}
