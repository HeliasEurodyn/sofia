package com.crm.sofia.model.form;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.ComponentPersistEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "FormScript")
@Table(name = "form_script")
public class FormScript extends BaseEntity {

    private String name;

    private String script;

}
