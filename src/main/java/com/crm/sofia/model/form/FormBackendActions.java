package com.crm.sofia.model.form;

import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@Entity(name = "FormBackendActions")
@Table(name = "form_backend_actions")
public class FormBackendActions extends BaseEntity implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String editor;

    @Column(columnDefinition = "TEXT")
    private String trigger_on;

    @Column(name = "short_order")
    private Long shortOrder;
}
