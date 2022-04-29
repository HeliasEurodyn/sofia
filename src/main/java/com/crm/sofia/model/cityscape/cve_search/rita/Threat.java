package com.crm.sofia.model.cityscape.cve_search.rita;

import com.crm.sofia.model.common.MainEntity;
import lombok.Data;

import javax.persistence.Column;

@Data
//@Accessors(chain = true)
//@Entity(name = "Threat")
//@Table(name = "threat")
public class Threat extends MainEntity {

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "occurrence_probability")
    Double occurrenceProbability;


}
