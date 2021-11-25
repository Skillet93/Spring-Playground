package com.company.rs.controller;

import com.company.rs.domain.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/book")
    public void orderBook(@RequestBody BookDTO book){
        jmsTemplate.convertAndSend("order-queue", book);
    }
}
