package com.example.springrestapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeathCheck {
    @GetMapping(value = "/ping")
    @ResponseBody

    public String ping() {
        return "pong";
    }
}
