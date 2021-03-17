package com.game.cricket.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Value("${db.user}")
    private String dbuser;

    @Value("${db.password}")
    private String dbpassword;


    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }


}
