package com.crm.sofia.model.rest_documentation.rest_documentation_endpoint;


import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ExcludeEndpointField")
@Table(name = "exclude_endpoint_field")
public class ExcludeEndpointField extends BaseEntity implements Serializable  {

    @Column(columnDefinition = "TEXT")
    private String excludeField;

}
