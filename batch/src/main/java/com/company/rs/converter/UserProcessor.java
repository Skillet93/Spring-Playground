package com.company.rs.converter;

import com.company.rs.dto.UserConverted;
import com.company.rs.dto.UserRaw;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Slf4j
public class UserProcessor implements ItemProcessor<UserRaw, UserConverted> {
    private final DateTimeFormatter shortDataTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public UserConverted process(UserRaw rawUser) {
        log.debug("Process data for {} {}", rawUser.getFirstName(), rawUser.getLastName());
        return UserConverted.builder()
                .firstName(rawUser.getFirstName())
                .lastName(rawUser.getLastName())
                .age(getUserAge(rawUser))
                .build();
    }

    private int getUserAge(UserRaw rawUser) {
        return Period.between(LocalDate.parse(rawUser.getBirthDate(), shortDataTimeFormat), LocalDate.now()).getYears();
    }
}
