package com.crm.sofia.model.sofia.list;

import com.crm.sofia.model.common.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "ListComponent")
@Table(name = "list_component")
public class ListComponent extends BaseEntity {


}
