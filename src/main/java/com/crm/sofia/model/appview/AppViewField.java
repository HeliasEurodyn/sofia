package com.crm.sofia.model.appview;

import com.crm.sofia.model.common.BaseNoIdEntity;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "AppViewField")
@DiscriminatorValue("AppViewField")
public class AppViewField extends PersistEntityField {
}
