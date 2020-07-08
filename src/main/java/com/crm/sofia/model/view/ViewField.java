package com.crm.sofia.model.view;

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
@Entity(name = "ViewField")
@javax.persistence.Table(name = "custom_view_field")
@Accessors(chain = true)
@DynamicUpdate
public class ViewField extends PersistEntityField {
}
