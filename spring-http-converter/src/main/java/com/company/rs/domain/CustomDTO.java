package com.company.rs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CustomDTO {
    private final CustomSubField firstObject;
    private final String fieldTwo;
    private final String fieldThree;
}
