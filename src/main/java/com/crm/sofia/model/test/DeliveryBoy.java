package com.crm.sofia.model.test;


import com.crm.sofia.model.Pizza;
import com.crm.sofia.model.common.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@Getter
@Setter
@Entity(name = "DeliveryBoy")
@javax.persistence.Table(name = "delivery_boy")
@Accessors(chain = true)
@DynamicUpdate
public class DeliveryBoy extends BaseEntity {

//    @JoinColumn(name = "delivery_boy_id")
//    private List<Pizza> pizzaList;

    @OneToMany(targetEntity = Pizza.class)
    @JoinColumn(name = "delivery_boy_id")
    private List<Pizza> pizzaList;

}
