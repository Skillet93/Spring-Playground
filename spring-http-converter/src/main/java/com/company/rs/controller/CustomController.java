package com.company.rs.controller;

import com.company.rs.domain.CustomDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @PostMapping(path = "add")
    public void acceptCustomTextType(@RequestBody CustomDTO customObject) {
        System.out.println(customObject.getFirstObject());
        System.out.println(customObject.getFieldTwo());
        System.out.println(customObject.getFieldThree());
    }
}
