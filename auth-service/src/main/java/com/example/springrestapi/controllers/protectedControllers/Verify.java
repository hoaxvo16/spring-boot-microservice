package com.example.springrestapi.controllers.protectedControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/verify")
public class Verify {

    @GetMapping(value = "/admin")
    public void verifyAdmin() {
        System.out.println("OK");
    }
}
