package com.company.rs.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CalculationResult extends ApplicationEvent {
    private Operation operation;
    private Double result;

    @Builder
    public CalculationResult(Object object, Double result, Operation operation) {
        super(object);
        this.operation = operation;
        this.result = result;
    }
}
