package com.crm.sofia.model.rest_documentation;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.model.rest_documentation.rest_documentation_endpoint.RestDocumentationEndpoint;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity(name = "RestDocumentation")
@Table(name = "rest_documentation")
public class RestDocumentation extends MainEntity implements Serializable {

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean active;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "rest_documentation_id")
    private List<RestDocumentationEndpoint> restDocumentationEndpoints;
}
