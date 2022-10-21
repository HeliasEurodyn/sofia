package com.crm.sofia.model.business_unit;

import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity(name = "BusinessUnit")
@Table(name = "business_unit")
public class BusinessUnit extends MainEntity {

    @Column
    private String title;

    @Column
    private String description;

}
