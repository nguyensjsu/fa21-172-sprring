package com.example.customer;

import java.util.Random;
import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
public class CustomerController {
    
    @Autowired
    private CustomerRepository repository;
    private List<Customer> customers;
  
    MainController(CustomerRepository repository) {
        this.repository = repository;
    }

    class Message {
        private String status;
        public String getStatus() {
            return status;
        }
        public void setStatus(String msg) {
            status = msg;
        }
    }


    //Ping method - check status of customer server
    @GetMapping(value={"/","/ping"})
    String home() {
        return "Welcome to Gong Cha! - Customer Management";
    }


//==================================================================================================
//Customer related calls
    //Get all customers
    @GetMapping("/customers")
    public List<Customer> all() {
        return 
    }
    

}
