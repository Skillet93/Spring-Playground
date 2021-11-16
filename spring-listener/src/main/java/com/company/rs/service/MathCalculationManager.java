package com.company.rs.service;

import com.company.rs.domain.CalculationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MathCalculationManager implements ApplicationListener<CalculationResult> {

    //@SneakyThrows
    @Override
    public void onApplicationEvent(CalculationResult event) {
        //Thread.sleep(5000L);
        log.info("There is new event for {} operation with result {}", event.getOperation(), event.getResult());
    }
}
