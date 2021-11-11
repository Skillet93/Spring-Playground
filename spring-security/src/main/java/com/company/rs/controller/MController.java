package com.company.rs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/m")
@RestController
class MController {

    @GetMapping("/m1")
    public String m1() {
        log.info("M1 method called");
        return "m1";
    }

    @GetMapping("/m2")
    public String m2() {
        log.info("M2 method called");
        return "m2";
    }

    @GetMapping("/m3")
    public String m3() {
        log.info("M3 method called");
        return "m3";
    }
}
