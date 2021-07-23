package com.crm.sofia.controllers.test;

import com.crm.sofia.model.sofia.test.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
//
//    @Autowired
//    private KieSession session;

    @PostMapping("/order")
    public Order orderNow(@RequestBody Order order) {
//        session.insert(order);
//        session.fireAllRules();
        return order;
    }

}
