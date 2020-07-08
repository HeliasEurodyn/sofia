package com.crm.sofia.model.test;

import com.crm.sofia.model.Pizza;
import com.crm.sofia.model.common.BaseEntity;
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
@Entity(name = "NeapolitanPizza")
@javax.persistence.Table(name = "neapolitan_pizza")
@Accessors(chain = true)
@DynamicUpdate
public class NeapolitanPizza extends Pizza {

    @Column
    private String napoliSuburb;

    @Override
    public void tastesLike() {
        System.out.println("Tastes like Neapolitan Pizza...");
    }


}
