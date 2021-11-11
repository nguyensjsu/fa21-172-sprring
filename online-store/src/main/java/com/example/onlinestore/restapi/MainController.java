package com.example.onlinestore.restapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {
    private CustomerRepository customerRepo;

    @GetMapping("/")
    String home() {
        return "Welcome to our store Gong Cha!";
    }
}
