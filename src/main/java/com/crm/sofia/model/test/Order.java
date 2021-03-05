package com.crm.sofia.model.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class Order {

    private String name;
    private String cardType;
    private int discount;
    private int price;

}
