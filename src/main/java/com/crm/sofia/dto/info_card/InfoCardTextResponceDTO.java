package com.crm.sofia.dto.info_card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoCardTextResponceDTO {

    private String cardText;

    private String title;

    private String icon;

    private String iconColor;

}
