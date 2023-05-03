package com.crm.sofia.dto.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class HtmlTemplateNotificationParamsDTO {

    private String htmlReportId;
    private String htmlReportSelectionId;
    private List<String> receiverIds;

}
