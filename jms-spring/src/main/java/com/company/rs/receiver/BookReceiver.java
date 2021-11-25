package com.company.rs.receiver;

import com.company.rs.domain.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookReceiver {

    @JmsListener(containerFactory = "jmsFactory", destination = "order-queue")
    public void receive (BookDTO book){
        log.info("Register the new book {}", book);
    }
}
