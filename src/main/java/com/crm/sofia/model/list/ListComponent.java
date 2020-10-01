package com.crm.sofia.model.list;

import com.crm.sofia.dto.list.ListComponentFieldDTO;
import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.model.component.Component;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListComponent")
@Table(name = "list_component")
public class ListComponent extends BaseEntity {


}
