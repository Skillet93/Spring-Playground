package com.company.rs.controller;

import com.company.rs.domain.CalculationResult;
import com.company.rs.domain.Operation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;


    @GetMapping("add/firstNumber/{firstNumber}/secondNumber/{secondNumber}")
    public Double add(@PathVariable Integer firstNumber, @PathVariable Integer secondNumber) {
        double result = firstNumber + secondNumber;
        produceEvent(result, Operation.ADD);

        return result;
    }

    @GetMapping("divide/firstNumber/{firstNumber}/secondNumber/{secondNumber}")
    public Double divide(@PathVariable Double firstNumber, @PathVariable Double secondNumber) {
        double result = firstNumber / secondNumber;
        produceEvent(result, Operation.DIVISION);

        return result;
    }

    @GetMapping("subtraction/firstNumber/{firstNumber}/secondNumber/{secondNumber}")
    public Double subtraction(@PathVariable Double firstNumber, @PathVariable Double secondNumber) {
        double result = firstNumber - secondNumber;
        produceEvent(result, Operation.SUBTRACTION);

        return result;
    }

    @GetMapping("multiplication/firstNumber/{firstNumber}/secondNumber/{secondNumber}")
    public Double multiplication(@PathVariable Double firstNumber, @PathVariable Double secondNumber) {
        double result = firstNumber * secondNumber;
        produceEvent(result, Operation.MULTIPLICATION);

        return result;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    private void produceEvent(double result, Operation operation) {
        applicationEventPublisher.publishEvent(CalculationResult.builder()
                .object(this)
                .operation(operation)
                .result(result)
                .build()
        );
    }
}
