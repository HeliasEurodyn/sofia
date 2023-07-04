package com.crm.sofia.model.rest_documentation.rest_documentation_endpoint;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.model.list.ListEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "RestDocumentationEndpoint")
@Table(name = "rest_documentation_endpoint")
public class RestDocumentationEndpoint extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ListEntity.class)
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private ListEntity list;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = FormEntity.class)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private FormEntity form;

    @Column
    private String title;

    @Column(length=1024)
    private String description;

    @Column
    private String type;

    @Column
    private String method;
}
