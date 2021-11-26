package com.company.rs.controller;

import com.company.rs.domain.BookDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@RestController
@RequestMapping("/books")
public class BookController {


    @GetMapping(value = "/stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<BookDto> getBooksStream(@RequestParam int size) {
        return Flux
                .just(generateBook(size))
                .delayElements(Duration.ofSeconds(2))
                .log();
    }

    private BookDto[] generateBook(int size) {
        BookDto[] books = new BookDto[size];
        for (int i = 0; i < size; i++) {
            books[i] = BookDto.builder()
                    .title(RandomStringUtils.randomAlphabetic(10))
                    .author(RandomStringUtils.randomAlphabetic(10))
                    .price(new Random().nextInt())
                    .build();
        }
        return books;
    }
}
