package com.crm.sofia.model.cityscape.cve_search.rita;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
//@Accessors(chain = true)
//@Entity(name = "Threat")
//@Table(name = "threat")
public class Threat extends BaseEntity {

    @Column(name = "code")
    String code;

    @Column(name = "name")
    String name;

    @Column(name = "occurrence_probability")
    Double occurrenceProbability;


}
