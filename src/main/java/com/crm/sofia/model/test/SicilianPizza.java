package com.crm.sofia.model.test;


import com.crm.sofia.model.Pizza;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Getter
@Setter
@Entity(name = "SicilianPizza")
@javax.persistence.Table(name = "sicilian_pizza")
@Accessors(chain = true)
@DynamicUpdate
public class SicilianPizza extends Pizza {

    @Column
    private int pizzaLength;

    @Override
    public void tastesLike() {
        System.out.println("Tastes like Sicilian Pizza...");
    }
}
