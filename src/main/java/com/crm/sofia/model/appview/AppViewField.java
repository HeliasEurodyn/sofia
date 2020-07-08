package com.crm.sofia.model.appview;

import com.crm.sofia.model.common.BaseNoIdEntity;
import com.crm.sofia.model.persistEntity.PersistEntityField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Data
@Getter
@Setter
@Entity(name = "AppViewField")
@javax.persistence.Table(name = "app_view_field")
@Accessors(chain = true)
@DynamicUpdate
public class AppViewField extends PersistEntityField {
}
