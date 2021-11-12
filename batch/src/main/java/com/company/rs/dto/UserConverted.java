package com.company.rs.dto;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserConverted {
    private String firstName;
    private String lastName;
    private Integer age;

}
