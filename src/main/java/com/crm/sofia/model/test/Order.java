package com.crm.sofia.model.test;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
public class Order {

    private String name;
    private String cardType;
    private int discount;
    private int price;

}
